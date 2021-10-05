package client.core;

import client.view.login.LoginViewModel;

public class ViewModelFactory
{
  private ModelFactory modelFactory;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }

  private LoginViewModel loginViewModel;
  public LoginViewModel getLoginViewModel()
  {
    if (this.loginViewModel==null)
      loginViewModel = new LoginViewModel(modelFactory.getLoginModel());
    return loginViewModel;
  }

}
