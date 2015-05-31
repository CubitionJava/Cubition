package net.cubition.api;

import java.util.HashMap;
interface ModLoader {
	public Mod getMod (File mod);
	public String[] availableMods ( );
}
