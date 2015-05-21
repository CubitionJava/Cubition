package net.cubition.client.ui;

import java.util.ArrayList;
import java.util.Collections;

public class ScreenObject3D extends ScreenObject {

	private ArrayList<IGLDrawable3D> elements = new ArrayList<IGLDrawable3D>();

	public void draw() {
		elements.forEach(net.cubition.client.ui.IGLDrawable3D::draw);
	}

	public ScreenObject3D(IGLDrawable3D... components) {
		Collections.addAll(elements, components);
	}
}
