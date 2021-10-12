package client.model;

import client.networking.Client;

public class FriendListModelManager implements FriendListModel
{
  private Client client;

  public FriendListModelManager(Client client)
  {
    this.client = client;
  }

  @Override public void requestCurrFriendList()
  {
    //nothing
  }
}
