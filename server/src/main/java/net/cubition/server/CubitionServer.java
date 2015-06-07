package net.cubition.server;

import net.cubition.api.ModManager;
import net.cubition.api.Player;
import net.cubition.api.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class CubitionServer extends Server {

	public ArrayList<Player> onlinePlayers;
	public ServerConfiguration config;
	public final File WORKING_DIR = new File ("");
	public static final Logger LOGGER = LoggerFactory.getLogger (CubitionServer.class);
	
	public boolean shouldShutdown = false;
	
	@Override
	public Player[] getOnlinePlayers() {
		return onlinePlayers.toArray(new Player[onlinePlayers.size()]);
	}
	
	public static void main (String [] args) {
		try {
			Server.setServer(new CubitionServer ());
			Server.getServer().start();
		}
		catch (Exception ex) {
			LOGGER.info ("Could not start server");
			System.exit (0);
		}
	}
	
	@Override
	public void start () {
		config = new ServerConfiguration ();
		config.loadFromFile (new File ("server.ini"));
		
		onlinePlayers = new ArrayList<Player>();
		
		try {
			ModManager.setModFolder (null);
		} catch (Exception e) {
			// TODO handle exception
		}
		
		while (!shouldShutdown)
			tick ();
		
	}
	
	public void tick () {
		// Insert 1 COIN(s) HERE
		if (new Random().nextInt (10) == 0)
			shouldShutdown = true;
		else
			LOGGER.info("Server still running, waiting for random to be 0");
	}

	@Override
	public void stop() {
		stop (config.serverClosingMessage);
	}

	@Override
	public void stop(String msg) {
		shouldShutdown = true;
		
		for (Player p: onlinePlayers)
			p.kick(msg);
		
		ModManager.unloadAll ();
		
		System.exit (0);
	}
	
	@Override
	public void restart () {
		if (Paths.get(config.restartScript).toFile().exists())
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					super.run();

					try {
						new ProcessBuilder()
								.command("sh", config.restartScript)
								.directory(WORKING_DIR)
								.start();
					} catch (IOException ex) {
						throw new ServerError("Error Running " + config.restartScript, ex);
					}
				}
			});

		this.stop ();
	}

	@Override
	public Player getPlayer(int id) {
		for (Player p: onlinePlayers)
			if (p.getID() == id)
				return p;
		
		// TODO OFFLINEPLAYER
		return null;
	}

	@Override
	public Player getPlayer(String name) {
		for (Player p: onlinePlayers)
			if (p.getName().equalsIgnoreCase(name))
				return p;
		
		// TODO OFFLINEPLAYER
		return null;
	}
	
}
