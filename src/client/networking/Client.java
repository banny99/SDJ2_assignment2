package client.networking;

import shared.LoginObject;
import shared.MessageObject;
import shared.Observable;

public interface Client extends Observable
{
  String login(String username, String password);
  String login(LoginObject lo);
  void sendMessage(String msg);
  void sendMessage(MessageObject msg);
}
