package client.view.login;

import client.core.ViewHandler;
import client.view.chat.ChatViewModel;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements ViewController
{
  @FXML private Label messageLabel;
  @FXML private TextField tf_username;
  @FXML private TextField tf_password;
  @FXML private Button btn_login;

  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    viewHandler = vh;
    loginViewModel = lvm;

    //binding
//    lb_error.textProperty().bind(loginViewModel.getErrorLabelProperty());
  }


  public void onLoginBtnPressed(ActionEvent actionEvent)
  {
    String reply = loginViewModel.loginBtnPressed(tf_username.getText(), tf_password.getText());

    if (reply.equals("approved"))
    {
      viewHandler.openView("friendList");
    }
    else
    {
      messageLabel.setVisible(true);
      messageLabel.setText("! " + reply);
    }
  }


  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    //nothing
  }
}
