package net.cubition.client.ui;

import java.util.ArrayList;
import java.util.Collections;

public class ScreenObject2D extends ScreenObject {

	private ArrayList<IGLDrawable2D> elements = new ArrayList<IGLDrawable2D>();

	public void draw() {
		elements.forEach(net.cubition.client.ui.IGLDrawable2D::draw);
	}

	public ScreenObject2D(IGLDrawable2D... components) {
		Collections.addAll(elements, components);
	}

}
