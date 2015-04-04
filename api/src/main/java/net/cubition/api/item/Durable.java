package net.cubition.api.item;

public interface Durable {

	/**
	 * Gets the amount of durability-points of this item
	 * 
	 * @return the amount of durability
	 */
	public int getDurability();

	/**
	 * Gets the maximum durability-points of this item
	 * 
	 * @return the maximum durability
	 */
	public int getMaximumDurability();

}
