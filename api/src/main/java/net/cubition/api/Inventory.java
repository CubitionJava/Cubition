package net.cubition.api;

public interface Inventory {

	ItemStack[] getItems();
	void addItems(ItemStack... items);
	void removeItems(ItemStack... items);
	void setItem(int slot, ItemStack item);
}
