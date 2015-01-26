package net.cubition.bootstrap;

import java.nio.file.Paths;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A resource is a mod, library, executable, texture pack, or other content that can be pulled at runtime.
 *
 * This is completely serializable, and can be thrown around. This is also compatible with GSON.
 *
 * {@link com.google.gson.Gson}
 */
public class Resource implements Serializable {
    /**
     * Provides a comment in the JSON file, helping the user with this Resource.
     */
    private String _c = "This is a Resource. More info about me can be found at http://cubition.net/...";

    /**
     * The name of the resource defines the actual resource name, and is used in downloading resources.
     */
    private final String name;

    /**
     * The author is the one who created the resource, and therefore defines what namespace this resource is under.
     */
    private final String author;

    /**
     * The version of the resource. 'LATEST' means that the newest resource is used.
     */
    private final String version;

    /**
     * The download repository for the resource
     */
    private final String source;

    /**
     * The local copy of this resource
     */
    private final transient String localPath;

    /**
     * Creates a new Resource representation containing data useful for fetching the mod, as well as version control.
     *
     * @param name The name of this resource
     * @param author The author of this resource
     * @param version The version of this resource
     */
    public Resource(String name, String author, String version) {
        this(name, author, version, null);
    }

    /**
     * Creates a new Resource representation containing data useful for fetching the mod, as well as version control.
     *
     * @param name The name of this resource
     * @param author The author of this resource
     * @param version The version of this resource
     * @param source The custom repo of this resource
     */
    public Resource(String name, String author, String version, String source) {
        this.name = name;
        this.author = author;
        this.version = version;

        // Only store if custom
        if (source != null && !source.equalsIgnoreCase(Bootstrap.DEFAULT_RESOURCE_SERVER)) {
            this.source = source;
        } else {
            this.source = null;
        }

        // Derive local path
        String separator = File.pathSeparator;
        this.localPath = "mods" + separator + author + separator + name + separator + version + ".jar";
    }

    /**
     * Returns the name of this resource. This is also the name used in polling for resources.
     *
     * @return The name of this resource.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the author of this resource. This also defines what namespace this resource is under.
     *
     * @return The author of this resource.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the version of this resource.
     *
     * @return The version of this resource.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the repository for this resource
     *
     * @return The repository for this resource
     */
    public String getSource() {
        if (this.source == null) {
            return Bootstrap.DEFAULT_RESOURCE_SERVER;
        }
        return this.source;
    }

    /**
     * Check if this Resource exists locally
     *
     * @return Whether of not this resource needs to be downloaded
     */
    public boolean existsLocally() {
        return getLocalFile() != null && getLocalFile().exists();
    }

    /**
     * Downloads a local copy of this Resource
     *
     * @return If the operation was successful or not
     */
    public boolean downloadLocalCopy() {
        // TODO: Implement this
        return true;
    }

    /**
     * Returns the local copy of this Resource, if it has been downloaded.
     *
     * @return The local copy of this resource, or null if it doesn't exist locally yet.
     */
    public File getLocalFile() {
        if (!getSource().equalsIgnoreCase(Bootstrap.DEFAULT_RESOURCE_SERVER) && getSource().startsWith("file://")) {
            System.out.println("WARNING: Using local sources is bad practice (" + getSource() + ")");
            return new File(this.source.substring("file://".length()));
        }

        if (this.localPath == null || !this.existsLocally()) {
            return null;
        }

        return new File(this.localPath);
    }

    /**
     * Poll dependencies for this Resource.
     *
     * @return The dependencies for this Resource, if any.
     */
    public Resource[] pollDependencies() {
        // TODO: This is a placeholder. Poll dependencies here.
        return new Resource[0];
    }

    /**
     * Poll dependencies for this Resource, grabbing sub-dependencies as required.
     *
     * @return The recursive dependencies for this Resource, if any.
     */
    public Resource[] pollDependenciesRecursively() {
        ArrayList<Resource> resources = new ArrayList<>();

        for (Resource resource : pollDependencies()) {
            resources.add(resource);
            Collections.addAll(resources, resource.pollDependenciesRecursively());
        }

        return resources.toArray(new Resource[resources.size()]);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}