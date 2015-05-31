package net.cubition.api;

import java.util.HashMap;
interface CommandSender extends MessageReceiver {
	public void dispatchCommand (String cmd);
	public  dispatchCommand (String cmd, String[] args);
}
