package net.cubition.client.ui.scene;

import java.util.ArrayList;

import net.cubition.client.ui.IGLDrawable;

public class Scene implements IGLDrawable {

	protected ArrayList<IGLDrawable> sceneComponents = new ArrayList<IGLDrawable>();
	
	public void draw () {
		for (IGLDrawable drawable : sceneComponents)
			drawable.draw ();
	}
	
}
