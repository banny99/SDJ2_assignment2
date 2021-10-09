package client.networking;

import com.google.gson.Gson;
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

  @Override public String login(String username, String password)
  {
    String serverReply = "denied";

    try
    {
      //send login request
      LoginObject loginObject = new LoginObject(username, password);
      String jsonRequest = gson.toJson(loginObject);
      out.writeUTF(jsonRequest);

      //receive message
      System.out.println(loginObject);
      serverReply = in.readUTF();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return serverReply;
  }

  @Override public void sendMessage(String msg)
  {
    try
    {
      MessageObject messageObject = new MessageObject(msg);
      String jsonRequest = gson.toJson(messageObject);
      out.writeUTF(jsonRequest);

      String jsonReply = in.readUTF();
      messageObject = gson.fromJson(jsonReply, MessageObject.class);
      System.out.println(messageObject);
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
