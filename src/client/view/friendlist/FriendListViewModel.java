package client.view.friendlist;

import client.model.FriendListModel;

public class FriendListViewModel
{
  private FriendListModel friendListModel;
  public FriendListViewModel(FriendListModel friendListModel)
  {
    this.friendListModel = friendListModel;
  }

  public void requestCurrFriendList()
  {
    friendListModel.requestCurrFriendList();
  }
}
