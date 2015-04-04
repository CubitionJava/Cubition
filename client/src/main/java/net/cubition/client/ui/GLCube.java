package net.cubition.client.ui;

public class GLCube implements IGLDrawable3D {

	public double x, y, z, width, height, depth;

	public void draw() {
		// TODO: Add code for draing using GL11......
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getDepth() {
		return depth;
	}

	@Override
	public void setX(double value) {
		x = value;
	}

	@Override
	public void setY(double value) {
		y = value;
	}

	@Override
	public void setZ(double value) {
		z = value;
	}

	@Override
	public void setWidth(double value) {
		width = value;
	}

	@Override
	public void setHeight(double value) {
		height = value;
	}

	@Override
	public void setDepth(double value) {
		depth = value;
	}

}
