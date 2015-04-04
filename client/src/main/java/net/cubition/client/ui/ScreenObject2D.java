package net.cubition.client.ui;

import java.util.ArrayList;

public class ScreenObject2D extends ScreenObject {

	private ArrayList<IGLDrawable2D> elements = new ArrayList<IGLDrawable2D>();

	public void draw() {
		for (IGLDrawable2D drawable : elements)
			drawable.draw();
	}

	public ScreenObject2D(IGLDrawable2D... components) {
		for (IGLDrawable2D drawable : components)
			elements.add(drawable);
	}

}
