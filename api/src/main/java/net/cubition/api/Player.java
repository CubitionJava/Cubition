package net.cubition.api;

import java.util.HashMap;
interface Player extends CommandSender, Creature {
	public int getFoodLevel ( );
	public void setFoodLevel (int level);
}
