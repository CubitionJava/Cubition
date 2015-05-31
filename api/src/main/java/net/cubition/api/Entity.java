package net.cubition.api;

import java.util.HashMap;
interface Entity {
	public void getLocation ( );
	public void teleport (Entity e);
	public void teleport (Location loc);
	public void remove ( );
}
