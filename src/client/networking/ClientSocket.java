package client.networking;

import shared.LoginObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket implements Client
{
  private Socket clientSocket;
  private ObjectInputStream inputStream;
  private ObjectOutputStream outputStream;

  public ClientSocket(String host, int port)
  {
    try
    {
      clientSocket = new Socket(host, port);
      System.out.println("connection established");

      inputStream = new ObjectInputStream(clientSocket.getInputStream());
      outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void login(String username, String password)
  {
    LoginObject loginObject = new LoginObject(username, password);
    try
    {
      outputStream.writeObject(loginObject);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
