package net.cubition.api;

import java.util.HashMap;
interface Region {
	public Location center ( );
	public boolean isInside (Location test);
	public Blocks getBlocksInside ( );
}
