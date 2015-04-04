package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolShovel;

public class BlockDirt extends ItemBase implements BlockType, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "3";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		if (item.getToolType() instanceof ToolShovel)
			return 6;
		else
			return 3;
	}

	@Override
	public String getTranslatableName() {
		return "tile.dirt.default.name";
	}

	@Override
	public String getTranslatableName(byte damage) {
		switch (damage) {
		case 1:
			return "tile.dirt.coarse.name";
		case 2:
			return "tile.dirt.podzol.name";
		default:
			return getTranslatableName();
		}
	}

}
