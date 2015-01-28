package net.cubition.api.mod;

import java.util.ArrayList;
import java.util.List;

public class BaseModManager implements ModManager {
    private List<Mod> mods = new ArrayList<>();

    @Override
    public List<Mod> getMods() {
        return mods;
    }

    @Override
    public Mod load(String mod) {
        return null;
    }

}
