package net.cubition.client.view;

import net.cubition.client.View;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import java.io.FileInputStream;
import java.nio.ByteBuffer;


public class LoadingScreenView implements View {
		
	@Override
	public void render () {
		FileInputStream in;
		try {
			in = new FileInputStream("logo.png");

			PNGDecoder decoder = new PNGDecoder(in);
			
			System.out.println("width="+decoder.getWidth());
			System.out.println("height="+decoder.getHeight());
			
			ByteBuffer buf = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
			decoder.decode(buf, decoder.getWidth()*4, Format.RGBA);
			buf.flip();
		} catch (Exception ex) {
			// TODO: Error handling.
			// Something with file loading must have gone wrong
		} finally {
			if (in != null)
				in.close();
		}
	}
	
}
