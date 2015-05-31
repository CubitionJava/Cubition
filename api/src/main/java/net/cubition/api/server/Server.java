package net.cubition.api.server;

import net.cubition.api.Player;
import net.cubition.api.WorldLoader;

public abstract class Server {
	
	private static Server server = null;
	private static WorldLoader worldLoader;
	
	public static void setServer (Server s) throws Exception {
		if (server == null)
			server = s;
		else
			throw new Exception ("Could not set Server");
	}
	
	public static Server getServer ( ) {
		return server;
	}
	
	public static WorldLoader getWorldLoader ( ) {
		return worldLoader;
	}

	public static void setWorldLoader (WorldLoader loader) throws Exception {
		if (worldLoader == null)
			worldLoader = loader;
		else
			throw new Exception ("Could not set WorldLoader");
	}
	
	public abstract Player[] getOnlinePlayers ( );
	public abstract void start ();
	public abstract void stop ( );
	public abstract void stop (String msg);
	public abstract void restart ();
	public abstract Player getPlayer (int id);
	public abstract Player getPlayer (String name);

}
