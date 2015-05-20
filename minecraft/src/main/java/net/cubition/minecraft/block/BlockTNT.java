package net.cubition.minecraft.block;

import net.cubition.api.block.BlockType;
import net.cubition.api.item.ItemBase;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;

public class BlockTNT extends ItemBase implements BlockType,
        NetworkIDResource {

    @Override
    public String getNetworkID() {
        return "46";
    }

    @Override
    public float getDigSpeed(ToolItem item) {
        return 0;
    }

    @Override
    public String getTranslatableName() {
        return "tile.tnt.default.name";
    }
}
