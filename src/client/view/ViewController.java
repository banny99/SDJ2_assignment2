package client.view;

import client.core.ViewHandler;
import client.view.chat.ChatViewModel;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewModel;

public interface ViewController
{
  void init(ViewHandler vh, LoginViewModel lvm);
  void init(ViewHandler vh, ChatViewModel cvm);
  void init(ViewHandler vh, FriendListViewModel fvm);
}
