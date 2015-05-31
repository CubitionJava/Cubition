package net.cubition.api;

import java.io.File;
import java.util.ArrayList;

public class ModManager {
	private static ModLoader loader;
	private static ArrayList<Mod> mods = new ArrayList<Mod>();
	private static File modFolder;
	
	// Lock constructor
	private ModManager () {}
	
	public static void setModLoader (ModLoader loader) throws Exception {
		if (loader == null)
			throw new Exception ("Can not set modloader to null");
		
		ModManager.loader = loader;
	}
	
	public static File getModFolder () {
		return modFolder;
	}
	
	public static Mod loadMod (File mod) throws Exception {
		if (loader == null)
			throw new Exception ("No modloader set");
		
		if (!mod.exists())
			throw new Exception ("This mod file does not exist");
		
		return loader.getMod(mod);
	}
	
	public static void unloadMod (Mod mod) throws Exception {
		if (mod == null)
			throw new Exception ("Cannot unload null mod");
		
		if (!mods.contains (mod))
			throw new Exception ("Mod " + mod.getName() + " (" + mod.getAuthor() + ") cannot be unloaded as it is not yet loaded.");
		
		mod.deinitialize();
		mods.remove (mod);
	}
	
	public static Mod[] mods ( ) {
		return mods.toArray(new Mod[0]);
	}

	public static void unloadAll() {
		try {
			for (Mod mod: mods)
				unloadMod (mod);
		} catch (Exception e) {
			// TODO [ERROR] Could not unload mod x
		}
		
		mods = null;
		loader = null;
	}

	public static void setModFolder(File folder) throws Exception {
		if (folder == null)
			throw new Exception ("Could not set the mod folder to null.");
		
		if (!folder.isDirectory())
			throw new Exception ("The given file is not a folder.");
		
		modFolder = folder;
	}
}
