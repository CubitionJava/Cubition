package net.cubition.api;

public interface CommandSender extends MessageReceiver {
	public void dispatchCommand (String cmd);
	public void dispatchCommand (String cmd, String[] args);
}
