package net.cubition.api;

public interface Entity {
	public void getLocation ( );
	public void teleport (Entity e);
	public void teleport (Location loc);
	public void remove ();
}
