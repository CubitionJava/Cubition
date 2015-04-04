package net.cubition.minecraft.block;

import net.cubition.api.TranslatableResource;
import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolPickaxe;

public class BlockWood extends ItemBase implements BlockType,
		TranslatableResource, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "5";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		if (item.getToolType() instanceof ToolAxe)
			return 4;
		else
			return 2;
	}

	@Override
	public String getTranslatableName() {
		return "tile.wood.default.name";
	}

	@Override
	public String getTranslatableName(byte damage) {
		switch (damage) {
		case 1:
			return "tile.wood.spruce.name";
		case 2:
			return "tile.wood.birch.name";
		case 3:
			return "tile.wood.jungle.name";
		case 4:
			return "tile.wood.acacia.name";
		case 5:
			return "tile.wood.darkOak.name";
		default:
			return getTranslatableName();
		}
	}

}
