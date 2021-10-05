package client.core;

import client.model.LoginModel;
import client.model.LoginModelManager;

public class ModelFactory
{
  private LoginModel loginModel;

  public LoginModel getLoginModel()
  {
    if (loginModel==null)
      loginModel = new LoginModelManager();
    return loginModel;
  }
}
