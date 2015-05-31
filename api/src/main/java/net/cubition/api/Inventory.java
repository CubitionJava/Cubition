package net.cubition.api;

public interface Inventory {

	public ItemStack[] getItems ();
	public void addItems (ItemStack... items);
	public void removeItems (ItemStack... items);
	
}
