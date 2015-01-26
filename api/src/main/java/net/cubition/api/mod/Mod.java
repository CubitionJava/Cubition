package net.cubition.api.mod;

/**
 * A Mod is the main way for developers to interface with the server.<br>
 * Mods are loaded at startup or by command, and can add anything to the server through the Modding API
 */
public interface Mod {
    /**
     * Gets the custom ServerController for this mod
     *
     * @return the custom ServerController for this mod, or null
     */
    public String customController();

    /**
     * Initializes the Mod's state
     */
    public void initiliaze();
}
