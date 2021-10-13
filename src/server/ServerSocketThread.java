package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
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
      System.out.println("user logged in");
      //subscription to connectionPool
      connectionPool.addListener("msg", listener);

      chat();
      System.out.println("client disconnected : " + socket.getPort());
    }

  }


  private boolean login()
  {
    String reply = "";
    LoginObject receivedLoginObject = null;

    while (!reply.equals("approved"))
    {
      try
      {
        String jsonRequest = inputStream.readUTF();
        TransferObject transferObject = gson.fromJson(jsonRequest, TransferObject.class);

        if (transferObject.getType().equals("LO"))
        {
          receivedLoginObject = gson.fromJson(transferObject.getContentClass(), LoginObject.class);
          System.out.println(receivedLoginObject);

          try
          {
            Username username = new Username(receivedLoginObject.getUsername());
            Password password = new Password(receivedLoginObject.getPassword());
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
          connectionPool.broadcastMessages(messageObject);
        }
        else
        {
          connectionPool.removeListener("msg", listener);
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
}
