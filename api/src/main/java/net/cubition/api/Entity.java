package net.cubition.api;

public interface Entity {
	public Location getLocation ( );
	public void teleport (Entity e);
	public void teleport (Location loc);
	public void remove ();
}
