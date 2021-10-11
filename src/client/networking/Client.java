package client.networking;

import shared.LoginObject;
import shared.MessageObject;
import shared.Observable;

public interface Client extends Observable
{
  String login(LoginObject lo);
  void sendMessage(MessageObject msg);
  void requestCurrFriendList();
}
