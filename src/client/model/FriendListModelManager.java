package client.model;

import client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FriendListModelManager implements FriendListModel
{
  private Client client;
  private PropertyChangeSupport changeSupport;

  public FriendListModelManager(Client client)
  {
    this.client = client;
    changeSupport = new PropertyChangeSupport(this);

    //subscription
    client.addListener("cnct", this::updateConnections);
  }

  private void updateConnections(PropertyChangeEvent evt)
  {
    changeSupport.firePropertyChange(evt);
  }

  @Override public void requestConnections()
  {
    client.requestConnections();
  }



  @Override public void disconnect()
  {
    client.disconnect();
  }


  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(eventName, listener);
  }
  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(eventName, listener);
  }
}
