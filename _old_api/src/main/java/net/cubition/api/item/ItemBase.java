package net.cubition.api.item;

public abstract class ItemBase {

	/**
	 * Returns whether the item should be destroyed on explosion or on
	 * interaction with destroying-liquids
	 * 
	 * @return true or false
	 */
	public boolean destroyable() {
		return true;
	}

	/**
	 * Returns the translatable name of this item
	 * 
	 * @return The translatable name
	 */
	public abstract String getTranslatableName();

	/**
	 * Returns the translatable name of this item with a certain damage-value
	 * 
	 * @param damage
	 *            The damage-value
	 * @return The translatable name for this damage value if one exists, else
	 *         the default transatableName
	 */
	public String getTranslatableName(byte damage) {
		return this.getTranslatableName();
	}

	/**
	 * The maximum amount a ItemStack of this type can contain
	 * 
	 * @return the size
	 */
	public int maxStackSize() {
		return 64;
	}

}
