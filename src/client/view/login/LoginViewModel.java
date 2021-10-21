package client.view.login;

import client.model.LoginModel;
import shared.LoginObject;
import shared.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements Observable
{

  private final PropertyChangeSupport changeSupport;
  private final LoginModel loginModel;


  public LoginViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;
    changeSupport = new PropertyChangeSupport(this);
  }


  public void loginBtnPressed(LoginObject lo)
  {
    loginModel.processLogin(lo);
  }



  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(listener);
  }

  public void disconnect()
  {
    loginModel.disconnect();
  }
}
