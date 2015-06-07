package net.cubition.api;

public interface Entity {
	Location getLocation();
	void teleport(Entity e);
	void teleport(Location loc);
	void remove();
}
