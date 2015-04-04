package net.cubition.api;

import net.cubition.api.vc.Availability;
import net.cubition.api.vc.Versions;
import org.apache.log4j.Logger;

/**
 * A ServerController manages the run loop of the server
 */
@Availability(server = Versions.SERVER_DEVL_0_0_1, client = Versions.CLIENT_NEVER)
public interface ServerController {
	/**
	 * Initializes the server
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public void initialize();

	/**
	 * Starts the server
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public void start();

	/**
	 * Server runloop Ticks everything the server needs to handle
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public void tick();

	/**
	 * Stops the server
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public void destroy();

	/**
	 * Restats the server
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public void restart();

	/**
	 * Gets the current working directory for the server
	 *
	 * @return The server current working directory
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public String getWorkingDirectory();

	/**
	 * Gets Current Server Version
	 *
	 * @return The Current Version of the server
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public Versions getVersion();

	/**
	 * Gets the Server's Logger
	 *
	 * @return The Server's Current Logger
	 */
	@Availability(server = Versions.SERVER_DEVL_0_0_1)
	public Logger getLogger();
}
