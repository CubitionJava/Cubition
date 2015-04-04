package net.cubition.core.entity;

import net.cubition.api.entity.EntityBase;
import net.cubition.api.item.ItemStack;
import net.cubition.api.world.Location;

public class EntityItem extends EntityBase {

	/**
	 * The ItemStack this item entity holds
	 */
	private ItemStack stack;

	/**
	 * Whether entities are able to pickup this item
	 */
	private boolean uppickable = true;

	/**
	 * Spawns a new EntityItem on with the given ItemStack on the given location
	 * 
	 * @param stack
	 *            The ItemStack this EntityItem holds
	 * @param location
	 *            The location this EntityItem will spawn
	 */
	public EntityItem(ItemStack stack, Location location) {
		super(location);
		this.stack = stack;
	}

	/**
	 * Returns the ItemStack this EntityItem holds
	 * 
	 * @return the ItemStack
	 */
	public ItemStack getItemStack() {
		return stack;
	}

	public boolean isUpPickable() {
		return this.uppickable;
	}

}
