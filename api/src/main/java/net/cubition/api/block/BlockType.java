package net.cubition.api.block;

import net.cubition.api.item.ToolItem;

public interface BlockType {

	/**
	 * Get the dig speed using tool [tool]<br />
	 * <br />
	 * To calculate the current block dig state,<br />
	 * use this formula:<br />
	 *
	 * <code>digState = time * digSpeed</code><br />
	 * where <code>time</code> is in seconds and <code>digSpeed</code> is a
	 * float equal to lower than 10. (10 is insta-break)<br/>
	 * <br />
	 * Example with a dig speed of 0.73: 2 * 0.73 = 1.46; integer value = 1<br />
	 * When it reaches 10 the block is broken, so approximately after 14
	 * seconds.<br />
	 * <br />
	 * Special cases<br />
	 * 0 = unbreakable<br />
	 * 10 = instant breaking<br />
	 *
	 * @param tool
	 *            the tool
	 * @return the dig speed
	 */
	public float getDigSpeed(ToolItem item);

}
