package net.cubition.cubition.desktop;

import net.cubition.cubition.Cubition;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Cubition(), config);
	}
}
