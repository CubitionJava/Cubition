package net.cubition.client.ui;

public interface IGLDrawable2D {

	/**
	 * Draw this 3D element on the screen using GL11
	 */
	public void draw();

	public double getX();

	public double getY();

	public double getWidth();

	public double getHeight();

	public void setX(double value);

	public void setY(double value);

	public void setWidth(double value);

	public void setHeight(double value);

}
