package net.cubition.api.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chat message build out of chat coponents
 */
public class ChatMessage {

	private List<String> components = new ArrayList<String>();

	/**
	 * Convert this ChatMessage to bytes so it can be transfered over network.
	 * 
	 * @return the byte value of this chat message
	 */
	public byte[] toBytes() {
		// TODO Convert this.components to bytes.
		// [part1].[part2].[part3], etc.
		return new byte[0];
	}

	@Override
	public String toString() {
		String output = "{[";
		boolean comma = false;

		for (String component : components) {
			output += "{text:\"" + component.replace("\"", "\\\"") + "\"}"
					+ (comma ? "," : "");
			comma = true;
		}

		return output + "]}";
	}

}
