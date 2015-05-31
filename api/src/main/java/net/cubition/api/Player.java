package net.cubition.api;

public interface Player extends CommandSender, Creature {
	public int getID ();
	
	public int getFoodLevel ( );
	public void setFoodLevel (int level);
	
	public void kick ();
	public void kick(String message);
}
