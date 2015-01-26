package net.cubition.bootstrap;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.cubition.bootstrap.config.LaunchConfig;
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

/**
 * The Bootstrap class allows for dynamic and easy configuration of both server and clients, and allows for them
 * to be updated, and started, without any effort from the user.
 *
 * The bootstrap also pulls any required dependencies for Resources, and installs them as well.
 */
public class Bootstrap {
    /**
     * The version of the Bootstrap.
     */
    public static final String VERSION = "0.1";

    /**
     * The location of the Bootstrap configuration.
     */
    private static final File CONFIG_LOCATION = new File("config.json");

    /**
     * The default resource download server
     */
    public static final String DEFAULT_RESOURCE_SERVER = "http://main.cubition.net/";

    /**
     * The entry points that we can access Executables via
     */
    private static final String[] EXECUTABLE_ENDPOINTS = {"net.cubition.server.ServerBaseController"};

    /**
     * Parameter:
     *     Used mainly in a client situation, this param forces no GUI to appear.
     */
    @Parameter(names = {"-nogui", "--no-gui"}, description = "Forces no GUI to appear")
    private boolean showGui = true;

    /**
     * Parameter:
     *     Shows the help page, and exits.
     */
    @Parameter(names = {"-h", "--help"}, description = "Show this help page")
    private boolean showHelp = false;

    /**
     * The main Bootstrap configuration. This defines Resources to be loaded, as well as other settings.
     */
    private LaunchConfig configuration = null;

    /**
     * The JSON parser converts the LaunchConfig to and from a String representation.
     */
    private Gson jsonParser = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Starts the Bootstrap process, loading dependencies, mods, and other resources as required,
     * and then starting the executable Resource defined.
     */
    private void start() {
        // Read in a configuration, if available.
        try {
            if (!CONFIG_LOCATION.exists()) {
                // It doesn't exist.
                throw new FileNotFoundException(CONFIG_LOCATION.getPath());
            }

            // Open the file
            InputStream in = new BufferedInputStream(new FileInputStream(CONFIG_LOCATION));

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
            System.out.println("Generating empty configuration at \"" +
                    CONFIG_LOCATION.getPath() + "\". Configure at will, and restart Bootstrap.");

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

        // Build dependencies
        System.out.println("Building dependencies...");

        // We know what we need fetch, so throw it all into a pool, and compute dependencies.
        List<Resource> dependencyTree = new ArrayList<>();

        // Add the main executable dependency and mods
        dependencyTree.add(configuration.getExecutable());

        // Add the mods, making sure we don't define duplicates
        for (Resource modResource : configuration.getMods()) {
            boolean isDuplicate = false;
            // Compare against all we have currently
            for (Resource parentResource :
                    dependencyTree.toArray(new Resource[dependencyTree.size()])) {
                // Compare the values between the two current resources
                if (modResource.getAuthor().equalsIgnoreCase(parentResource.getAuthor())
                        && modResource.getName().equalsIgnoreCase(parentResource.getName())) {
                    // We have a duplicate mod definition!
                    // We can't really find out which one is newer, because 'version' is a String.
                    // Instead, use the first one we find.
                    // This is dangerous; warn the user about it.
                    System.out.println("WARNING: Duplicate Resource definition in your config.yml." +
                            " Using first found (" + parentResource + ").");
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
        for (Resource resource :
                dependencyTree.toArray(new Resource[dependencyTree.size()])) {
            // Add them together
            Resource[] resources = resource.pollDependenciesRecursively();

            // Add it, checking for duplicates
            for (Resource modResource : resources) {
                boolean isDuplicate = false;
                // Compare against all we have currently
                for (Resource parentResource :
                        dependencyTree.toArray(new Resource[dependencyTree.size()])) {
                    // Compare the values between the two current resources
                    if (modResource.getAuthor().equalsIgnoreCase(parentResource.getAuthor())
                            && modResource.getName().equalsIgnoreCase(parentResource.getName())) {
                        // We have a duplicate mod definition!
                        // We can't really find out which one is newer, because 'version' is a String.
                        // Instead, use the first one we find.
                        // This is dangerous; warn the user about it.
                        System.out.println("WARNING: Duplicate Resource definition in dependency for " + resource + "." +
                                " Using first found (" + parentResource + ").");
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

        // TODO: Download Resources, as required

        // Alright, pull the main server from the Resource bundle
        dependencyTree.remove(configuration.getExecutable());

        Resource mainExecutable = configuration.getExecutable();

        // Mount it
        URL mainExecutableJar = null;

        try {
            mainExecutableJar = mainExecutable.getLocalFile().toURI().toURL();
        } catch (MalformedURLException e) {
            System.err.println("Failure while importing main executable:");
            e.printStackTrace();
            System.exit(2);
        }

        // Create a classloader for it
        URLClassLoader loader = new URLClassLoader(new URL[]{
                mainExecutableJar
        });

        // Build exports
        Map<String, Object> values = new HashMap<>();

        ArrayList<String> mods = new ArrayList<>();
        for (Resource resource : dependencyTree) {
            if (resource.getLocalFile() != null) {
                mods.add(resource.getLocalFile().getPath());
            }
        }

        values.put("mods", mods);

        // Poll it for standard imports
        // TODO: Read configuration
        for (String checkPath : EXECUTABLE_ENDPOINTS) {
            try {
                Class<?> checkClass = loader.loadClass(checkPath);
                // Check this classes methods
                for (Method method : checkClass.getDeclaredMethods()) {
                    if (method.getName().equalsIgnoreCase("entry")) {
                        System.out.println("Launching...");
                        System.out.println("-----------------------------");
                        method.invoke(null, values);
                        System.exit(0);
                    }
                }
            } catch (ClassNotFoundException
                    | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // We shouldn't be here!
        System.err.println("No entry point found in the executable .jar.");
    }

    /**
     * Saves the current Bootstrap configuration.
     * 
     * @throws IOException If a IO error occurs while saving.
     */
    public void saveConfiguration() throws IOException {
        // Open file
        DataOutputStream out = new DataOutputStream(new FileOutputStream(CONFIG_LOCATION));

        // Write the new contents
        String result = jsonParser.toJson(configuration);

        out.writeBytes(result);

        // Flush and close
        out.flush();
        out.close();
    }

    /**
     * Starts the Cubition bootstrap, and eventually a server or client, if available.
     * @param args The arguments to apply to this bootstrap session.
     */
    public static void main(String[] args) {
        // Display the opening header
        System.out.println("Cubition Bootstrap, version " + VERSION);

        // Create a new instance of the Bootstrap, with empty arguments
        Bootstrap instance = new Bootstrap();

        // Create a new instance of JCommander, the argument parser
        JCommander argParser = new JCommander(instance);

        // Setup in a way that is easy on the user, but ensures no mistakes
        argParser.setProgramName("java -jar bootstrap.jar"); // Used in the usage page.
        argParser.setAcceptUnknownOptions(false);
        argParser.setAllowAbbreviatedOptions(true);
        argParser.setCaseSensitiveOptions(false);

        // Parse the arguments
        try {
            argParser.parse(args);
        } catch (Exception e) {
            // Woops! An error occured while parsing the options.
            // Tell the user what happened
            System.out.println(e.getMessage());

            // Show the documentation for Cubition bootstrap.
            argParser.usage();

            // Exit, with -1 to indicate an error
            System.exit(-1);
        }

        // Check if we need to display help
        if (instance.showHelp) {
            // Show the documentation for Cubition bootstrap.
            argParser.usage();

            // Exit, with 0 to indicate success
            System.exit(0);
        }

        // Launch the bootstrap main
        instance.start();
    }
}
