package net.cubition.cubition.LoadingScreenView

import net.cubition.cubition.View;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreenView implements View {
	
	private Texture logo;
	private SpriteBatch drawBatch;
	
	public LoadingScreenView () {
		// Load the Texture for the logo
		drawBatch = new SpriteBatch (1);
		
		FileHandle textureFileHandle = = new FileHandle (new File ("cubition.png"));
		logo = new Texture (textureFileHandle);
	}
	
	@Override
	public void render () {
		// Draw the logo on the screen
		drawBatch.begin ();
		drawBatch.draw (logo, (Gdx.graphics.getWidth () / 2) - (logo.getWidth () / 2), (Gdx.graphics.getHeight () / 2) - (logo.getHeight () / 2));
		drawBatch.flush ();
		drawBatch.end ();
	}
	
}
