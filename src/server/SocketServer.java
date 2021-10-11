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
  private ArrayList<Long> connectedUsers;
  public SocketServer()
  {
    connectedUsers = new ArrayList<>();
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
        Thread tempThread = new Thread(new ServerSocketThread(socket, connectedUsers));
        tempThread.start();

        connectedUsers.add(tempThread.getId());
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
