package net.cubition.api;

public interface TranslatableResource {

	/**
	 * Returns the translatable name of this resource
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
	public String getTranslatableName(byte damage);

}
