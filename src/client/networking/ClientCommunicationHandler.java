package client.networking;

import com.google.gson.Gson;
import shared.TransferObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientCommunicationHandler extends Thread
{
  private Socket socket;
  private ClientSocket client;
  private DataInputStream in;
  private DataOutputStream out;
  private Gson gson;

  public ClientCommunicationHandler(Socket socket, ClientSocket client)
  {
    this.socket = socket;
    this.client = client;
    gson = new Gson();

    try
    {
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
      System.out.println("connection established ...");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @Override public void run()
  {
    try
    {
      //infinite listening to server messages
      while (true)
      {
        String json = in.readUTF();
        TransferObject transferObject = gson.fromJson(json, TransferObject.class);
        client.receiveMessage(transferObject);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  public String logIn(TransferObject transferObject) throws IOException
  {
    out.writeUTF(gson.toJson(transferObject));
    return in.readUTF();
  }

  public void sendMessage(TransferObject transferObject)
  {
    try
    {
      out.writeUTF(gson.toJson(transferObject));
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("(Exception) message sending failed");
    }
  }


  public void disconnect(TransferObject transferObject) throws IOException
  {
    out.writeUTF(gson.toJson(transferObject));
  }

}

