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
     * The main
     */
    private String main;
    private Resource []dependencies;

    public static final class Resource
    {
        private String name;
        private String author;
        private String version;
        private String source;
    }
}
