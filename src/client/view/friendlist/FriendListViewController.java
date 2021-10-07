package client.view.friendlist;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.chat.ChatViewModel;
import client.view.login.LoginViewModel;

public class FriendListViewController implements ViewController
{

  private ViewHandler viewHandler;
  private FriendListViewModel friendListViewModel;

  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    viewHandler = vh;
    friendListViewModel = fvm;
  }

  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    //nothing
  }
}
