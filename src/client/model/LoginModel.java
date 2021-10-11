package client.model;

import shared.LoginObject;
import shared.Observable;

public interface LoginModel extends Observable
{
  String processLogin(String username, String password);
  String processLogin(LoginObject lo);
}
