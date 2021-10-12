package server;

import shared.MessageObject;

import java.util.ArrayList;

public class ConnectionPool
{
  private ArrayList<ServerSocketThread> connections = new ArrayList<>();

  public void broadcastMessages(MessageObject messageObject)
  {
    for (ServerSocketThread t : connections)
    {
      t.sendMessage(messageObject);
    }
  }

  public void addConnection(ServerSocketThread tempThread)
  {
    connections.add(tempThread);
  }

  public void removeConnection(ServerSocketThread tempThread)
  {
    connections.remove(tempThread);
  }
}
