package net.cubition.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Settings {

	public static boolean fullscreen = false;
	public static int screenWidth = 800;
	public static int screenHeight = 600;
	
	public static void load (File settingsFile)
	{
		try {
			// Create a Buffered Reader (for .readLine())
			BufferedReader reader = new BufferedReader (new FileReader(settingsFile));
			
			String line = null;
			
			// Loop over each line
			while ((line = reader.readLine()) != null)
			{
				line = line.trim();
				if (!line.startsWith("#"))
				{
					String[] parts = line.split("=", 2);
					
					if (parts.length > 1)
					{
						switch (parts[0].trim ())
						{
							case "fullscreen":
								fullscreen = Boolean.parseBoolean(parts[1].trim ());
								break;
							case "screen_width":
								screenWidth = Integer.parseInt (parts[1].trim());
								break;
							case "screen_height":
								screenHeight = Integer.parseInt (parts[1].trim());
								break;
						}
					}
				}
			}
			
			// Close the reader
			reader.close();
			
		} catch (FileNotFoundException e) {
			// Could not read settings file
		}
		catch (IOException e) {
			// Could not read line
		}
	}
	
	public static void save (File settingsFile)
	{
		try {
			String settings = "# Last saved on " + new Date().toString() + System.lineSeparator()
					+ "fullscreen=" + (fullscreen ? "true" : "false") + System.lineSeparator()
					+ "screen_width=" + screenWidth + System.lineSeparator()
					+ "screen_height=" + screenHeight;
			
			FileWriter writer = new FileWriter(settingsFile);
			
			writer.write(settings);
			
			writer.flush();
			writer.close ();
		} catch (IOException e) {
			// Could not write for some reason
		}
	}
	
}
