package client.networking;

import com.google.gson.Gson;
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

  private ClientCommunicationHandler handler;
  

  public ClientSocket()
  {
    changeSupport = new PropertyChangeSupport(this);
    gson = new Gson();

    try
    {
      Socket socket = new Socket("localhost", 4444);
      handler = new ClientCommunicationHandler(socket, this);
      handler.setDaemon(true);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @Override public String login(LoginObject lo)
  {

    TransferObject transferObject = new TransferObject("LO", gson.toJson(lo));
    String serverReply = handler.logIn(transferObject);

    //when login approved ->run communication thread
    if (serverReply.equals("approved"))
    {
      handler.start();
    }

    return serverReply;
  }



  @Override public void sendMessage(MessageObject messageObject)
  {
    String jsonMsg = gson.toJson(messageObject);
    TransferObject transferObject = new TransferObject("MSG", jsonMsg);
    handler.sendMessage(transferObject);
  }

  public void receiveMessage(TransferObject transferObject)
  {
    MessageObject messageObject = gson.fromJson(transferObject.getContentClass(), MessageObject.class);
    System.out.println("received: " + messageObject);
    changeSupport.firePropertyChange("chat", null, messageObject);
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
