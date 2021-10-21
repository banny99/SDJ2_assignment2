package client.core;

import client.model.*;

public class ModelFactory
{
  private final ClientFactory clientFactory;
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

  private Model chatModel;
  public Model getChatModel()
  {
    if (chatModel == null)
      chatModel = new ChatModelManager(clientFactory.getClient());
    return chatModel;
  }

  private Model model;
  public Model getModel()
  {
    if (model == null)
      model = new FriendListModelManager(clientFactory.getClient());
    return model;
  }
}
