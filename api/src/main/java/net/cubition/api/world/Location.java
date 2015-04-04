package net.cubition.api.world;

import net.cubition.api.block.Block;

public class Location {
	public double x = 0, y = 0, z = 0;
	public double xrot = 0, yrot = 0, zrot = 0;
	private World world;

	public Location(World world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(World world, double x, double y, double z, double xrot,
			double yrot, double zrot) {
		this(world, x, y, z);
		this.xrot = xrot;
		this.yrot = yrot;
		this.zrot = zrot;
	}

	public Block getBlock() {
		return world.getChunkAt(this).getBlockAt(getRelativeX(),
				getRelativeY(), getRelativeZ());
	}

	public int getBlockX() {
		return (int) x;
	}

	public int getBlockY() {
		return (int) y;
	}

	public int getBlockZ() {
		return (int) z;
	}

	public double getRelativeX() {
		return x % 16;
	}

	public double getRelativeY() {
		return y % 16;
	}

	public double getRelativeZ() {
		return z % 16;
	}

	public Chunk getChunk() {
		return world.getChunkAt(this);
	}

}
