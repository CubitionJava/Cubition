package net.cubition.api.mod;

public class ModResourceFile
{
    /**
     * The name of this mod
     */
    private String name;

    /**
     * The type of this Resource<br>
     * should be 'mod' for all mods
     */
    private String type;

    /**
     * The author of this mod
     */
    private String author;

    /**
     * The current version of this mod
     */
    private String version;

    /**
     * The main class name or entry point for this mod
     */
    private String main;

    /**
     * The dependencies for this mod
     */
    private Dependency []dependencies;

    /**
     * A dependency for a mod
     */
    public static final class Dependency
    {
        /**
         * The name
         */
        private String name;

        /**
         * The author
         */
        private String author;

        /**
         * The version
         */
        private String version;

        /**
         * The source
         */
        private String source;
    }
}
