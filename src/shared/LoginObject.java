package shared;

import java.io.Serializable;

public class LoginObject implements Serializable
{
  private String username;
  private String password;

  public LoginObject(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  @Override public String toString()
  {
    return "LoginObject{" + "username='" + username + '\'' + ", password='"
        + password + '\'' + '}';
  }
}
