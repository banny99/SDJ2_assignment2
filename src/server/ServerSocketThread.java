package server;

import com.google.gson.Gson;
import server.model.Password;
import server.model.Username;
import shared.LoginObject;
import shared.MessageObject;

import java.io.*;
import java.net.Socket;

public class ServerSocketThread implements Runnable
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

    chat();
  }

  private void chat()
  {
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
