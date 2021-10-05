package client.view.login;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
  }


  public void loginBtnPressed(String username, String password)
  {
    System.out.println(username + ":" + password +" ->from VM");
    loginModel.processLogin(username, password);
  }
}
