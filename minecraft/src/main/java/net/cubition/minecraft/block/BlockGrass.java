package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolShovel;

public class BlockGrass extends ItemBase implements BlockType, NetworkIDResource {

	@Override
	public float getDigSpeed(ToolItem item) {
		if (item.getToolType() instanceof ToolShovel)
			return 6;
		else
			return 3;
	}

	@Override
	public String getNetworkID() {
		return "1";
	}

	@Override
	public String getTranslatableName() {
		return "tile.grass.name";
	}

}
