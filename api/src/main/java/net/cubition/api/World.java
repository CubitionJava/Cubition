package net.cubition.api;

public interface World {
	Block getBlockAt(Location loc);
	Chunk getChunkAt(Location loc);
	Entity[] getEntities();
	void setBlockDamageAt(Location location, byte damage);
	void setBlockTypeAt(Location location, Material m);
	Material getBlockTypeAt(Location location);
	byte getBlockDamageAt(Location location);
}
