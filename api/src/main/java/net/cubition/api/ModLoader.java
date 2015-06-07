package net.cubition.api;

import java.io.File;

public interface ModLoader {
	Mod getMod(File mod);
	String[] availableMods();
}
