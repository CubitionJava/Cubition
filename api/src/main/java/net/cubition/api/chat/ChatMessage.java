package net.cubition.api.chat;

import net.cubition.api.util.Pair;

public class ChatMessage {
  
  private List<String> components = new ArrayList <String>();
  
  /**
   * Convert this ChatMessage to bytes so it can be
   * transfered over network.
   * 
   * @return the byte value of this chat message
   */
  public byte[] toBytes () {
    // TODO Add 
    return new byte[];
  }
  
}
