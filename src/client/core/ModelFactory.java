package client.core;

import client.model.LoginModel;
import client.model.LoginModelManager;

public class ModelFactory
{
  private ClientFactory clientFactory;
  public ModelFactory(ClientFactory cf)
  {
    clientFactory = cf;
  }

  private LoginModel loginModel;

  public LoginModel getLoginModel()
  {
    if (loginModel==null)
      loginModel = new LoginModelManager(clientFactory.getClient());
    return loginModel;
  }
}
