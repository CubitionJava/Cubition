package net.cubition.bootstrap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

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
     * The download repository for the resource. This can also link to a local file.
     */
    private String source;

    /**
     * The local copy of this resource, including where it is stored.
     */
    private transient String localPath;

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
    public Resource(String name, String author, String version, URL source) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.source = source != null ? source.toString() : null;
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
    public URL getSource() throws MalformedURLException {
        if (this.source == null) {
            try {
                return new URL(Bootstrap.DEFAULT_RESOURCE_SERVER);
            } catch (MalformedURLException ex) {
                return null;
            }
        }

        return new URL(this.source);
    }

    /**
     * Check if this Resource exists locally
     *
     * @return Whether of not this resource needs to be downloaded
     */
    public boolean existsLocally() {
        return this.localPath != null && new File(this.localPath).exists();
    }

    /**
     * Downloads a local copy of this Resource
     *
     * @return If the operation was successful or not
     */
    public boolean downloadLocalCopy() throws IOException {
        // Check if we are using a local file
        if (source != null && source.startsWith("file://")
                && (source.toLowerCase().endsWith(".jar") || source.toLowerCase().endsWith(".zip"))) {
            // We are, use the raw source instead
            LOG.warn("Using local files for dependencies isn't recommended @ " + toString());

            localPath = source.substring("file://".length());
            return true;
        }

        // If the file already exists, ignore it
        if (this.existsLocally()) {
            return true;
        }

        // Build local path then
        this.localPath = new StringBuilder()
                .append("mods").append(File.separator)
                .append(author).append(File.separator)
                .append(name).append(File.separator)
                .append(name).append("_").append(version)
                .toString();

        // Check the parent folders for the localpath exist
        File localPathFolderDir = new File(this.localPath).getParentFile();
        if (!localPathFolderDir.exists()) {
            // Create some folders
            if (!localPathFolderDir.mkdirs()) {
                LOG.error("Failed to create directory structure @ " + toString());
                return false;
            }
        }

        // Make sure the remote source is a repo, not a file.
        if (source != null && (source.toLowerCase().endsWith(".jar") || source.toLowerCase().endsWith(".zip"))) {
            LOG.warn("Directly using files without a repo isn't recommended @ " + toString());

            // Download it
            String extension = source.toLowerCase().endsWith(".jar") ? ".jar" : ".zip";

            this.localPath += extension;

            // Do it
            try (FileOutputStream localOut = new FileOutputStream(this.localPath);
                 InputStream content = new BufferedInputStream(new URL(source).openStream())) {
                IOUtils.copy(content, localOut);
            }

            return true;
        }

        // Alright. Java URLs are dumb, and only accept absolute URLs. If the repo is local, we need to fix this.
        if (source != null && source.startsWith("file://")) {
            // Cool. Fix it up
            source = new File(source.substring("file://".length())).toURI().toURL().toString();
        }

        // Currently, we don't know what kind of file we are talking about. Lets grab this information first.
        // Build the remote path - we generate this on the fly.
        String remotePath = source;

        // If it is null, it is from the default repo
        if (remotePath == null) {
            remotePath = Bootstrap.DEFAULT_RESOURCE_SERVER;
        }

        // Make sure it ends with a /
        if (!source.endsWith("/")) {
            remotePath += "/";
        }

        remotePath = new StringBuilder()
                .append(remotePath)
                .append(author).append("/")
                .append(name).append("/")
                .append(name).append("_").append(version)
                .toString();

        // Open a basic stream, if possible
        URL jsonURL = new URL(remotePath + ".json");
        LOG.debug("Grabbing " + jsonURL.toString());

        try (InputStream jsonIn = new BufferedInputStream(jsonURL.openStream());
             OutputStream jsonOut = new FileOutputStream(this.localPath + ".json")) {
            IOUtils.copy(jsonIn, jsonOut);
        }

        // Parse our new found JSON file
        String contents;
        try (InputStream jsonIn = new FileInputStream(this.localPath + ".json")) {
            contents = IOUtils.toString(jsonIn);
        }

        JsonObject description = new Gson().fromJson(contents, JsonObject.class);

        // Grab the extension from this description
        String extension = description.has("type") ? description.get("type").getAsString() : "jar";

        // Download the actual Resource now we have the info we need
        this.localPath += "." + extension;
        remotePath += "." + extension;

        if (Files.exists(Paths.get(localPath))) return true;

        LOG.debug("Built final local destination for resource "
                + (author + ":" + name + ":" + version) + ": " + this.localPath);

        try (FileOutputStream localOut = new FileOutputStream(this.localPath);
             InputStream content = new BufferedInputStream(new URL(remotePath).openStream())) {
            IOUtils.copy(content, localOut);
        }

        // Yay! I did it mom!
        return true;
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
