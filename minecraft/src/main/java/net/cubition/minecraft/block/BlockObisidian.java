package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolPickaxe;

public class BlockObsidian extends ItemBase implements BlockType, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "49";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		return 1;
	}

	@Override
	public String getTranslatableName() {
		return "tile.obsidian.default.name";
	}
}
