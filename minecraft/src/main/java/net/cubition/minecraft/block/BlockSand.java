package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolShovel;

public class BlockSand extends ItemBase implements BlockType, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "12";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		if (item.getToolType() instanceof ToolShovel)
			return 5;
		else
			return 7;
	}

	@Override
	public String getTranslatableName() {
		return "tile.sand.default.name";
	}
}
