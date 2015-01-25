package net.cubition.api.block;

import net.cubition.api.tool.ToolType;

public interface BlockType {

	/**
	 * Get the dig speed using tool [tool]
	 * 
	 * To calculate the current block dig state,
	 * use this formula:
	 * 
	 * [digState = time * digSpeed]
	 * time > seconds
	 * digSpeed > float~1
	 * 
	 * @param tool the tool
	 * @return the dig speed
	 */
	public float getDigSpeed (ToolType tool);
	
}
