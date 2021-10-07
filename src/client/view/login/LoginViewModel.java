package client.view.login;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    loginModel.addListener("login", this::onLoginReply);
  }

  private void onLoginReply(PropertyChangeEvent evt)
  {
    errorLabel.set("back from server");
    changeSupport.firePropertyChange(evt);
  }

  public void loginBtnPressed(String username, String password)
  {
    loginModel.processLogin(username, password);
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
