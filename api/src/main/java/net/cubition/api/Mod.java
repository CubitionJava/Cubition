package net.cubition.api;

import java.io.File;
public interface Mod {
	public File getDataFolder ( );
	public String getName ( );
	public String getAuthor ( );
	public void initialize ( );
	public void deinitialize ( );
}
