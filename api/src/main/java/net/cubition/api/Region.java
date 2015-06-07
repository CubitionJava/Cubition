package net.cubition.api;

public interface Region {
	Location center();
	boolean isInside(Location test);
	Block[] getBlocksInside();
}
