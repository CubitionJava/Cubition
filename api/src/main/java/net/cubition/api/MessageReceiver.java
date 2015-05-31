package net.cubition.api;

import java.util.HashMap;
interface MessageReceiver {
	public void sendMessage (String msg);
	public void sendMessage (FormattedMessage msg);
}
