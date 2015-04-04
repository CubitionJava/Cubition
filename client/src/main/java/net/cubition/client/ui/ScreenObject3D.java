package net.cubition.client.ui;

import java.util.ArrayList;

public class ScreenObject3D extends ScreenObject {

	private ArrayList<IGLDrawable3D> elements = new ArrayList<IGLDrawable3D>();

	public void draw() {
		for (IGLDrawable3D drawable : elements)
			drawable.draw();
	}

	public ScreenObject3D(IGLDrawable3D... components) {
		for (IGLDrawable3D drawable : components)
			elements.add(drawable);
	}
}
