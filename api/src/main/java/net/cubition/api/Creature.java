package net.cubition.api;

public interface Creature extends Entity {
	String getName();
	int getMaxHealth();
	int getHealth();
	void setHealth(int health);
}
