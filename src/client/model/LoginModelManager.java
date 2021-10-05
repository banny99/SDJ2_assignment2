package client.model;

import client.networking.Client;

public class LoginModelManager implements LoginModel
{
  private Client client;

  public LoginModelManager(Client c)
  {
    client = c;
  }

  @Override public void processLogin(String username, String password)
  {
    client.login(username, password);
  }
}
