package net.cubition.api;

import java.util.HashMap;
interface World {
	public Block getBlockAt (Location loc);
	public Chunk getChunkAt (Location loc);
	public Entity[] getEntities ( );
}
