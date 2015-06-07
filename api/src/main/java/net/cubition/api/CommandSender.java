package net.cubition.api;

public interface CommandSender extends MessageReceiver {
	void dispatchCommand(String cmd);
	void dispatchCommand(String cmd, String[] args);
}
