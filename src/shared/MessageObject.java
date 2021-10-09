package shared;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageObject implements Serializable
{
  private String msg;
//  private Timestamp timestamp;

  public MessageObject(String msg)
  {
    this.msg = msg;
//    timestamp = Timestamp.now ...?
  }

  public String getMsg()
  {
    return msg;
  }

  @Override public String toString()
  {
    return "MessageObject{" + "msg='" + msg + '\'' + '}';
  }
}
