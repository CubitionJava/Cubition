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
		if (item.getToolType() instanceof ToolPickaxe) {
			return 4; // Pickaxe breaks a gold block faster than a hand
		} else {
			return 2;
		}
	}

	@Override
	public String getTranslatableName() {
		return "tile.gold.default.name";
	}
}
