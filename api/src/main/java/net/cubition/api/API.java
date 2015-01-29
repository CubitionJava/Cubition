package net.cubition.api;

import net.cubition.api.vc.Versions;

/**
 * API stores information about the currently running API
 */
public class API {
    /**
     * Gets the name of the current API Version
     *
     * @return The name of the current API Version
     */
    public static String getVersionName() {
        return "CAPI 0.1D";
    }

    /**
     * Gets the build of the current API Version
     *
     * @return The build of the current API Verion
     */
    public static long getVersionBuild() {
        return 0x8105CCE;
    }

    /**
     * Checks if a given server version is supported by this API
     *
     * @param version The Server Version to check
     * @return Whether or not this is supported
     */
    public static boolean supportsServerBuild(Versions version) {
        if (version == Versions.SERVER_DEVL_0_0_1) return true;
        else return false;
    }

    /**
     * Lock Instances
     */
    private API() {}
}
