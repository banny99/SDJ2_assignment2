package client.networking;

import com.google.gson.Gson;
import shared.FriendListObject;
import shared.LoginObject;
import shared.MessageObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class ClientSocket implements Client
{
  private final PropertyChangeSupport changeSupport;

  private Socket clientSocket;
  private DataInputStream in;
  private DataOutputStream out;

  private Gson gson;

  public ClientSocket()
  {
    changeSupport = new PropertyChangeSupport(this);
    gson = new Gson();

    try
    {
      clientSocket = new Socket("localhost", 4444);
      in = new DataInputStream(clientSocket.getInputStream());
      out = new DataOutputStream(clientSocket.getOutputStream());
      System.out.println("connection established ...");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @Override public String login(LoginObject lo)
  {
    String serverReply = "denied";

    try
    {
      //send login request
      String jsonRequest = gson.toJson(lo);
      out.writeUTF(jsonRequest);

      //receive logIn message
      serverReply = in.readUTF();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return serverReply;
  }


  @Override public void sendMessage(MessageObject messageObject)
  {
    try
    {
      String jsonRequest = gson.toJson(messageObject);
      out.writeUTF(jsonRequest);

      String jsonReply = in.readUTF();
      messageObject = gson.fromJson(jsonReply, MessageObject.class);
      System.out.println(messageObject);
      changeSupport.firePropertyChange("chat", null, messageObject);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void requestCurrFriendList()
  {
    try
    {
      String friendListRequest = "friendListRequest";
      out.writeUTF(friendListRequest);

      String jsonFriendList = in.readUTF();
      FriendListObject tempFriendListObject = gson.fromJson(jsonFriendList, FriendListObject.class);
      System.out.println(tempFriendListObject + "from client");
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
