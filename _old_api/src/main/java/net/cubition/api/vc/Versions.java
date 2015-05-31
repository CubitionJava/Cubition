package net.cubition.api.vc;

/**
 * Represents the versions of the server and client
 */
public enum Versions {
	/**
	 * Never in Client
	 */
	CLIENT_NEVER(null, -1),

	/**
	 * In Client Development Version 0.0.1
	 */
	CLIENT_DEVL_0_0_1("Client 0.0.1D", 1),

	/**
	 * Never in Server
	 */
	SERVER_NEVER(null, -1),

	/**
	 * In Server Development Version 0.0.1
	 */
	SERVER_DEVL_0_0_1("Server 0.0.1D", 0x8105CCE);

	/**
	 * The name of this version
	 */
	private String name;

	/**
	 * The build number for this version
	 */
	private long build;

	private Versions(String name, long build) {
		this.build = build;
		this.name = name;
	}

	/**
	 * Gets the name for this version
	 *
	 * @return The name of this version
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the build number for this version
	 *
	 * @return The build number of this version
	 */
	public long getBuild() {
		return this.build;
	}
}
