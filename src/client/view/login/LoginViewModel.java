package client.view.login;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class LoginViewModel
{

  private LoginModel loginModel;
  private StringProperty errorLabel;
  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  public LoginViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;

    errorLabel = new SimpleStringProperty();

    //subscription
    loginModel.addListener("login", this::onLoginReply);
  }

  private void onLoginReply(PropertyChangeEvent evt)
  {
    errorLabel.set("back from server");
  }

  public void loginBtnPressed(String username, String password)
  {
    loginModel.processLogin(username, password);
  }
}
