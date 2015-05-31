package net.cubition.api;

public interface Creature extends Entity {
	public String getName ( );
	public int getMaxHealth ( );
	public int getHealth ( );
	public void setHealth (int health);
}
