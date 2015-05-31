package net.cubition.api.item;

import net.cubition.api.tool.ToolType;

public abstract class ToolItem extends ItemBase {

	/**
	 * Returns the type of tool this ToolItem is
	 * 
	 * @return the type
	 */
	public abstract ToolType getToolType();

}
