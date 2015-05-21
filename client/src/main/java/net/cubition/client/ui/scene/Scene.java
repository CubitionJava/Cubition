package net.cubition.client.ui.scene;

import net.cubition.client.ui.IGLDrawable;
import net.cubition.client.ui.ScreenObject;

import java.util.HashMap;

public class Scene implements IGLDrawable {

	protected HashMap<String, ScreenObject> sceneComponents = new HashMap<String, ScreenObject>();

	public void draw() {
		sceneComponents.values().forEach(net.cubition.client.ui.ScreenObject::draw);
	}

	public ScreenObject getObject(String name) {
		return sceneComponents.get(name);
	}

	public void addObject(String name, ScreenObject object) {
		sceneComponents.put(name, object);
	}

}
