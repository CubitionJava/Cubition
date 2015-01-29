package net.cubition.api.item;

public interface ItemBase {

	/**
	 * Returns whether the item should be destroyed on explosion
	 * or on interaction with destroying-liquids
	 * 
	 * @return true or false
	 */
	public boolean destroyOnExplode ();
	
	/**
	 * Returns the translatable name of this item
	 * @return The translatable name
	 */
	public String getTranslatableName ();
	
	/**
	 * Returns the translatable name of this item with a certain damage-value
	 * 
	 * @param damage The damage-value
	 * @return The translatable name
	 */
	public String getTranslatableName (byte damage);
	
}
