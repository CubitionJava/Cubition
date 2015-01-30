package net.cubition.api.mod;

import java.net.MalformedURLException;
import java.net.URL;

import net.cubition.api.vc.Availability;
import net.cubition.api.vc.Versions;

public class ModResourceFile
{
	/**
	 * Initiates a new ModResourceFile
	 * 
	 * @param name The name of the Mod
	 * @param author The author of the Mod
	 * @param version The version of the Mod
	 * @param main The Main class of the Mod
	 * @param dependencies An array of dependencies
	 */
    public ModResourceFile (String name, String author, String version, Class<Mod> main, Dependency[] dependencies)
    {
    	this.name = name;
    	this.author = author;
    	this.version = version;
    	this.main = main.getName();
    	this.dependencies = dependencies;
    }
    
    /**
	 * Initiates a new ModResourceFile
	 * 
	 * @param name The name of the Mod
	 * @param author The author of the Mod
	 * @param version The version of the Mod
	 * @param main The Main class of the Mod
	 * @param dependencies An array of dependencies
	 */
    public ModResourceFile (String name, String author, String version, String main, Dependency[] dependencies)
    {
    	this.name = name;
    	this.author = author;
    	this.version = version;
    	this.main = main;
    	this.dependencies = dependencies;
    }
    
    /**
     * NOT YET IMPLEMENTED!!
     * Initiates a new ModRersouceFile from from the URL
     * 
     * @param resourceFileURL The URL of the resource file
     */
    @Availability (client = Versions.CLIENT_NEVER, server = Versions.SERVER_NEVER)
    public ModResourceFile (URL resourceFileURL)
    {
    	throw new UnsupportedOperationException ();
    }
	
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
        
        /**
         * The type of this Resource<br>
         * should be 'mod' for all mods
         */
        private String type;
        
        public Dependency (String name, String author, String version, String source, String type)
        {
        	this.name = name;
        	this.author = author;
        	this.version = version;
        	this.source = source;
        	this.type = type;
        }
        
        public String getName () {
        	return name;
        }
        
        public String getAuthor () {
        	return author;
        }
        
        public String getVersion () {
        	return version;
        }
        
        public String getSource () {
        	return source;
        }
        
        public String getType () {
        	return type;
        }
        
        public URL getDownloadURL () throws MalformedURLException {
        	return new URL ((source.endsWith("/") ? source : source + "/") + author + "/" +
        			name + "/" + name + "_" + version +
        			(type.startsWith(".") ? type : "." + type));
        }
    }
    
    public String getName () {
    	return name;
    }
    
    public String getVersion () {
    	return version;
    }
    
    public String getAuthor () {
    	return author;
    }
    
    public String getMain () {
    	return main;
    }
    
    public String getType () {
    	return type;
    }
    
    @SuppressWarnings("unchecked")
	public Class<Mod> getMainClass () throws ClassNotFoundException, ClassCastException {
			Class<?> clazz = Class.forName(main);
			if (clazz.isAssignableFrom (Mod.class))
				return (Class<Mod>) clazz;
			
			throw new ClassCastException ();
    }
    
    public Dependency[] getDependencies () {
    	return dependencies;
    }
    
}
