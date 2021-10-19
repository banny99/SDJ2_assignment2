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
    ArrayList<LoginObject> tempMembers = messageObject.getChatMembers();
    System.out.println(tempMembers + " :" + tempMembers.size());

    if (tempMembers.isEmpty())
    {
      for (ServerSocketThread t : listeners)
      {
        t.sendMessage(messageObject);
      }
    }
    else
    {
      System.out.println(listeners.size() + "," + activeUsers.size());

      for (LoginObject l : tempMembers)
      {
        for (int i=0; i<listeners.size(); i++)
        {
          if (l.equals(activeUsers.get(i)))
            listeners.get(i).sendMessage(messageObject);
        }
      }

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
