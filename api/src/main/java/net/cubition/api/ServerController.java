package net.cubition.api;

public interface ServerController {
    /**
     * Initializes the server
     */
    public void initialize();

    /**
     * Starts the server
     */
    public void start();

    /**
     * Server runloop
     * Ticks everything the server needs to handle
     */
    public void tick();

    /**
     * Stops the server
     */
    public void destroy();

    /**
     * Restats the server
     */
    public void restart();
}
