package net.cubition.api.entity;

import net.cubition.api.world.Location;

public class CreatureBase extends EntityBase
{

	/**
	 * The amount of health this creature currently has
	 */
	private int health;
	
	/**
	 * The amount of health this creature can have at maximum
	 */
	private int maxHealth;
	
	public CreatureBase(Location location) {
		super(location);
	}
	
	public int getHealth ()
	{
		return health;
	}
	
	public int getMaxHealth ()
	{
		return maxHealth;
	}

	public void setHealth (int health)
	{
		this.health = Math.min (health, maxHealth);
	}
	
}
