package net.cubition.api.mod;

public class ModResourceFile
{
    private String name;
    private String type;
    private String author;
    private String version;
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
