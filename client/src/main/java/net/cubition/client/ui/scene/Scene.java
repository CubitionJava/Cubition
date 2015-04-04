package net.cubition.client.ui.scene;

import java.util.ArrayList;

import net.cubition.client.ui.IGLDrawable;
import net.cubition.client.ui.IGLDrawable2D;
import net.cubition.client.ui.ScreenObject;

public class Scene implements IGLDrawable {

	protected ArrayList<ScreenObject> sceneComponents = new ArrayList<ScreenObject>();

	public void draw() {
		for (ScreenObject drawable : sceneComponents)
			drawable.draw();
	}

}
