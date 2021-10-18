package client.model;

import shared.Observable;

public interface FriendListModel extends Observable
{
  void disconnect();
  void requestConnections();
}
