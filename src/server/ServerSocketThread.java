package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.FriendListObject;
import shared.LoginObject;
import shared.MessageObject;
import shared.TransferObject;
import java.io.*;
import java.net.Socket;

public class ServerSocketThread extends Thread
{
  private DataInputStream inputStream;
  private DataOutputStream outputStream;
  private Gson gson;

  public ServerSocketThread(Socket socket)
  {
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

    friendList();
//    chat();
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
        TransferObject temp = gson.fromJson(jsonRequest, TransferObject.class);
        receivedLoginObject = gson.fromJson(temp.getContentClass(), LoginObject.class);
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

    SocketServer.addLogedUser(this, receivedLoginObject.getUsername());
  }


  private void friendList()
  {
    System.out.println(" ->friend list");

    while (true){
      try
      {
        String request = inputStream.readUTF();
        System.out.println(request);

        FriendListObject tempFriendListObject = new FriendListObject(SocketServer.connectedUserNames);
        String jsonFriendList = gson.toJson(tempFriendListObject);
        outputStream.writeUTF(jsonFriendList);
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
        MessageObject messageObject = gson.fromJson(jsonRequest, MessageObject.class);
        System.out.println(messageObject);

        outputStream.writeUTF(gson.toJson(messageObject));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void notifyClient()
  {
    try
    {
      FriendListObject tempFriendListObject = new FriendListObject(SocketServer.connectedUserNames);
      String jsonFriendList = gson.toJson(tempFriendListObject);
      outputStream.writeUTF(jsonFriendList);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
