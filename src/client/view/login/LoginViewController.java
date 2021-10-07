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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    loginViewModel.addListener("login", new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        System.out.println("viewController: message from server: " + evt.getNewValue());
        if (evt.getNewValue().equals("approved")){
          viewHandler.openView("friendList");
        }
      }
    });

    //binding
    lb_error.textProperty().bind(loginViewModel.getErrorLabelProperty());
  }


  public void onLoginBtnPressed(ActionEvent actionEvent)
  {
    loginViewModel.loginBtnPressed(tf_username.getText(), tf_password.getText());
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
