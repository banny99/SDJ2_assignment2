package client.networking;

import shared.Observable;

public interface Client extends Observable
{
  String login(String username, String password);
}
