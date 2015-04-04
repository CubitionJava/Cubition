package net.cubition.client.ui;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glTexImage2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class ImageObject extends ScreenObject {

	private BufferedImage image;
	private boolean rgba = false;
	
	public ImageObject (File imageFile) {
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {}
	}
	
	public ImageObject (File imageFile, boolean rgba) {
		this(imageFile);
		this.rgba = rgba;
	}
	
	public ImageObject (BufferedImage image) {
		this.image = image;
	}
	
	public ImageObject (BufferedImage image, boolean rgba) {
		this.image = image;
		this.rgba = rgba;
	}
	
	public void draw ()
	{
		if (image == null)
			return;
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * (rgba ? 4 : 3));
        
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        // Flip the buffer
        buffer.flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}
	
}
