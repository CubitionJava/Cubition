package net.cubition.api.world;

public interface World {

	String getName();

	Chunk getChunk(double x, double y, double z);

	Chunk getChunkAt(Location loc);
}
