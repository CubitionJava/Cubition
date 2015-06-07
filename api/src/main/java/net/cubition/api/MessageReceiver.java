package net.cubition.api;

public interface MessageReceiver {
	void sendMessage(String msg);
	void sendMessage(FormattedMessage msg);
}
