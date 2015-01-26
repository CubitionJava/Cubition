package net.cubition.api.world;

public interface World {

    public String getName();

    public Chunk getChunk(double x, double y, double z);

    public Chunk getChunkAt(Location loc);
}
