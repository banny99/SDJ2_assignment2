package shared;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class MessageObject implements Serializable
{
  private String messageText;
  private Time messageTimeStamp;
  private String sender;
  private ArrayList<LoginObject> chatMembers;

  public MessageObject(String msg, String sender)
  {
    messageText = msg;
    messageTimeStamp = new Time(System.currentTimeMillis());
    this.sender = sender;
  }

  public MessageObject(String msg, String sender, ArrayList<LoginObject> chatMembers)
  {
    messageText = msg;
    messageTimeStamp = new Time(System.currentTimeMillis());
    this.sender = sender;
    this.chatMembers = chatMembers;
  }

  public String getMessageText()
  {
    return sender + ":\n" + messageText;
  }
  public Time getMessageTimeStamp()
  {
    return messageTimeStamp;
  }

  public ArrayList<LoginObject> getChatMembers()
  {
    return chatMembers;
  }

  @Override public String toString()
  {
    return "MessageObject{" + "messageText='" + messageText + '\''
        + ", messageTimeStamp=" + messageTimeStamp + ", sender='" + sender
        + '\'' + ", chatMembers=" + chatMembers + '}';
  }
}
