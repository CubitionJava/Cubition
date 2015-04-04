package net.cubition.client.ui;

public interface IGLDrawable3D {

	/**
	 * Draw this 3D element on the screen using GL11
	 */
	public void draw();

	public double getX();

	public double getY();

	public double getZ();

	public double getWidth();

	public double getHeight();

	public double getDepth();

	public void setX(double value);

	public void setY(double value);

	public void setZ(double value);

	public void setWidth(double value);

	public void setHeight(double value);

	public void setDepth(double value);

}
