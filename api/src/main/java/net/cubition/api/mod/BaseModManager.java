package net.cubition.api.mod;

import java.util.ArrayList;
import java.util.List;

public class BaseModManager implements ModManager {
    private List<Mod> mods = new ArrayList<>();

    /**
     * Gets all loaded mods
     *
     * @return All loaded mods
     */
    @Override
    public List<Mod> getMods() {
        return mods;
    }

    /**
     * Loads a mod
     *
     * @param mod The path to the mod's file
     * @return null, for now
     */
    @Override
    public Mod load(String mod) {
        return null;
    }
}
