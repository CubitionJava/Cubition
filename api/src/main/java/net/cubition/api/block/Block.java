package net.cubition.api.block;

import net.cubition.api.world.Chunk;
import net.cubition.api.world.Location;

public class Block
{

	/**
	 * The chunk this block is in
	 */
	private Chunk chunk;
	
	/**
	 * The coordinates of the block
	 * relative to the chunk
	 */
	private int x, y, z;
	
	/**
	 * The current state of the block
	 */
	private BlockState state;
	
	public Block (Location location)
	{
		this.chunk = location.getChunk ();
		this.x = (int) location.getRelativeX ();
		this.y = (int) location.getRelativeY ();
		this.z = (int) location.getRelativeZ ();
	}
	
	/**
	 * @return the location of this block
	 */
	public Location getLocation ()
	{
		return new Location (chunk.getWorld (), chunk.getStartX (), chunk.getStartY (), chunk.getStartZ ());
	}
	
	/**
	 * @return the type this block is
	 */
	public BlockType getType ()
	{
		return chunk.getBlockType (x, y, z);
	}
	
	/**
	 * Sets the type of this block
	 * 
	 * @param type the type
	 */
	public void setBlockType (BlockType type)
	{
		chunk.setBlockType (x, y, z, type);
	}
	
	/**
	 * @return the current block state
	 */
	public BlockState getBlockState ()
	{
		return state;
	}
	
	public void setBlockData (BlockState state)
	{
		this.state = state;
	}

}
