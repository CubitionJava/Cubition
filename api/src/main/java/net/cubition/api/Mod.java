package net.cubition.api;

import java.util.HashMap;
interface Mod {
	public File getDataFolder ( );
	public String getName ( );
	public String getAuthor ( );
	public void initialize ( );
	public void deinitialize ( );
}
