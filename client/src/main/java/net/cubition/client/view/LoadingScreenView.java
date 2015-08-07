package net.cubition.client.view;

import net.cubition.client.View;
import de.matthiasmann.twl.utils.PNGDecoder;

public class LoadingScreenView implements View {
		
	@Override
	public void render () {
		InputStream in = new FileInputStream("logo.png");
		
		try {
			PNGDecoder decoder = new PNGDecoder(in);
			
			System.out.println("width="+decoder.getWidth());
			System.out.println("height="+decoder.getHeight());
			
			ByteBuffer buf = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
			decoder.decode(buf, decoder.getWidth()*4, Format.RGBA);
			buf.flip();
		} finally {
			in.close();
		}
	}
	
}
