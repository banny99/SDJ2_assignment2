package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.FriendListObject;
import shared.LoginObject;
import shared.MessageObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketThread implements Runnable
{
  private DataInputStream inputStream;
  private DataOutputStream outputStream;
  private Gson gson;
  private ArrayList<Long> connectedUsers;

  public ServerSocketThread(Socket socket, ArrayList<Long> connectedUsers)
  {
    gson = new Gson();
    this.connectedUsers = connectedUsers;
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

  private void friendList()
  {
    System.out.println(" ->friend list");

    while (true){
      try
      {
        String request = inputStream.readUTF();
        System.out.println(request);

        FriendListObject tempFriendListObject = new FriendListObject(connectedUsers);
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

  private void login()
  {
    String reply = "";
    while (!reply.equals("approved"))
    {
      try
      {
        String jsonRequest = inputStream.readUTF();
        LoginObject receivedLoginObject = gson.fromJson(jsonRequest, LoginObject.class);
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

}
