package net.cubition.client.gl;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

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
		return createWindow (width, height, title, GLFW.glfwGetPrimaryMonitor (), 0, visible, resizable);
	}
	
	public static GLWindow createWindow(int width, int height, String title)
	{
		return createWindow (width, height, title, true, false);
	}
	
	public void swapBuffers ()
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

	public void sizeCenter (int width, int height) {
		ByteBuffer vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(
            windowID,
            (GLFWvidmode.width(vidmode) - width) / 2,
            (GLFWvidmode.height(vidmode) - height) / 2
        );
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
	public GLContext createContext () {
		GLFW.glfwMakeContextCurrent (windowID);
		return GLContext.createFromCurrent();
	}
	public void show () {
		GLFW.glfwShowWindow(windowID);
	}
	public void mouseVisible (CursorMode mode) {
		GLFW.glfwSetInputMode (windowID, GLFW.GLFW_CURSOR, mode.getGlfwMode());
	}
	
	public enum CursorMode {
		NORMAL (1),
		HIDDEN (2),
		DISABLED (212995);
		private int glfwMode;
		private CursorMode (int glfwMode) {
			this.glfwMode = glfwMode;
		}
		public int getGlfwMode () {
			return glfwMode;
		}
	}
}
