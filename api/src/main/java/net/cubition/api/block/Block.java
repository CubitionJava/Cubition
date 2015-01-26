package net.cubition.api.block;

import net.cubition.api.world.Chunk;
import net.cubition.api.world.Location;

public class Block {
    private Chunk chunk;
    private int x, y, z;

    public Block(Location location) {
        this.chunk = location.getChunk();
        this.x = (int) location.getRelativeX();
        this.y = (int) location.getRelativeY();
        this.z = (int) location.getRelativeZ();
    }

    public Location getLocation() {
        return new Location(chunk.getWorld(), x, y, z);
    }

    public BlockType getType() {
        return chunk.getBlockType(x, y, z);
    }

    public void setBlockType(BlockType type) {
        chunk.setBlockType(x, y, z, type);
    }
}
