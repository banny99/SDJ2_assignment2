package client.model;

import shared.LoginObject;
import shared.Observable;

public interface LoginModel
{
  void processLogin(LoginObject lo);
  void disconnect();
}
