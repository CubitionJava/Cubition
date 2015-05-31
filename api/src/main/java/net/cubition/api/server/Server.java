package net.cubition.api.server;

import java.util.HashMap;
interface Server {
	public Player[] getOnlinePlayers ( );
	public void stop ( );
	public void stop (String msg);
	public Player getPlayer (int id);
	public Player getPlayer (String name);
}
