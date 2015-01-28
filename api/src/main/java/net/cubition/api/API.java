package net.cubition.api;

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
     * Lock Instances
     */
    private API() {}
}
