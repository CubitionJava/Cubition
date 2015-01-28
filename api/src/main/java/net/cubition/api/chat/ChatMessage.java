package net.cubition.api.chat;

import net.cubition.api.util.Pair;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a chat message build out of chat coponents
 */
public class ChatMessage {
  
  private List<String> components = new ArrayList <String>();
  
  /**
   * Convert this ChatMessage to bytes so it can be
   * transfered over network.
   * 
   * @return the byte value of this chat message
   */
  public byte[] toBytes () {
    // TODO Convert this.components to bytes.
    // [part1].[part2].[part3], etc.
    return new byte[0];
  }
  
  @Override
  public String toString () {
    String output = "{[";
    boolean comma = false;
    
    for (String component : components)
    {
      output += "{text:\"" + component.replace ("\"", "\\\"") + "\"}" + (comma ? "," : "");
      comma = true;
    }
    
    return output +="]}";
  }
  
}
