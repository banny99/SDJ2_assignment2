package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.ConnectionsObject;
import shared.LoginObject;
import shared.MessageObject;
import shared.TransferObject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.Socket;

public class ServerSocketThread extends Thread
{
  private Socket socket;
  private DataInputStream inputStream;
  private DataOutputStream outputStream;
  private ConnectionPool connectionPool;
  private Gson gson;

  private PropertyChangeListener listener = this::sendMessage;

  private LoginObject loggedUser;

  public ServerSocketThread(Socket socket, ConnectionPool connectionPool)
  {
    this.socket = socket;
    this.connectionPool = connectionPool;
    gson = new Gson();

    try
    {
      inputStream = new DataInputStream(socket.getInputStream());
      outputStream = new DataOutputStream(socket.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    System.out.println("Server communication-socket running ...");

    if (login())
    {
      connectionPool.addListener(this, loggedUser);
      System.out.println("user logged in");

      chat();
      System.out.println("client disconnected : " + socket.getPort());
    }

  }


  private boolean login()
  {
    String reply = "";

    while (!reply.equals("approved"))
    {
      try
      {
        String jsonRequest = inputStream.readUTF();
        TransferObject transferObject = gson.fromJson(jsonRequest, TransferObject.class);

        if (transferObject.getType().equals("LO"))
        {
          loggedUser = gson.fromJson(transferObject.getContentClass(), LoginObject.class);
          System.out.println(loggedUser);

          try
          {
            Username username = new Username(loggedUser.getUsername());
            Password password = new Password(loggedUser.getPassword());
            reply = "approved";
          }
          catch (IllegalArgumentException e){
            reply = e.getMessage();
          }

          outputStream.writeUTF(reply);
          System.out.println(reply);
        }

        else
        {
          return false;
        }

      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return true;
  }


  private void chat()
  {
    System.out.println(" ->chat");
    try
    {
      while (true)
      {
        String jsonRequest = inputStream.readUTF();
        TransferObject transferObject = gson.fromJson(jsonRequest, TransferObject.class);

        if (transferObject.getType().equals("MSG"))
        {
          MessageObject messageObject = gson.fromJson(transferObject.getContentClass(), MessageObject.class);
          System.out.println(messageObject);
//          connectionPool.broadcastMessages(messageObject);
          connectionPool.broadcastMessage(messageObject);
        }
        else
        {
//          connectionPool.removeListener("msg", listener);
          connectionPool.removeListener(this);
          break;
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  //subscriber's "update()" method
  private void sendMessage(PropertyChangeEvent evt)
  {
    MessageObject messageObject = (MessageObject) evt.getNewValue();
    TransferObject transferObject = new TransferObject("MSG", gson.toJson(messageObject));
    try
    {
      outputStream.writeUTF(gson.toJson(transferObject));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(MessageObject messageObject)
  {
    try
    {
      TransferObject transferObject = new TransferObject("MSG", gson.toJson(messageObject));
      outputStream.writeUTF(gson.toJson(transferObject));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void updateConnections(ConnectionsObject connectionsObject)
  {
    try
    {
      TransferObject transferObject = new TransferObject("CNCT", gson.toJson(connectionsObject));
      outputStream.writeUTF(gson.toJson(transferObject));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
