package client.model;

import shared.LoginObject;
import shared.Observable;

public interface LoginModel extends Observable
{
  void processLogin(LoginObject lo);
  void disconnect();
}
