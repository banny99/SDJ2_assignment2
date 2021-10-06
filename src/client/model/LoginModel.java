package client.model;

import shared.Observable;

public interface LoginModel extends Observable
{
  void processLogin(String username, String password);
}
