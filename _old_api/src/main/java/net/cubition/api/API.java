package net.cubition.api;

import net.cubition.api.block.BlockTypeRegister;
import net.cubition.api.item.ItemRegister;
import net.cubition.api.tool.ToolTypeRegister;
import net.cubition.api.vc.Versions;
import net.cubition.api.weapon.WeaponTypeRegister;

/**
 * API stores information about the currently running API
 */
public class API {

	private static BlockTypeRegister blockTypeRegister = new BlockTypeRegister();
	private static ItemRegister itemRegister = new ItemRegister();
	private static ToolTypeRegister toolTypeRegister = new ToolTypeRegister();
	private static WeaponTypeRegister weaponTypeRegister = new WeaponTypeRegister();

	/**
	 * Gets the name of the current API Version
	 *
	 * @return The name of the current API Version
	 */
	public static String getVersionName() {
		return "CAPI 0.1D";
	}

	/**
	 * Gets the build of the current API Version
	 *
	 * @return The build of the current API Verion
	 */
	public static long getVersionBuild() {
		return 0x8105CCE;
	}

	/**
	 * Checks if a given server version is supported by this API
	 *
	 * @param version
	 *            The Server Version to check
	 * @return Whether or not this is supported
	 */
	public static boolean supportsServerBuild(Versions version) {
		switch (version) {

		case CLIENT_DEVL_0_0_1:
			return true;

		default:
			return false;

		}
	}

	/**
	 * Provides the BlockType register
	 * 
	 * @return the register
	 */
	public static BlockTypeRegister getBlockTypeRegister() {
		return blockTypeRegister;
	}

	/**
	 * Provides the Item register
	 * 
	 * @return the register
	 */
	public static ItemRegister getItemRegister() {
		return itemRegister;
	}

	/**
	 * Provides the ToolType register
	 * 
	 * @return the register
	 */
	public static ToolTypeRegister getToolTypeRegister() {
		return toolTypeRegister;
	}

	/**
	 * Provides the WeaponType register
	 *
	 * @return the register
	 */
	public static WeaponTypeRegister getWeaponTypeRegister() {
		return weaponTypeRegister;
	}

	/**
	 * Lock Instances
	 */
	private API() {
	}
}
