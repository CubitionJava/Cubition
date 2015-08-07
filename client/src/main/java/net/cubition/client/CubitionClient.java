package net.cubition.client;

import net.cubition.client.view.LoadingScreenView;
import static org.lwjgl.opengl.GL11.*;

public class CubitionClient {
	
	// Keep these public. Makes it easier in the long run
	public View view;
	public boolean shouldShutdown = false;
	
	private static boolean initialized;
	
	public static void main (String[] args) {
		if (!initialized)
			new CubitionClient().run();
		initialized = true;
	}
	
	public void run () {
		this.view = new LoadingScreenView();
		
		while (!shouldShutdown) {
			render ();
		}
	}
	
	public void render () {
		// Reset GL: Clear to black
		glClearColor (0, 0, 0, 1);
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		// Render the current view, nothing special
		// Nothing needs to be passed to .render(). Gtx is static public
		view.render ();
	}
	
}
