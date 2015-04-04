package net.cubition.client;

import java.io.File;
import java.io.IOException;

import org.lwjgl.glfw.GLFW;

import net.cubition.client.gl.GLWindow;

public class Client {

	protected final static String LIB_PATH = System.getenv("USERPROFILE")
			+ File.separator + "Cubition";
	
	protected final static File LIB_FOLDER = new File (LIB_PATH);
	protected final static File SETTINGS_FILE = new File (LIB_FOLDER, "settings");
	
	protected static GLWindow window;
	
	public static void main (String[] args)
	{
		// Startup
		// No need for existance checking, Java does this for us.
		LIB_FOLDER.mkdirs();
	
		try {
			SETTINGS_FILE.createNewFile();
		} catch (IOException e) {
			// Could not create settings file
			e.printStackTrace();
			System.exit (1);
		}
		
		Settings.load(SETTINGS_FILE);
		
		window = GLWindow.createWindow(Settings.screenWidth, Settings.screenHeight, "Cubition", true, true);
		
		long i = 0;
		while (i < 81234789l && !window.shouldClose())
		{
		}
		
		window.destroy();
		
		GLFW.glfwTerminate();
	}
	
}
