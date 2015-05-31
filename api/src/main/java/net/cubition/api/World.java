package net.cubition.api;

public interface World {
	public Block getBlockAt (Location loc);
	public Chunk getChunkAt (Location loc);
	public Entity[] getEntities ( );
	public void setBlockDamageAt(Location location, byte damage);
	public void setBlockTypeAt(Location location, Material m);
	public Material getBlockTypeAt(Location location);
	public byte getBlockDamageAt(Location location);
}
