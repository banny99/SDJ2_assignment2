package shared;

import java.io.Serializable;
import java.sql.Time;

public class MessageObject implements Serializable
{
  private String messageText;
  private Time messageTimeStamp;
  private String username;

  public MessageObject(String msg, String username)
  {
    messageText = msg;
    messageTimeStamp = new Time(System.currentTimeMillis());
    this.username = username;
  }

  public String getMessageText()
  {
    return username + ":\n" + messageText;
  }
  public Time getMessageTimeStamp()
  {
    return messageTimeStamp;
  }

  @Override public String toString()
  {
    return "MessageObject{" + "messageText='" + messageText + '\'' + '}';
  }
}
