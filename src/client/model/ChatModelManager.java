package client.model;

import client.networking.Client;
import shared.MessageObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements Model
{

  private Client client;
  private PropertyChangeSupport changeSupport;

  public ChatModelManager(Client c)
  {
    client = c;
    changeSupport = new PropertyChangeSupport(this);

    //subscription
    client.addListener("chat", this::receiveMsg);
  }

  public void receiveMsg(PropertyChangeEvent evt)
  {
    changeSupport.firePropertyChange(evt);
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
  @Override public void processMessage(MessageObject msg)
  {
    client.sendMessage(msg);
  }



  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(listener);
  }
  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(listener);
  }
}
