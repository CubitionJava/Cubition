package net.cubition.api.entity;

import net.cubition.api.world.Location;

public class EntityBase {

	private Location currentLocation;
	
	public int health;
	public int maxhealth;
	
	/**
	 * Spawn a new Entity of this type
	 * 
	 * @param location the place where to spawn the new entity
	 */
	public EntityBase (Location location)
	{
		this.currentLocation = location;
	}
	
	public Location getLocation ()
	{
		return currentLocation;
	}

	public void teleport (Location location)
	{
		this.currentLocation = location;
	}
	
	public void teleport (EntityBase entity)
	{
		this.currentLocation = entity.getLocation();
	}
	
}
