package net.cubition.api.item;

public class ItemStack {

	/**
	 * The amount of items this stack contains
	 */
	private int amount;

	/**
	 * The damage value this item stack
	 */
	private byte damage;

	/**
	 * The item
	 */
	private ItemBase item;

	/**
	 * Constructs a new ItemStack
	 * 
	 * @param item
	 *            The Item
	 * @param amount
	 *            The amount of items this ItemStack contains
	 * @param damage
	 *            The damage value of the items
	 */
	public ItemStack(ItemBase item, int amount, byte damage) {
		this.item = item;
		this.amount = amount;
		this.damage = damage;
	}

	public ItemStack(ItemBase item) {
		this(item, 1, (byte) 0);
	}

	public ItemStack(ItemBase item, int amount) {
		this(item, amount, (byte) 0);
	}

	public ItemStack(ItemBase item, byte damage) {
		this(item, 1, damage);
	}

	public int getAmount() {
		return amount;
	}

	public byte getDamage() {
		return damage;
	}

	public ItemBase getItem() {
		return item;
	}
}
