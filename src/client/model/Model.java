package client.model;

import shared.MessageObject;
import shared.Observable;

import java.beans.PropertyChangeEvent;

public interface Model extends Observable
{
  String processLogin(String username, String password);
  void processMessage(String msg);
  void receiveMsg(PropertyChangeEvent evt);
  void processMessage(MessageObject msg);
}
