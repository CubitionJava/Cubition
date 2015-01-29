package net.cubition.api.item;

import net.cubition.api.tool.ToolType;

public interface ToolItem extends ItemBase {

	/**
	 * Returns the type of tool this ToolItem is
	 * @return the type
	 */
	public ToolType getToolType ();
	
}
