package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.LoginObject;

import java.io.*;
import java.net.Socket;

public class ServerSocketThread implements Runnable
{
  private DataInputStream inputStream;
  private DataOutputStream outputStream;

  public ServerSocketThread(Socket socket)
  {
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
    Gson gson = new Gson();

    try
    {
      String jsonRequest = inputStream.readUTF();
      LoginObject receivedLoginObject = gson.fromJson(jsonRequest, LoginObject.class);
      System.out.println(receivedLoginObject);

      String reply;
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
