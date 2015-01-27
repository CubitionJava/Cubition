package net.cubition.api.mod;

import java.io.File;

/**
 * A Mod is the main way for developers to interface with the server.<br>
 * Mods are loaded at startup or by command, and can add anything to the server through the Modding API
 */
public interface Mod {
    /**
     * Gets the fully-qualified name for the custom ServerController for this mod
     *
     * @return the custom ServerController for this mod, or null
     */
    public String customController();

    /**
     * Gets the data folder for this mod<br>
     * should exist after first call
     *
     * @return This Mod's Data Folder
     */
    public File getDataFolder();

    /**
     * Initializes the Mod's state
     */
    public void initialiaze();
}
