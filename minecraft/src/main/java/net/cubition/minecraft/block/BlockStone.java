package net.cubition.minecraft.block;

import net.cubition.api.TranslatableResource;
import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.minecraft.tool.ToolPickaxe;

public class BlockStone extends ItemBase implements BlockType, TranslatableResource, NetworkIDResource {

	@Override
	public String getNetworkID() {
		return "1";
	}

	@Override
	public float getDigSpeed(ToolItem item) {
		if (item.getToolType() instanceof ToolPickaxe)
			return 4;
		else
			return 2;
	}

	@Override
	public String getTranslatableName() {
		return "tile.stone.stone.name";
	}
	
	@Override 
	public String getTranslatableName (byte damage)	{
		switch (damage)
		{
		case 1:
			return "tile.stone.granite.name";
		case 2:
			return "tile.stone.graniteSmooth.name";
		case 3:
			return "tile.stone.diorite.name";
		case 4:
			return "tile.stone.dioriteSmooth.name";
		case 5:
			return "tile.stone.andesite.name";
		case 6:
			return "tile.stone.andesiteSmooth.name";
		default:
			return getTranslatableName ();
		}
	}

}
