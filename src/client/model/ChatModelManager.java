package client.model;

import client.networking.Client;
import shared.MessageObject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements Model
{

  private final Client client;
  private final PropertyChangeSupport changeSupport;

  public ChatModelManager(Client client)
  {
    this.client = client;
    changeSupport = new PropertyChangeSupport(this);

    //subscription
    this.client.addListener("chat", this::receiveMsg);
    this.client.addListener("cnct", this::updateActiveMembers);
  }


  @Override public void requestConnections()
  {
    client.requestConnections();
  }

  private void updateActiveMembers(PropertyChangeEvent evt)
  {
    changeSupport.firePropertyChange(evt);
  }


  public void receiveMsg(PropertyChangeEvent evt)
  {
    changeSupport.firePropertyChange(evt);
  }

  @Override public void processMessage(MessageObject msg)
  {
    client.sendMessage(msg);
  }


  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(eventName, listener);
  }
  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(eventName, listener);
  }


  @Override public void disconnect()
  {
    client.disconnect();
  }

}
