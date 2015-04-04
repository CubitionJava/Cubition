package net.cubition.client.ui.scene;

import java.util.Collection;
import java.util.HashMap;

public class SceneManager {

	protected HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	protected String activeSceneName;
	protected Scene activeScene;

	public void activate(String name) {
		if (!scenes.containsKey(name))
			throw new IllegalArgumentException("This scene does not exist");

		activeSceneName = name;
		activeScene = scenes.get(activeSceneName);
	}

	public String getActiveSceneName() {
		return activeSceneName;
	}

	public Scene getActiveScene() {
		return activeScene;
	}

	public Collection<Scene> getScenes() {
		return scenes.values();
	}

	public void addScene(String name, Scene scene) {
		if (scenes.containsKey(name))
			throw new IllegalArgumentException(
					"A scene with such name already exists.");

		scenes.put(name, scene);
	}

	public void removeScene(String name) {
		if (!scenes.containsKey(name))
			throw new IllegalArgumentException(
					"A scene with such name does not exist.");

		scenes.remove(name);
	}

	public void draw() {
		if (activeScene != null)
			activeScene.draw();
	}

}
