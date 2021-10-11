package shared;

import java.util.ArrayList;

public class FriendListObject
{
  private ArrayList<String> friendListNames;
  public FriendListObject(ArrayList<String> friendList)
  {
    this.friendListNames = friendList;
  }

  public ArrayList<String> getFriendList()
  {
    return friendListNames;
  }

  @Override public String toString()
  {
    return "FriendListObject{" + "friendListNames=" + friendListNames + '}';
  }
}
