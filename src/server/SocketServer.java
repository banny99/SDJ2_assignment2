package server;

import shared.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer implements Observable
{

  private PropertyChangeSupport changeSupport;
  static ArrayList<Long> connectedUserIDs;
  static ArrayList<String> connectedUserNames;
  private static ArrayList<ServerSocketThread> connections;

  public SocketServer()
  {
    connections = new ArrayList<>();
    connectedUserNames = new ArrayList<>();
    connectedUserIDs = new ArrayList<>();
    changeSupport = new PropertyChangeSupport(this);
  }

  public void startServer()
  {
    try
    {
      ServerSocket welcomeSocket = new ServerSocket(4444);

      while (true){
        System.out.println("Server listening for client requests ...");

        Socket socket = welcomeSocket.accept();
        Thread tempThread = new ServerSocketThread(socket);
        tempThread.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  static void addLogedUser(ServerSocketThread t, Long id, String username){
    connections.add(t);
    connectedUserIDs.add(id);
    connectedUserNames.add(username);
    notifyConnectedClients();
  }
  static void addLogedUser(ServerSocketThread t, String username){
    connections.add(t);
    connectedUserNames.add(username);
    notifyConnectedClients();
  }

  private static void notifyConnectedClients()
  {
    for (ServerSocketThread t : connections)
      t.notifyClient();
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
