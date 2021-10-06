package client.networking;

import com.google.gson.Gson;
import shared.LoginObject;
import shared.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class ClientSocket implements Client
{
  private final PropertyChangeSupport changeSupport;

  public ClientSocket()
  {
    changeSupport = new PropertyChangeSupport(this);
  }

  @Override public void login(String username, String password)
  {
    try
    {
      Socket clientSocket = new Socket("localhost", 4444);
//      ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
//      ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

      System.out.println("connection established ...");

      //send login request
      Gson gson = new Gson();
      LoginObject loginObject = new LoginObject(username, password);
      String jsonRequest = gson.toJson(loginObject);
      out.writeUTF(jsonRequest);
      System.out.println(loginObject);

      //receive message
      String serverReply = in.readUTF();
      System.out.println(serverReply);

      changeSupport.firePropertyChange("login", null, serverReply);
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
