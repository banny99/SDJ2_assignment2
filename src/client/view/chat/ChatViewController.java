package client.view.chat;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.LoginObject;
import shared.MessageObject;

import java.sql.Time;

public class ChatViewController implements ViewController
{

  @FXML private TableView<MessageObject> chatTable;
  @FXML private TableColumn<MessageObject, String> msgTC;
  @FXML private TableColumn<MessageObject, Time> timeTC;

  @FXML private TextArea chatTextField;

  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;
  private LoginObject loginObject;

  @Override public void init(ViewHandler vh, ChatViewModel cvm)
  {
    viewHandler = vh;
    chatViewModel = cvm;

    //binding
    msgTC.setCellValueFactory(new PropertyValueFactory<>("messageText"));
    timeTC.setCellValueFactory(new PropertyValueFactory<>("messageTimeStamp"));
    chatTable.setItems(chatViewModel.getTableContentProperty());
  }

  public void init(ViewHandler vh, ChatViewModel cvm, LoginObject lo)
  {
    viewHandler = vh;
    chatViewModel = cvm;
    loginObject = lo;

    //binding
    msgTC.setText(loginObject.getUsername());
    System.out.println(loginObject.getUsername());
    msgTC.setCellValueFactory(new PropertyValueFactory<>("messageText"));
    timeTC.setCellValueFactory(new PropertyValueFactory<>("messageTimeStamp"));
    chatTable.setItems(chatViewModel.getTableContentProperty());
  }

  public void sendMsgButtonPressed(ActionEvent actionEvent)
  {
    MessageObject messageObject = new MessageObject(chatTextField.getText(), loginObject.getUsername());
//    chatViewModel.sendMessage(chatTextField.getText());
    chatViewModel.sendMessage(messageObject);
    chatTextField.clear();
  }


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm,
      LoginObject loginObject)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, LoginViewModel lvm,
      LoginObject loginObject)
  {
    //nothing
  }
}
