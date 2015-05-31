package net.cubition.bootstrap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.cubition.bootstrap.config.LaunchConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Bootstrap class allows for dynamic and easy configuration of both server
 * and clients, and allows for them to be updated, and started, without any
 * effort from the user.
 *
 * The bootstrap also pulls any required dependencies for Resources, and
 * installs them as well.
 */
public class Bootstrap {
	private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

	/**
	 * The version of the Bootstrap.
	 */
	public static final String VERSION = "0.1";

	/**
	 * The default resource download server
	 */
	public static final String DEFAULT_RESOURCE_SERVER = "http://main.cubition.net/";

	/**
	 * The entry points that we can access Executables via
	 */
	private static final String[] EXECUTABLE_ENDPOINTS = { "net.cubition.server.ServerBaseController" };

	/**
	 * Parameter: Used mainly in a client situation, this param forces no GUI to
	 * appear.
	 */
	@Parameter(names = { "-nogui", "--no-gui" }, description = "Forces no GUI to appear")
	private boolean showGui = true;

	/**
	 * Parameter: Shows the help page, and exits.
	 */
	@Parameter(names = { "-h", "--help" }, description = "Show this help page")
	private boolean showHelp = false;

	/**
	 * Parameter: Errases the mod directory, to enable mods to be downloaded
	 * again.
	 */
	@Parameter(names = { "--clean" }, description = "Erase mods directory, and build from scratch")
	private boolean cleanBuild = false;

	/**
	 * Parameter: Forces debug messages to appear.
	 */
	@Parameter(names = { "--debug" }, description = "Forces debug messages to appear")
	private boolean debug = false;

	/**
	 * Parameter: Hides messages that are [INFO] and below
	 */
	@Parameter(names = { "--quiet" }, description = "Hides messages that are [INFO] and below")
	private boolean quiet = false;

	/**
	 * Defines where to store mod files.
	 *
	 * Parameter: Allows for changes to the final mod directory, if required.
	 */
	@Parameter(names = { "--modDir" }, description = "Sets the target directory for mods to be installed under")
	private String modDir = "mods";

	/**
	 * The location of the Bootstrap configuration.
	 *
	 * Parameter: Allows for a custom config to be inserted into a current
	 * setup.s
	 */
	@Parameter(names = { "--config" }, description = "Sets the configuration to be used")
	private String configLocation = "config.json";

	/**
	 * The main Bootstrap configuration. This defines Resources to be loaded, as
	 * well as other settings.
	 */
	private LaunchConfig configuration = null;

