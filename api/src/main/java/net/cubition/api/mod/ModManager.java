package net.cubition.api.mod;

import java.util.List;

/**
 * A ModManager handles the loading and unloading of Mods into the server
 */
public interface ModManager
{
    /**
     * Gets the list of mods currently loaded by this manager
     *
     * @return the list of mods currently loaded by this manager (non-null)
     */
    public List<Mod> getMods();

    /**
     * Loads the mod from the specified path
     *
     * @param mod The path to the modfile
     * @return The loaded mod
     */
    public Mod load(String mod);
}
