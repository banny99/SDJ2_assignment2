package client.networking;

import shared.LoginObject;
import shared.MessageObject;
import shared.Observable;
import shared.TransferObject;

public interface Client extends Observable
{
  String login(LoginObject lo);
  void sendMessage(MessageObject msg);
  void receiveMessage(TransferObject transferObject);
}
