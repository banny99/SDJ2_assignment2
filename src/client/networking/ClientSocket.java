package client.networking;

import com.google.gson.Gson;
import shared.LoginObject;

import java.io.*;
import java.net.Socket;

public class ClientSocket implements Client
{

//  public static void main(String[] args)
//  {
//    login();
//  }
//
//  private static void login()
//  {
//    String username = "bb";
//    String password = "aaaa";
//
//    try
//    {
//      Socket clientSocket = new Socket("localhost", 4444);
////      ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
////      ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
//      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
//      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
//
//      System.out.println("connection established ...");
//
//      //send login request
//      Gson gson = new Gson();
//      LoginObject loginObject = new LoginObject(username, password);
//      String jsonRequest = gson.toJson(loginObject);
//      out.writeUTF(jsonRequest);
//      System.out.println(loginObject);
//
//      //receive message
//      String serverReply = in.readUTF();
//      System.out.println(serverReply);
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//  }

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
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}
