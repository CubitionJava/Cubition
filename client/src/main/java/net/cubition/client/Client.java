package net.cubition.client;

import java.io.File;
import java.io.IOException;

import net.cubition.client.gl.GLWindow;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class Client {

	protected final static String LIB_PATH = System.getenv("USERPROFILE")
			+ File.separator + "Cubition";
	
	protected final static File LIB_FOLDER = new File (LIB_PATH);
	protected final static File SETTINGS_FILE = new File (LIB_FOLDER, "settings");
	
	protected static GLWindow window;
	
	public static void main (String[] args)
	{
		// Startup
		// No need for existence checking, Java does this for us.
		LIB_FOLDER.mkdirs();
	
		try {
			SETTINGS_FILE.createNewFile();
		} catch (IOException e) {
			// Could not create settings file
			e.printStackTrace();
			System.exit (1);
		}
		
		Settings.load(SETTINGS_FILE);
		
		GLFW.glfwInit ();
		
		window = GLWindow.createWindow(Settings.screenWidth, Settings.screenHeight, "Cubition", false, true);
		GLContext context = window.createContext();
		
		window.sizeCenter(Settings.screenWidth, Settings.screenHeight);
		window.show ();
		
		GL11.glClearColor(1f, 1f, 1f, 1f);
		
		
		long i = 0;
		while (i < 81234789l && !window.shouldClose())
		{
			tick();
			i++;
		}
		
		window.destroy();
		GLFW.glfwTerminate();
	}
	
	public static void tick () {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glColor3f(100f, 255f, 255f);
		GL11.glBegin(GL11.GL_QUADS);
	    {
	    	GL11.glVertex2f(100,100);
		    GL11.glVertex2f(100+200,100);
		    GL11.glVertex2f(100+200,100+200);
		    GL11.glVertex2f(100,100+200);
	    }
	    GL11.glEnd();
	    
	    window.swapBuffers();
	}
	
}
