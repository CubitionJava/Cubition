package net.cubition.api;

public class Region2D implements Region {
	private Location min, max;
	
	public Region2D (Location pos1, Location pos2) throws Exception {
		if (pos1.getWorld() != pos2.getWorld())
			throw new Exception ("Could not create a region with points in different worlds.");
		
		this.min = new Location (pos1.getWorld(), Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y), Math.min(pos1.z, pos2.z));
		this.max = new Location (pos1.getWorld(), Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y), Math.max(pos1.z, pos2.z));
	}

	public Location getMin () {
		return min;
	}
	public Location getMax () {
		return max;
	}
	
	@Override
	public Location center() {
		return new Location
			(min.getWorld(),
			(min.x + max.x) / 2,
			(min.y + max.y) / 2,
			(min.z + max.z) / 2);
	}

	@Override
	public boolean isInside(Location test) {
		if (test.getWorld() != min.getWorld())
			return false;
		
		return (test.x >= min.x && test.x <= max.x) &&
				(test.z >= min.z && test.z <= max.z);
	}

	@Override
	public Block[] getBlocksInside() {
		// TODO Auto-generated method stub
		return null;
	}
}
