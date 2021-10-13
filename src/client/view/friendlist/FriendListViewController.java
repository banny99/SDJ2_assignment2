package client.view.friendlist;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.chat.ChatViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;
import shared.LoginObject;

public class FriendListViewController implements ViewController
{

  private ViewHandler viewHandler;
  private FriendListViewModel friendListViewModel;

  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    viewHandler = vh;
    friendListViewModel = fvm;
  }


  private void updateCurrFriendList()
  {
    friendListViewModel.requestCurrFriendList();
  }
  //btn-action
  public void updateTable(ActionEvent actionEvent)
  {
    updateCurrFriendList();
  }



  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, ChatViewModel cvm, LoginObject loginObject)
  {
    //nothing
  }

  @Override public void closeWindow()
  {
    //ToDo...
    //?? ...
  }
}
