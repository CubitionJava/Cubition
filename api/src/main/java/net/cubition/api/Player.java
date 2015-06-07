package net.cubition.api;

public interface Player extends CommandSender, Creature {
	int getID();
	
	int getFoodLevel();
	void setFoodLevel(int level);
	
	void kick();
	void kick(String message);
	
	Inventory getInventory();
}
