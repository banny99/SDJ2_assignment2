package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.LoginObject;
import shared.MessageObject;
import shared.TransferObject;
import java.io.*;
import java.net.Socket;

public class ServerSocketThread extends Thread
{
  private DataInputStream inputStream;
  private DataOutputStream outputStream;
  private ConnectionPool connectionPool;
  private Gson gson;

  public ServerSocketThread(Socket socket, ConnectionPool connectionPool)
  {
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

    login();
    System.out.println("user logged in");

    chat();
  }


  private void login()
  {
    String reply = "";
    LoginObject receivedLoginObject = null;

    while (!reply.equals("approved"))
    {
      try
      {
        String jsonRequest = inputStream.readUTF();
        TransferObject transferObject = gson.fromJson(jsonRequest, TransferObject.class);
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
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }


  private void chat()
  {
    System.out.println(" ->chat");
    while (true){
      try
      {
        String jsonRequest = inputStream.readUTF();
        TransferObject transferObject = gson.fromJson(jsonRequest, TransferObject.class);
        MessageObject messageObject = gson.fromJson(transferObject.getContentClass(), MessageObject.class);
        System.out.println(messageObject);

        connectionPool.broadcastMessages(messageObject);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }


  public void sendMessage(MessageObject messageObject)
  {
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
