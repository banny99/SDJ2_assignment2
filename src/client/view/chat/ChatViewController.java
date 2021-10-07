package client.view.chat;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;

public class ChatViewController implements ViewController
{

  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;

  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    viewHandler = vh;
    chatViewModel = cvm;
  }

  public void sendMsgButtonPressed(ActionEvent actionEvent)
  {
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
