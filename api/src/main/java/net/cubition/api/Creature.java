package net.cubition.api;

import java.util.HashMap;
interface Creature extends Entity {
	public  getName ( );
	public int getMaxHealth ( );
	public int getHealth ( );
	public void setHealth (int health);
}
