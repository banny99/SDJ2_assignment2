package client.view.login;

import client.core.ViewHandler;
import client.view.chat.ChatViewModel;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements ViewController
{
  @FXML private Label lb_error;
  @FXML private TextField tf_username;
  @FXML private TextField tf_password;
  @FXML private Button btn_login;

  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    viewHandler = vh;
    loginViewModel = lvm;

    lb_error.textProperty().bind(loginViewModel.getErrorLabelProperty());
  }

  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    //nothing
  }

  public void onLoginBtnPressed(ActionEvent actionEvent)
  {
    loginViewModel.loginBtnPressed(tf_username.getText(), tf_password.getText());
  }
}
