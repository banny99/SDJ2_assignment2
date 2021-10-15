package shared;

import java.util.ArrayList;

public class ConnectionsObject
{
  private ArrayList<LoginObject> currConnections;

  public ConnectionsObject(ArrayList<LoginObject> currConnections)
  {
    this.currConnections = currConnections;
  }

  @Override public String toString()
  {
    return "ConnectionsObject{" + "currConnections=" + currConnections + '}';
  }
}
