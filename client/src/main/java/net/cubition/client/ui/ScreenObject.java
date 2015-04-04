package net.cubition.client.ui;

import java.util.ArrayList;

public class ScreenObject implements IGLDrawable {

	private ArrayList<IGLDrawable> elements = new ArrayList<IGLDrawable>();

	public void draw() {
		for (IGLDrawable drawable : elements)
			drawable.draw();
	}

}
