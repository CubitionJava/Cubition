package net.cubition.minecraft.item;

import net.cubition.api.API;
import net.cubition.api.TranslatableResource;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.api.tool.ToolType;
import net.cubition.minecraft.tool.ToolPickaxe;

public class ItemDiamondPickaxe extends ToolItem implements NetworkIDResource,
        TranslatableResource {

    @Override
    public String getNetworkID() {
        return "278";
    }

    @Override
    public String getTranslatableName() {
        return "item.pickaxeDiamond";
    }

    @Override
    public String getTranslatableName(byte damage) {
        return "item.pickaxeDiamond";
    }

    @Override
    public int maxStackSize() {
        return 1;
    }

    @Override
    public ToolType getToolType() {
        return API.getToolTypeRegister().get(ToolPickaxe.class.getName());
    }
}
