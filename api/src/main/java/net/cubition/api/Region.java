package net.cubition.api;

public interface Region {
	public Location center ( );
	public boolean isInside (Location test);
	public Block[] getBlocksInside ( );
}
