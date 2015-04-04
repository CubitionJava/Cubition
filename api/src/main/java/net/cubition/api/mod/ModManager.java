package net.cubition.api.mod;

import net.cubition.api.vc.Availability;
import net.cubition.api.vc.Versions;

import java.util.List;

/**
 * A ModManager handles the loading and unloading of Mods into the server
 */
public interface ModManager {
	/**
	 * Gets the list of mods currently loaded by this manager
	 *
	 * @return the list of mods currently loaded by this manager (non-null)
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
	public List<Mod> getMods();

	/**
	 * Loads the mod from the specified path
	 *
	 * @param mod
	 *            The path to the modfile
	 * @return The loaded mod
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_DEVL_0_0_1)
	public Mod load(String mod);
}
