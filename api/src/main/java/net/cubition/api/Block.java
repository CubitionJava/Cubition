package net.cubition.api;

public class Block {
	private Location location;
	private World world;
	
	public Block (Location loc) {
		this.location = loc;
		this.world = loc.getWorld();
	}
	
	public Location getLocation ( ) {
		return location;
	}
	
	public Material getType () {
		return world.getBlockTypeAt (location);
	}
	
	public byte getDamage () {
		return world.getBlockDamageAt (location);
	}
	
	public Chunk getChunk () {
		return world.getChunkAt(location);
	}
	
	public void setType (Material m) {
		world.setBlockTypeAt(location, m);
	}
	
	public void setDamage (byte damage) {
		world.setBlockDamageAt(location, damage);
	}
}
