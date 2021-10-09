package client.model;

public interface Model
{
  String processLogin(String username, String password);
  void processMessage(String msg);
}
