package net.cubition.minecraft.item;

import net.cubition.api.API;
import net.cubition.api.TranslatableResource;
import net.cubition.api.item.WeaponItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.api.weapon.WeaponType;
import net.cubition.minecraft.weapon.WeaponArrow;

public class ItemArrow extends WeaponItem implements NetworkIDResource,
        TranslatableResource {

    @Override
    public String getNetworkID() {
        return "10";
    }

    @Override
    public String getTranslatableName() {
        return "item.arrow";
    }

    @Override
    public String getTranslatableName(byte damage) {
        return "item.arrow";
    }

    @Override
    public int maxStackSize() {
        return 16;
    }

    @Override
    public WeaponType getWeaponType() {
        return API.getWeaponTypeRegister().get(WeaponArrow.class.getName());
    }
}
