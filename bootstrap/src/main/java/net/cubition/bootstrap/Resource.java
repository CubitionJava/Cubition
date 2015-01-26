package net.cubition.bootstrap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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
    private String name;

    /**
     * The author is the one who created the resource, and therefore defines what namespace this resource is under.
     */
    private String author;

    /**
     * The version of the resource. 'LATEST' means that the newest resource is used.
     */
    private String version;

    /**
     * The type of the resource. Used as the file's extension
     */
    private String type;

    /**
     * The download repository for the resource
     */
    private URL source;

    /**
     * The exact path to the resource
     * Excludes the type extension
     */
    private String path;

    /**
     * The local copy of this resource
     */
    private transient String localPath;

    /**
     * The remote path to this resource
     */
    private transient String remotePath;

    /**
     * Weather or not initialize() has been called yet
     */
    private transient boolean initialized = false;

    /**
     * Download a file from the source URL
     *
     * @param file the URL to download
     * @param output the output file
     * @return success
     */
    private static boolean downloadFile(URL file, String output, boolean force) {
        if (!force && Paths.get(output).toFile().exists()) return true;

        try {
            ReadableByteChannel rbc = Channels.newChannel(file.openStream());
            FileOutputStream fos = new FileOutputStream(output);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException ex) {
            return false;
        }

        return Paths.get(output).toFile().exists();
    }

    /**
     * Default Constructor for GSON
     */
    private Resource() {
        super();
    }

    /**
     * Creates a new Resource representation containing data useful for fetching the mod, as well as version control.
     *
     * @param name The name of this resource
     * @param author The author of this resource
     * @param type The type of this resource
     * @param version The version of this resource
     */
    public Resource(String name, String author, String type, String version) {
        this(name, author, type, version, null);
    }

    /**
     * Creates a new Resource representation containing data useful for fetching the mod, as well as version control.
     *
     * @param name The name of this resource
     * @param author The author of this resource
     * @param version The version of this resource
     * @param type The type of this resource
     * @param source The custom repo of this resource
     */
    public Resource(String name, String author, String type, String version, URL source) {
        this.type = type;
        this.name = name;
        this.author = author;
        this.version = version;
        this.source = source;

        this.initialize();
    }

    /**
     * Initializes this resource.<br>
     *     Called after Deserialization or from the constructor
     */
    private void initialize() {
        if (this.initialized) return;

        // Sanitize source Field
        if (this.source != null && !this.source.toString().equalsIgnoreCase(Bootstrap.DEFAULT_RESOURCE_SERVER)) {
            if (this.source.toString().startsWith("file://"))
                System.out.println("WARNING: Using local sources is bad practice (" + source + ")");
        } else {
            this.source = null;
        }

        if (this.path == null) {
            // Derive paths
            String separator = "/";
            this.localPath = "mods" + separator + author + separator + name + separator + name + "_" + version;
            this.remotePath = ((source == null) ? Bootstrap.DEFAULT_RESOURCE_SERVER : source) +
                    author + "/" + name + "/" + name + "_" + version;
        } else {
            this.localPath = this.path;
            this.remotePath = this.path;
        }

        this.initialized = true;
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
     * Returns the type of this resource
     *
     * @return The type of this resource
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the repository for this resource
     *
     * @return The repository for this resource
     */
    public URL getSource() {
        if (this.source == null) {
            try {
                return new URL(Bootstrap.DEFAULT_RESOURCE_SERVER);
            } catch (MalformedURLException ex) {
                return null;
            }
        }

        return this.source;
    }

    /**
     * Check if this Resource exists locally
     *
     * @return Whether of not this resource needs to be downloaded
     */
    public boolean existsLocally() {
        if (!this.initialized) this.initialize();

        return (Paths.get(this.localPath + "." + this.type).toFile().exists()) &&
               (Paths.get(this.localPath + ".json").toFile().exists());
    }

    /**
     * Downloads a local copy of this Resource
     *
     * @return If the operation was successful or not
     */
    public boolean downloadLocalCopy() {
        if (this.existsLocally()) return true;

        Paths.get(this.localPath + ".json").toFile().getAbsoluteFile().getParentFile().mkdirs();

        try {
            if (!Resource.downloadFile(new URL(this.remotePath + "." + this.type),  this.localPath + "." + this.type,  false)) return false;
            if (!Resource.downloadFile(new URL(this.remotePath + ".json"), this.localPath + ".json", false)) return false;
        } catch (MalformedURLException ex) {
            System.out.println("ERROR: Invalid URL " + this.remotePath + " for resource " + this.getName());
            return false;
        }

        return true;
    }

    public File getRemoteFile() {
        if (!this.initialized) this.initialize();

        return new File(this.remotePath + "." + this.type);
    }

    /**
     * Returns the local copy of this Resource, if it has been downloaded.
     *
     * @return The local copy of this resource, or null if it doesn't exist locally yet.
     */
    public File getLocalFile() {
        if (!this.existsLocally()) {
            return null;
        }

        return new File(this.localPath + "." + this.type);
    }

    /**
     * Poll dependencies for this Resource.
     *
     * @return The dependencies for this Resource, if any.
     */
    public Resource[] pollDependencies() {
        if (!this.initialized) this.initialize();

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
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
