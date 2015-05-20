package net.cubition.minecraft.item;

import net.cubition.api.API;
import net.cubition.api.TranslatableResource;
import net.cubition.api.item.ToolItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.api.tool.ToolType;
import net.cubition.minecraft.tool.ToolPickaxe;

public class ItemIronPickaxe extends ToolItem implements NetworkIDResource,
        TranslatableResource {

        @Override
        public String getNetworkID() {
                return "257";
        }

        @Override
        public String getTranslatableName() {
                return "item.pickaxeIron";
        }

        @Override
        public String getTranslatableName(byte damage) {
                return "item.pickaxeIron";
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
