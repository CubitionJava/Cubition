package net.cubition.bootstrap.config;

import net.cubition.bootstrap.Bootstrap;
import net.cubition.bootstrap.Resource;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The LaunchConfig defines a configuration of a server/client/other runnable service. This allows users to
 * import mods, configure the server/client, and much more.
 *
 * Defaults are located in a new LaunchConfig, for ease of access.
 *
 * This is completely serializable, and can be thrown around. This is also compatible with GSON.
 *
 * {@link com.google.gson.Gson}
 */
public class LaunchConfig implements Serializable {
    /**
     * Provides a comment in the JSON file, helping the user with this LaunchConfig.
     */
    private String _c = "This is the main configuration for the Bootstrap. " +
            "More info about me can be found at http://cubition.net/...";

    /**
     * The 'type' defines what main executable this configuration is associated with.
     *
     * This resource has to point to a runnable .jar with a endpoint located (by default)
     * at net.cubition.server.CubitionBaseServer or net.cubition.client.CubitionBaseClient. This can
     * be modified within the Resource itself, via its mod.json configuration.
     *
     * Dependencies are automatically polled for this executable, if it includes such definitions in a .json
     * file with the same filename and location.
     */
    private Resource type = new Resource("server", "cubition", "jar", Bootstrap.VERSION);

    /**
     * The 'mods' defines what mods are to be imported at runtime. Whether these are full blown games,
     * mini tweak mods, or whatever, put them here.
     *
     * Dependencies are automatically polled for these mods, if they include such definitions in a .json
     * file with the same filename and location.
     */
    private Resource[] mods = new Resource[] {
            new Resource("core", "cubition", "mod", Bootstrap.VERSION)
    };

    /**
     * Returns the executable resource that this LaunchConfig defines.
     *
     * This is directly fed from user configuration, so results should NOT be trusted.
     *
     * @return A Resource, that should be executable.
     */
    public Resource getExecutable() {
        return type;
    }

    /**
     * Returns the directly defined mods for this LaunchConfig. This does not directly include
     * dependencies, and this should be worked out via {@link net.cubition.bootstrap.Resource#pollDependencies}.
     *
     * This is directly fed from user configuration, so results should NOT be trusted.
     *
     * @return An array of Resources.
     */
    public Resource[] getMods() {
        return mods;
    }

    @Override
    public String toString() {
        return "LaunchConfig{" +
                "type=" + type +
                ", mods=" + Arrays.toString(mods) +
                '}';
    }
}
