package net.cubition.client;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.*;

public class Client extends ApplicationAdapter {

	protected final static String LIB_PATH = System.getenv("USERPROFILE")
			+ File.separator + "Cubition";
	
	protected final static File LIB_FOLDER = new File (LIB_PATH);
	protected final static File SETTINGS_FILE = new File (LIB_FOLDER, "settings");

	@Override
	public void create ()
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
	}

	@Override
	public void render () {
		// Back color = black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
	}
	
}
