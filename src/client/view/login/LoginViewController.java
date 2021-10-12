package client.view.login;

import client.core.ViewHandler;
import client.view.chat.ChatViewModel;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.LoginObject;

public class LoginViewController implements ViewController
{
  @FXML private Label messageLabel;
  @FXML private TextField tf_username;
  @FXML private TextField tf_password;

  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    viewHandler = vh;
    loginViewModel = lvm;
  }


  public void onLoginBtnPressed(ActionEvent actionEvent)
  {
    LoginObject loginObject = new LoginObject(tf_username.getText(), tf_password.getText());
    String reply = loginViewModel.loginBtnPressed(loginObject);

    if (reply.equals("approved"))
    {
      System.out.println("logged in");

      //friend list
//      viewHandler.openFriendListView(loginObject);

      //chat
      viewHandler.openChatView(loginObject);
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
  @Override public void init(ViewHandler vh, LoginViewModel lvm, LoginObject loginObject)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm, LoginObject loginObject)
  {
    //nothing
  }
}
