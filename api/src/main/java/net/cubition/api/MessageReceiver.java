package net.cubition.api;

public interface MessageReceiver {
	public void sendMessage (String msg);
	public void sendMessage (FormattedMessage msg);
}
