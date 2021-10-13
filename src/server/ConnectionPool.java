package server;

import shared.MessageObject;
import shared.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConnectionPool implements Observable
{
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);


  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(eventName, listener);
  }
  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(eventName, listener);
  }

  public void broadcastMessages(MessageObject messageObject)
  {
    changeSupport.firePropertyChange("msg", null, messageObject);
  }

}
