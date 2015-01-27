package net.cubition.server;

import net.cubition.api.ServerController;
import net.cubition.api.mod.BaseModManager;
import net.cubition.api.mod.ModManager;
import net.cubition.api.vc.Availability;
import net.cubition.api.vc.Versions;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * This class is the default entry point into the server
 */
@Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_NEVER)
public class ServerBaseController implements ServerController {
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final String RESTART_SCRIPT = WORKING_DIRECTORY + "restart.sh";

    private boolean initialized = false;
    private boolean running = false;
    private List<String> modFiles;
    private ModManager modManager;

    /**
     * Initializes the Server<br>
     * <br>
     * General Algorithm:<br>
     * <ol>
     *     <li>Create the server's ModManager</li>
     *     <li>Load all mods</li>
     *     <li>If any server provides a separate Server Controller, call into its initialize() method, then return</li>
     *     <li>Load worlds, entities, etc.</li>
     *     <li>Start the Network Server, but don't listen yet</li>
     *     <li>Call initialize() on all Mods</li>
     * </ol>
     */
    @Override
    public void initialize() {
        if (this.initialized) return;

        // Create ModManager
        this.modManager = new BaseModManager();

        // Load All Mods
        this.modFiles.forEach((s) -> modManager.load(s));

        // Call initialize() on all Mods
        this.modManager.getMods().forEach((m) -> m.initialiaze());

        this.initialized = true;
    }

    /**
     * Start the server<br>
     * <br>
     * Make sure the server is initialized, then loop tick() on the main thread
     */
    @Override
    public void start() {
        if (!this.initialized) return;
        if (this.running) return;
        this.running = true;

        do {
            this.tick();
        } while (this.running);
    }

    /**
     * This method is run every tick that the server is running<br>
     * <br>
     * General Algorithm:<br>
     * <ol>
     *     <li>TODO: Write General Argorithm</li>
     * </ol>
     */
    @Override
    public synchronized void tick() {
        this.destroy();
    }

    /**
     * Shuts the Server down
     */
    @Override
    public synchronized void destroy() {
        System.out.println("Shutting down...");
        this.running = false;
    }

    /**
     * Perform full shutdown on server
     *
     * If "restart.sh" exists in current directory, run on exit
     */
    @Override
    public void restart() {
        if (Paths.get(ServerBaseController.RESTART_SCRIPT).toFile().exists()) Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    new ProcessBuilder().command("sh", "restart.sh").directory(Paths.get(ServerBaseController.WORKING_DIRECTORY).toFile()).start();
                } catch (IOException ex) {
                    throw new ServerError("Error Running restart.sh", ex);
                }
            }
        });

        this.destroy();
        System.exit(0);
    }

    /**
     * Gets the current working directory
     *
     * @return The server root
     */
    @Override
    public String getWorkingDirectory() {
        return ServerBaseController.WORKING_DIRECTORY;
    }

    /**
     * Gets the current version of this server
     *
     * @return The current server version
     */
    @Override
    public Versions getVersion() {
        return Versions.SERVER_DEVL_0_0_1;
    }

    /**
     * Method called from bootstrap to enter the default server controller<br>
     * Parses arguments, and calls out to initilize() in a new instance
     *
     * @param args Arguments to the server itself (constructed in Bootstrap)
     */
    public static void entry(Map<String, Object> args) {
        System.out.println("Welcome to serverland!");
        ServerBaseController instance = new ServerBaseController();
        instance.modFiles = (List<String>)args.get("mods");

        if (instance.modFiles == null) throw new ServerError("This program is not meant to be run directly!");

        // Start the Server
        instance.initialize();
        instance.start();
        System.exit(0);

        // If we get to here, there was an error
        throw new ServerError();
    }
}

