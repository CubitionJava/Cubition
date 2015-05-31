package net.cubition.api;

import java.io.File;

public interface ModLoader {
	public Mod getMod (File mod);
	public String[] availableMods ( );
}
