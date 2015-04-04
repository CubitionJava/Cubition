package net.cubition.client.gl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Object Oriented Window Wrapper
 * for GLWF
 * 
 * @author Xesau
 */
public class GLWindow {

	protected long windowID;
	
	private GLWindow (long windowID) {
		this.windowID = windowID;
	}
	
	public static GLWindow createWindow(int width, int height, String title, long monitor, long share, boolean visible, boolean resizable)
	{
		GLFW.glfwInit();
		
		GLFW.glfwDefaultWindowHints();
		
		GLFW.glfwWindowHint (GLFW.GLFW_VISIBLE, visible ? 1 : 0);
		GLFW.glfwWindowHint (GLFW.GLFW_RESIZABLE, resizable ? 1 : 0);
		
		return new GLWindow (GLFW.glfwCreateWindow(width, height, title, monitor, share));
	}
	
	public static GLWindow createWindow(int width, int height, String title, boolean visible, boolean resizable)
	{
		return createWindow (width, height, title, GLFW.glfwGetPrimaryMonitor(), 0, visible, resizable);
	}
	
	public static GLWindow createWindow(int width, int height, String title)
	{
		return createWindow (width, height, title, true, false);
	}
	
	public void SwapBuffers ()
	{
		GLFW.glfwSwapBuffers(windowID);
	}
	
	public boolean shouldClose ()
	{
		return GLFW.glfwWindowShouldClose(windowID) == 1;
	}
	
	public void shouldClose (int d) {
		GLFW.glfwSetWindowShouldClose (windowID, d);
	}

	public void destroy ()
	{
		GLFW.glfwDestroyWindow(windowID);
	}
	
	public void setKeyCallback (GLFWKeyCallback callback)
	{
		GLFW.glfwSetKeyCallback(windowID, callback);
	}
	
	public void fullscreen (boolean fullscreen) {
		
	}
	
	public void makeCurrentContext () {
		GLFW.glfwMakeContextCurrent(windowID);
	}
	
	public void size (int width, int height) {
		GLFW.glfwSetWindowSize (windowID, width, height);
	}
	
	public  void position (int x, int y) {
		GLFW.glfwSetWindowPos(windowID, x, y);
	}
	
	public void setTitle (String title) {
		GLFW.glfwSetWindowTitle(windowID, title);
	}
	
	public static void setGlfwHint (int hint, int value) {
		GLFW.glfwWindowHint (hint, value);
	}
	
	public void iconify () {
		GLFW.glfwIconifyWindow(windowID);
	}
	
}
