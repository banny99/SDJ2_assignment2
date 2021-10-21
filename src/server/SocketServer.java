package server;

import shared.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Observable
{

  private final PropertyChangeSupport changeSupport;
  private final ConnectionPool connectionPool;

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
