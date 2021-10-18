package client.view.friendlist;

import client.model.FriendListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.LoginObject;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class FriendListViewModel
{
  private FriendListModel friendListModel;
  private ObservableList<LoginObject> connectionsProperty;

  public FriendListViewModel(FriendListModel friendListModel)
  {
    this.friendListModel = friendListModel;
    connectionsProperty = FXCollections.observableArrayList();

    friendListModel.addListener("cnct", this::updateConnections);
  }

  private void updateConnections(PropertyChangeEvent evt)
  {
    connectionsProperty.setAll((ArrayList<LoginObject>) evt.getNewValue());
  }

  public ObservableList<LoginObject> getConnectionsProperty()
  {
    return connectionsProperty;
  }


  public void disconnect()
  {
    friendListModel.disconnect();
  }

  public void requestConnections()
  {
    friendListModel.requestConnections();
  }
}
