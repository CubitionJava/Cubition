package net.cubition.api.item;

import net.cubition.api.weapon.WeaponType;

public abstract class WeaponItem extends ItemBase {

    /**
     * Returns the type of weapon this WeaponItem is
     *
     * @return the type
     */
    public abstract WeaponType getWeaponType();
}
