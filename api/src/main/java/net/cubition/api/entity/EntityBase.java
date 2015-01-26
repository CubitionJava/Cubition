package net.cubition.api.entity;

import net.cubition.api.world.Location;

public abstract class EntityBase {

    /**
     * The current location of this entity
     *
     * @getter {@link net.cubition.api.entity.EntityBase.getLocation}
     * @setter {@link net.cubition.api.entity.EntityBase.teleport(Location); net.cubition.entity.EntityBase.teleport(EntityBase)}
     */
    private Location currentLocation;

    /**
     * Spawn a new Entity of this type
     *
     * @param location the place where to spawn the new entity
     */
    public EntityBase(Location location) {
        this.currentLocation = location;
    }

    /**
     * Returns the current location of this entity
     *
     * @return the current location
     */
    public Location getLocation() {
        return currentLocation;
    }

    /**
     * Teleports this entity to the given location
     *
     * @param location the location to teleport to
     */
    public void teleport(Location location) {
        this.currentLocation = location;
    }

    /**
     * Teleports this entity to the given other entity
     *
     * @param entity the entity to teleport this entity to
     */
    public void teleport(EntityBase entity) {
        this.currentLocation = entity.getLocation();
    }

}
