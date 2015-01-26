package net.cubition.api.block;

import net.cubition.api.tool.ToolType;

public interface BlockType {

	/**
	 * Get the dig speed using tool [tool]
	 * 
	 * To calculate the current block dig state,
	 * use this formula:
	 * 
	 * [digState = (int)time * digSpeed]
	 * where time is in seconds and 
	 * digSpeed is a float equal to lower than 10. (10 is insta-break)
	 * Example: 2 * 0.73 = 1.46 int = 1
	 * When it reaches 10 the block is broken
	 * 
	 * @param tool the tool
	 * @return the dig speed
	 */
	public float getDigSpeed (ToolType tool);
	
}
