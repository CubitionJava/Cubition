package net.cubition.api;

public class ItemStack {
	public Material item;
	public int amount;
	public byte damage;
	public ItemMeta data;

	public ItemStack (Material material) {
		this (material, 0, (byte) 0, new ItemMeta ());
	}
	
	public ItemStack (Material material, int amount) {
		this (material, amount, (byte) 0, new ItemMeta ());
	}
	
	public ItemStack (Material material, int amount, byte damage) {
		this (material, amount, damage, new ItemMeta ());
	}
	
	public ItemStack (Material material, int amount, ItemMeta itemMeta) {
		this (material, amount, (byte) 0, itemMeta);
	}
	
	public ItemStack (Material material, int amount, byte damage, ItemMeta itemMeta) {
		this.item = material;
		this.amount = amount;
		this.damage = damage;
		this.data = itemMeta;
	}

}
