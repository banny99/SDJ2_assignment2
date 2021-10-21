package client.model;

import client.networking.Client;
import shared.MessageObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FriendListModelManager implements Model
{
  private final Client client;
  private final PropertyChangeSupport changeSupport;

  public FriendListModelManager(Client client)
  {
    this.client = client;
    changeSupport = new PropertyChangeSupport(this);

    //subscription
    this.client.addListener("cnct", this::updateConnections);
  }

  @Override public void requestConnections()
  {
    client.requestConnections();
  }
  private void updateConnections(PropertyChangeEvent evt)
  {
    changeSupport.firePropertyChange(evt);
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


  @Override public void processMessage(MessageObject msg)
  {
    //nothing
  }
  @Override public void receiveMsg(PropertyChangeEvent evt)
  {
    //nothing
  }
}
