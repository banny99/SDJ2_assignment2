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
  private ConnectionPool connectionPool;

  public SocketServer()
  {
    changeSupport = new PropertyChangeSupport(this);

    connectionPool = new ConnectionPool();
  }

  public void startServer()
  {
    try
    {
      ServerSocket welcomeSocket = new ServerSocket(4444);

      while (true){
        System.out.println("Server listening for client requests ...");

        Socket socket = welcomeSocket.accept();
        ServerSocketThread tempThread = new ServerSocketThread(socket, connectionPool);
        connectionPool.addConnection(tempThread);
        tempThread.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
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
