package net.cubition.minecraft.item;

import net.cubition.api.API;
import net.cubition.api.TranslatableResource;
import net.cubition.api.item.WeaponItem;
import net.cubition.api.network.NetworkIDResource;
import net.cubition.api.weapon.WeaponType;
import net.cubition.minecraft.weapon.WeaponSword;

public class ItemWoodenSword extends WeaponItem implements NetworkIDResource,
        TranslatableResource {

    @Override
    public String getNetworkID() {
        return "268";
    }

    @Override
    public String getTranslatableName() {
        return "item.swordWood";
    }

    @Override
    public String getTranslatableName(byte damage) {
        return "item.swordWood";
    }

    @Override
    public int maxStackSize() {
        return 1;
    }

    @Override
    public WeaponType getWeaponType() {
        return API.getWeaponTypeRegister().get(WeaponSword.class.getName());
    }
}
