package server;

import shared.ConnectionsObject;
import shared.LoginObject;
import shared.MessageObject;
import shared.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

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


  private ArrayList<ServerSocketThread> listeners = new ArrayList<>();
  private ArrayList<LoginObject> activeUsers = new ArrayList<>();

  public void addListener(ServerSocketThread t, LoginObject user)
  {
    listeners.add(t);
    activeUsers.add(user);

    broadcastActiveUsers();
  }
  public void removeListener(ServerSocketThread t)
  {
    activeUsers.remove(listeners.indexOf(t));
    listeners.remove(t);

    broadcastActiveUsers();
  }

  public void broadcastMessage(MessageObject messageObject)
  {
    for (ServerSocketThread t : listeners)
    {
      t.sendMessage(messageObject);
    }
  }


  public void broadcastActiveUsers()
  {
    ArrayList<LoginObject> connectionsCopy = new ArrayList<>(activeUsers);
    for (ServerSocketThread t : listeners)
    {
      t.updateConnections(new ConnectionsObject(connectionsCopy));
    }
  }

  public void broadcastActiveUsers(ServerSocketThread t)
  {
    ArrayList<LoginObject> connectionsCopy = new ArrayList<>(activeUsers);
    t.updateConnections(new ConnectionsObject(connectionsCopy));
  }
}
