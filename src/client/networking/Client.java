package client.networking;

import shared.Observable;

public interface Client extends Observable
{
  void login(String username, String password);
}