	/**
	 * The JSON parser converts the LaunchConfig to and from a String
	 * representation.
	 */
	private Gson jsonParser = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Starts the Bootstrap process, loading dependencies, mods, and other
	 * resources as required, and then starting the executable Resource defined.
	 */
	private void start() {
		// Read in a configuration, if available.
		File configLocation = new File(this.configLocation);

		try {
			if (!configLocation.exists()) {
				// It doesn't exist.
				throw new FileNotFoundException(configLocation.getPath());
			}

			// Open the file
			InputStream in = new BufferedInputStream(new FileInputStream(
					configLocation));

			// Lazy reading
			String contents = IOUtils.toString(in);

			// Close the stream
			in.close();

			// Parse it, hopefully.
			configuration = jsonParser.fromJson(contents, LaunchConfig.class);
		} catch (Exception e) {
			if (!(e instanceof FileNotFoundException)) {
				// Woops! Something went wrong!
				e.printStackTrace();
				System.exit(2);
			}
		}

		// Check if we have a configuration at this point.
		if (configuration == null) {
			LOG.info("Generating empty configuration at \""
					+ configLocation.getPath()
					+ "\". Configure at will, and restart Bootstrap.");

			// Generate ourselves one
			configuration = new LaunchConfig();

			// Save it, as a placeholder
			try {
				saveConfiguration();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(2);
			}

			// Success!
			// Give the user a chance to set things up.
			System.exit(0);
		}

		// Build complete mod directory
		File modDir = new File(this.modDir);

		// Clean dependencies, if needed
		if (cleanBuild) {
			LOG.info("Producing clean build, as requested.");
			if (modDir.exists()) {
				try {
					FileUtils.deleteDirectory(modDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Build dependencies
		LOG.info("Building dependencies...");

		// We know what we need fetch, so throw it all into a pool, and compute
		// dependencies.
		List<Resource> dependencyTree = new ArrayList<>();

		// Add the main executable dependency and mods
		dependencyTree.add(configuration.getExecutable());

		// Add the mods, making sure we don't define duplicates
		for (Resource modResource : configuration.getMods()) {
			boolean isDuplicate = false;
			// Compare against all we have currently
			for (Resource parentResource : dependencyTree
					.toArray(new Resource[dependencyTree.size()])) {
				// Compare the values between the two current resources
				if (modResource.getAuthor().equalsIgnoreCase(
						parentResource.getAuthor())
						&& modResource.getName().equalsIgnoreCase(
								parentResource.getName())) {
					// We have a duplicate mod definition!

					// If their versions match, we don't have a problem.
					if (modResource.getVersion().equalsIgnoreCase(
							parentResource.getVersion())) {
						isDuplicate = true;
						break;
					}

					// We can't really find out which one is newer, because
					// 'version' is a String.
					// Instead, use the first one we find.
					// This is dangerous; warn the user about it.
					LOG.warn("Duplicate Resource definition in your config.json."
							+ " Using first found (" + parentResource + ").");
					// Skip adding it in the future.
					isDuplicate = true;
					break;
				}
			}

			if (isDuplicate) {
				// This is a duplicate; don't add it.
				continue;
			}

			// We are all good!
			dependencyTree.add(modResource);
		}

		// Add dependencies
		for (Resource resource : dependencyTree
				.toArray(new Resource[dependencyTree.size()])) {
			// Add them together
			Resource[] resources = new Resource[0];
			try {
				resources = resource.pollDependenciesRecursively(modDir);
			} catch (IOException e) {
				LOG.error("Error while polling dependencies", e);
				System.exit(2);
			}

			// Add it, checking for duplicates
			for (Resource modResource : resources) {
				boolean isDuplicate = false;
				// Compare against all we have currently
				for (Resource parentResource : dependencyTree
						.toArray(new Resource[dependencyTree.size()])) {
					// Compare the values between the two current resources
					if (modResource.getAuthor().equalsIgnoreCase(
							parentResource.getAuthor())
							&& modResource.getName().equalsIgnoreCase(
									parentResource.getName())) {
						// We have a duplicate mod definition!

						// If their versions match, we don't have a problem.
						if (modResource.getVersion().equalsIgnoreCase(
								parentResource.getVersion())) {
							isDuplicate = true;
							break;
						}

						// We can't really find out which one is newer, because
						// 'version' is a String.
						// Instead, use the first one we find.
						// This is dangerous; warn the user about it.
						LOG.warn("Duplicate Resource definition in dependency for "
								+ resource
								+ "."
								+ " Using first found ("
								+ parentResource + ").");
						// Skip adding it in the future.
						isDuplicate = true;
						break;
					}
				}

				if (isDuplicate) {
					// This is a duplicate; don't add it.
					continue;
				}

				// We are all good!
				dependencyTree.add(modResource);
			}
		}

		LOG.debug("Built " + dependencyTree.size() + " dependencies.");
		LOG.info("Downloading dependencies...");

		// Create a very basic thread pool
		final ExecutorService pool = Executors.newFixedThreadPool(16);
		final CountDownLatch success = new CountDownLatch(1);

		dependencyTree.forEach((r) -> pool.submit(() -> {
			try {
				if (!r.downloadLocalCopy(modDir)) {
					success.countDown();
				}
			} catch (IOException e) {
				LOG.error("Error while download Resource @ " + r, e);
				success.countDown();
			}
		}));

		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				LOG.error("Failed to download dependencies in time!");
				pool.shutdownNow();
				System.exit(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(2);
		}

		// Make sure all succeeded!
		if (success.getCount() != 1) {
			// It failed
			LOG.error("Failed to download all dependencies. Check logs, and try again.");
			System.exit(2);
		}

		// Alright, pull the main server from the Resource bundle
		dependencyTree.remove(configuration.getExecutable());
		Resource mainExecutable = configuration.getExecutable();

		// Mount it
		URL mainExecutableJar = null;

		try {
			mainExecutableJar = mainExecutable.getLocalFile().toURI().toURL();
		} catch (MalformedURLException e) {
			LOG.error("Failure while importing main executable", e);
			System.exit(2);
		}

		// Create a classloader for it
		URLClassLoader loader = new URLClassLoader(
				new URL[] { mainExecutableJar });

		// Build exports
		Map<String, Object> values = new HashMap<>();

		ArrayList<String> mods = new ArrayList<>();
		for (Resource resource : dependencyTree) {
			if (resource.getLocalFile() != null) {
				mods.add(resource.getLocalFile().getPath());
			}
		}

		values.put("mods", mods);
		values.put("debug", this.debug);

		if (mainExecutable.getRemoteDescription() != null
				&& mainExecutable.getRemoteDescription().has("mainClass")) {
			// Woo - this resource defines its own mainClass - lets check there.
			String checkPath = mainExecutable.getRemoteDescription()
					.get("mainClass").getAsString();
			LOG.debug("Found mainClass attribute in executable Resource description: "
					+ checkPath);

			try {
				Class<?> checkClass = loader.loadClass(checkPath);
				// Check this classes methods
				for (Method method : checkClass.getDeclaredMethods()) {
					if (method.getName().equalsIgnoreCase("entry")) {
						LOG.info("Launching...");
						Object result = method.invoke(null, values);
						if (result != null && result instanceof Number) {
							System.exit(((Number) result).intValue());
						} else {
							System.exit(0);
						}
					}
				}
			} catch (ClassNotFoundException | InvocationTargetException
					| IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			LOG.debug("No mainClass attribute found. Using a set of defaults...");
			// Poll it for standard imports
			for (String checkPath : EXECUTABLE_ENDPOINTS) {
				try {
					Class<?> checkClass = loader.loadClass(checkPath);
					// Check this classes methods
					for (Method method : checkClass.getDeclaredMethods()) {
						if (method.getName().equalsIgnoreCase("entry")) {
							LOG.info("Launching...");
							Object result = method.invoke(null, values);
							if (result != null && result instanceof Number) {
								System.exit(((Number) result).intValue());
							} else {
								System.exit(0);
							}
						}
					}
				} catch (ClassNotFoundException ignored) {
				} catch (InvocationTargetException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		// We shouldn't be here!
		LOG.error("No entry point found in the executable Resource.");
		System.exit(1);
	}

	/**
	 * Saves the current Bootstrap configuration.
	 * 
	 * @throws IOException
	 *             If a IO error occurs while saving.
	 */
	public void saveConfiguration() throws IOException {
		// Open file
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				new File(configLocation)));

		// Write the new contents
		String result = jsonParser.toJson(configuration);

		out.writeBytes(result);

		// Flush and close
		out.flush();
		out.close();
	}

	/**
	 * Starts the Cubition bootstrap, and eventually a server or client, if
	 * available.
	 * 
	 * @param args
	 *            The arguments to apply to this bootstrap session.
	 */
	public static void main(String[] args) {
		// Create a new instance of the Bootstrap, with empty arguments
		Bootstrap instance = new Bootstrap();

		// Create a new instance of JCommander, the argument parser
		JCommander argParser = new JCommander(instance);

		// Setup in a way that is easy on the user, but ensures no mistakes
		argParser.setProgramName("java -jar bootstrap.jar"); // Used in the
																// usage page.
		argParser.setAcceptUnknownOptions(false);
		argParser.setAllowAbbreviatedOptions(true);
		argParser.setCaseSensitiveOptions(false);

		// Parse the arguments
		try {
			argParser.parse(args);
		} catch (Exception e) {
			// Display the opening header
			LOG.info("Cubition Bootstrap, version " + VERSION);

			// Woops! An error occured while parsing the options.
			// Tell the user what happened
			LOG.warn(e.getMessage());

			// Show the documentation for Cubition bootstrap.
			StringBuilder builder = new StringBuilder();
			argParser.usage(builder);
			LOG.info(builder.toString());

			// Exit, with -1 to indicate an error
			System.exit(-1);
		}

		// Check if we need to display help
		if (instance.showHelp) {
			// Display the opening header
			LOG.info("Cubition Bootstrap, version " + VERSION);

			// Show the documentation for Cubition bootstrap.
			StringBuilder builder = new StringBuilder();
			argParser.usage(builder);
			LOG.info(builder.toString());

			// Exit, with 0 to indicate success
			System.exit(0);
		}

		// Enable debugging, if required
		if (instance.debug && instance.quiet) {
			// Display the opening header
			LOG.info("Cubition Bootstrap, version " + VERSION);

			LOG.error("Conflicting arguments: --debug and --quiet");

			// Show the documentation for Cubition bootstrap.
			StringBuilder builder = new StringBuilder();
			argParser.usage(builder);
			LOG.info(builder.toString());

			System.exit(2);
		} else if (instance.debug) {
			LogManager.getRootLogger().setLevel(Level.DEBUG);
		} else if (instance.quiet) {
			LogManager.getRootLogger().setLevel(Level.WARN);
		}

		// Display the opening header
		LOG.info("Cubition Bootstrap, version " + VERSION);

		// Launch the bootstrap main
		instance.start();
	}
}
