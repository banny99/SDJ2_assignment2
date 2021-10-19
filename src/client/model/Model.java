package client.model;

import shared.MessageObject;
import shared.Observable;

import java.beans.PropertyChangeEvent;

public interface Model extends Observable
{
  void processMessage(MessageObject msg);
  void receiveMsg(PropertyChangeEvent evt);

  void disconnect();

  void requestConnections();
}
