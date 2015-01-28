package net.cubition.api.mod;

import net.cubition.api.vc.Availability;
import net.cubition.api.vc.Versions;

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
    @Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
    public String customController();

    /**
     * Gets the data folder for this mod<br>
     * should exist after first call
     *
     * @return This Mod's Data Folder
     */
    @Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
    public File getDataFolder();

    /**
     * Gets the resource file for this mod<br>
     * The file is read before the mod is loaded
     *
     * @return The ResourceFile for this mod
     */
    @Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
    public ModResourceFile getResourceFile();

    /**
     * Initializes the Mod's state
     */
    @Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
    public void initialiaze();
}
