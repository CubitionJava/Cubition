package net.cubition.api.container;

import net.cubition.api.item.ItemStack;

public interface Container {

	/**
	 * Returns the ItemStack at point x-y in the container<br />
	 * <b>TIP</b>: First check if that point exists using x <= containerWidth ()
	 * and y <= containerHeight ()
	 * 
	 * @param x The x-point in the container
	 * @param y The y-point in the container
	 * @return the ItemStack in that field
	 * @throws NoSuchFieldException when there is no field at x-y
	 */
	public ItemStack getItemStackAt (int x, int y) throws NoSuchFieldException;
	
	/**
	 * Useful for checking if a certain field is in range of the Container
	 * @return the width of the container
	 */
	public int containerWidth ();
	
	/**
	 * Useful for checking if a certain field is in range of the Container
	 * @return the height  of the container
	 */
	public int containerHeight ();
	
	/**
	 * Checks whether there is an item on the given x-y field
	 * @param x The x-position of the field
	 * @param y The y-position of the field
	 * @return whether there is one or not
	 */
	public boolean hasItemOn (int x, int y);
	
}
