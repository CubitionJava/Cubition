package net.cubition.minecraft.item;

import net.cubition.api.API;
import net.cubition.api.TranslatableResource;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.api.tool.ToolType;
import net.cubition.minecraft.tool.ToolPickaxe;

public class ItemGoldPickaxe extends ToolItem implements NetworkIDResource,
        TranslatableResource {

    @Override
    public String getNetworkID() {
        return "285";
    }

    @Override
    public String getTranslatableName() {
        return "item.pickaxeGold";
    }

    @Override
    public String getTranslatableName(byte damage) {
        return "item.pickaxeGold";
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
