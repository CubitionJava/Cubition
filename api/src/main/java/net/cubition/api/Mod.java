package net.cubition.api;

import java.io.File;
public interface Mod {
	File getDataFolder();
	String getName();
	String getAuthor();
	void initialize();
	void deinitialize();
}
