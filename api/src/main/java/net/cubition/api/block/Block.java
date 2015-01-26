package net.cubition.api.block;

import net.cubition.api.world.Chunk;
import net.cubition.api.world.Location;

<<<<<<< HEAD
<<<<<<< HEAD
public class Block
{
=======
public class Block {
>>>>>>> parent of 53c9e69... Bloxx

	private Chunk chunk;
	private int x, y, z;
	
	public Block (Location location)
	{
		this.chunk = location.getChunk ();
		this.x = (int) location.getRelativeX ();
		this.y = (int) location.getRelativeY ();
		this.z = (int) location.getRelativeZ ();
	}
	
	public Location getLocation ()
	{
		return new Location (chunk.getWorld (), chunk.getStartX (), chunk.getStartY (), chunk.getStartZ ());
	}
	
	public BlockType getType ()
	{
		return chunk.getBlockType (x, y, z);
	}
	
	public void setBlockType (BlockType type)
	{
		chunk.setBlockType (x, y, z, type);
	}
	
<<<<<<< HEAD
	/**
	 * @return the current block state
	 */
	public BlockState getBlockState ()
	{
		return state;
	}
	
	public void setBlockData (BlockState state)
	{
		
	}
	
=======
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
>>>>>>> origin/master
=======
>>>>>>> parent of 53c9e69... Bloxx
}
