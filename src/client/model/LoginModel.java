package client.model;

import shared.Observable;

public interface LoginModel extends Observable
{
  String processLogin(String username, String password);
}
