package net.cubition.api.world;

import net.cubition.api.block.Block;
import net.cubition.api.block.BlockType;

public interface Chunk {

	Block getBlockAt(double relativeX, double relativeY, double relativeZ);

	void setBlockType(int relativeX, int relativeY, int relativeZ,
			BlockType type);

	BlockType getBlockType(int x, int y, int z);

	World getWorld();
	
	int getStartX ();
	
	int getStartY ();
	
	int getStartZ ();

}
