package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolPickaxe;

public class BlockGold extends ItemBase implements BlockType, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "41";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		return 2;
	}

	@Override
	public String getTranslatableName() {
		return "tile.gold.default.name";
	}
}
