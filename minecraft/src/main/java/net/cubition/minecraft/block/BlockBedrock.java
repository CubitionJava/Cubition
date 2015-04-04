package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;

public class BlockBedrock extends ItemBase implements BlockType,
		NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "7";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		return 0;
	}

	@Override
	public String getTranslatableName() {
		return "tile.bedrock.default.name";
	}
}
