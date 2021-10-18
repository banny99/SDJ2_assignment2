package shared;

import java.io.Serializable;
import java.sql.Time;

public class LoginObject implements Serializable
{
  private String username;
  private String password;
  private Time timeStamp;

  public LoginObject(String username, String password)
  {
    this.username = username;
    this.password = password;
    timeStamp = new Time(System.currentTimeMillis());
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public Time getTimeStamp()
  {
    return timeStamp;
  }

  @Override public String toString()
  {
    return "LoginObject{" + "username='" + username + '\'' + ", password='"
        + password + '\'' + '}';
  }
}
