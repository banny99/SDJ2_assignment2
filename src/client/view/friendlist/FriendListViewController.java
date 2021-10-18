package client.view.friendlist;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.chat.ChatViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.LoginObject;

public class FriendListViewController implements ViewController
{

  @FXML private TableView activeConnectionsTab;
  @FXML private TableColumn onlineFriendsCol;
  @FXML private TableColumn timeCol;

  private ViewHandler viewHandler;
  private FriendListViewModel friendListViewModel;


  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    viewHandler = vh;
    friendListViewModel = fvm;

    onlineFriendsCol.setCellValueFactory(new PropertyValueFactory<LoginObject, String>("username"));
    timeCol.setCellValueFactory(new PropertyValueFactory<LoginObject, String>("timeStamp"));
    activeConnectionsTab.setItems(friendListViewModel.getConnectionsProperty());

    requestConnections();
  }

  private void requestConnections()
  {
    friendListViewModel.requestConnections();
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
    friendListViewModel.disconnect();
  }
}
