package client.model;

import shared.LoginObject;
import shared.Observable;

public interface LoginModel extends Observable
{
  String processLogin(LoginObject lo);
}
