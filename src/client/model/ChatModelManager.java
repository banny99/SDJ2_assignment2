package client.model;

import client.networking.Client;

public class ChatModelManager implements Model
{

  private Client client;
  public ChatModelManager(Client client)
  {
    this.client = client;
  }

  @Override public String processLogin(String username, String password)
  {
    //do nothing
    return null;
  }

  @Override public void processMessage(String msg)
  {
    client.sendMessage(msg);
  }
}
