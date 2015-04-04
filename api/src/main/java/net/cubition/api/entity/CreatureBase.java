package net.cubition.api.entity;

import net.cubition.api.world.Location;

public abstract class CreatureBase extends EntityBase {

	/**
	 * The amount of health this Creature currently has.
	 */
	private int health;

	/**
	 * The amount of health this Creature can have at maximum.
	 */
	private int maxHealth;

	/**
	 * Spawn a new Creature of this type
	 *
	 * @param location
	 *            the place where to spawn the new entity
	 */
	public CreatureBase(Location location) {
		super(location);
	}

	/**
	 * Returns the current health of this Creature.
	 *
	 * @return The health value of this Creature.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Returns the maximum health that this Creature can have.
	 *
	 * @return The maximum health value
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Sets this Creatures health. This value cannot go above the maximum health
	 * for this Creature.
	 *
	 * @param health
	 *            The new health value
	 */
	public void setHealth(int health) {
		setHealth(health, false);
	}

	/**
	 * Sets this Creatures health, with optional overloading.
	 *
	 * @param health
	 *            The new health value
	 * @param overloaded
	 *            If true, health can exceed this Creatures maximum health
	 */
	public void setHealth(int health, boolean overloaded) {
		this.health = overloaded ? health : Math.min(health, maxHealth);
	}

}
