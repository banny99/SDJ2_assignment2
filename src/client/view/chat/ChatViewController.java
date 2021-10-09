package client.view.chat;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ChatViewController implements ViewController
{

  @FXML private TextArea chatTextField;

  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;

  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    viewHandler = vh;
    chatViewModel = cvm;
  }

  public void sendMsgButtonPressed(ActionEvent actionEvent)
  {
    chatViewModel.sendMessage(chatTextField.getText());
    chatTextField.clear();
  }


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    //nothing
  }
}
