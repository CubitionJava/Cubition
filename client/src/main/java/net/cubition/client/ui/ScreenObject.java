package net.cubition.client.ui;

import java.util.ArrayList;

public class ScreenObject implements IGLDrawable {

	private ArrayList<IGLDrawable> elements = new ArrayList<IGLDrawable>();

	public void draw() {
		elements.forEach(net.cubition.client.ui.IGLDrawable::draw);
	}

}
