package net.cubition.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ServerConfiguration {

	public String serverName;
	public String motd;
	public int maxPlayers;
	
	public String serverClosingMessage;
	public String serverRestartingMessage;
	
	public String modFolder;
	public String restartScript;
	
	public void loadFromFile(File file) {
		try {			
			BufferedReader reader = new BufferedReader(new FileReader (file));
			
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				
			}
			
			reader.close();
			
		} catch (IOException e) {
			// TODO handle not found
		}
		
	}
	
}
