package client.view.login;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.LoginObject;
import shared.Observable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements Observable
{

  private PropertyChangeSupport changeSupport;

  private LoginModel loginModel;
  private StringProperty errorLabel;
  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  public LoginViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;
    changeSupport = new PropertyChangeSupport(this);

    errorLabel = new SimpleStringProperty();

    //subscription
//    loginModel.addListener("login", this::onLoginReply);
  }


  public String loginBtnPressed(String username, String password)
  {
    return loginModel.processLogin(username, password);
  }
  public String loginBtnPressed(LoginObject lo)
  {
    return loginModel.processLogin(lo);
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
}
