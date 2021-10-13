package client.networking;

import com.google.gson.Gson;
import shared.DeniedLoginException;
import shared.LoginObject;
import shared.MessageObject;
import shared.TransferObject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class ClientSocket implements Client
{
  private final PropertyChangeSupport changeSupport;
  private Gson gson;

  private Socket clientSocket;
  private ClientCommunicationHandler handler;
  

  public ClientSocket()
  {
    changeSupport = new PropertyChangeSupport(this);
    gson = new Gson();

    try
    {
      clientSocket = new Socket("localhost", 4444);
      handler = new ClientCommunicationHandler(clientSocket, this);
      handler.setDaemon(true);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @Override public void login(LoginObject lo)
  {
    String serverReply;
    try
    {
      TransferObject transferObject = new TransferObject("LO", gson.toJson(lo));
      serverReply = handler.logIn(transferObject);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      serverReply = "denied";
    }

    //when login approved ->run communication thread
    if (serverReply.equals("approved"))
    {
      handler.start();
    }
    else
    {
      throw new DeniedLoginException(serverReply);
    }
  }


  @Override public void sendMessage(MessageObject messageObject)
  {
    String jsonMsg = gson.toJson(messageObject);
    TransferObject transferObject = new TransferObject("MSG", jsonMsg);
    handler.sendMessage(transferObject);
  }

  @Override public void receiveMessage(TransferObject transferObject)
  {
    MessageObject messageObject = gson.fromJson(transferObject.getContentClass(), MessageObject.class);
    System.out.println("received: " + messageObject);
    changeSupport.firePropertyChange("chat", null, messageObject);
  }


  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(listener);
  }
  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(listener);
  }


  @Override public void disconnect()
  {
    try
    {
      handler.disconnect(new TransferObject("disconnect", ""));
//      clientSocket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("client socket disconnecting failed");
    }
  }
}
