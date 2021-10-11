package shared;

import java.util.ArrayList;

public class FriendListObject
{
  private ArrayList<Long> friendList;
  public FriendListObject(ArrayList<Long> friendList)
  {
    this.friendList = friendList;
  }

  public ArrayList<Long> getFriendList()
  {
    return friendList;
  }
}
