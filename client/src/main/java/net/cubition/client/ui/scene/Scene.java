package net.cubition.client.ui.scene;

import java.util.HashMap;

import net.cubition.client.ui.IGLDrawable;
import net.cubition.client.ui.ScreenObject;

public class Scene implements IGLDrawable {

	protected HashMap<String, ScreenObject> sceneComponents = new HashMap<String, ScreenObject>();

	public void draw() {
		for (ScreenObject drawable : sceneComponents.values())
			drawable.draw();
	}

	public ScreenObject getObject(String name) {
		return sceneComponents.get(name);
	}

	public void addObject(String name, ScreenObject object) {
		sceneComponents.put(name, object);
	}

}
