package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{

  public void startServer()
  {
    try
    {
      ServerSocket welcomeSocket = new ServerSocket(4444);

      while (true){
        Socket socket = welcomeSocket.accept();
        new Thread(new ServerSocketThread(socket)).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
