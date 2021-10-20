package client.view.chat;

import client.core.ViewHandler;
import client.view.ViewController;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import shared.LoginObject;
import shared.MessageObject;
import java.sql.Time;
import java.util.ArrayList;

public class ChatViewController implements ViewController
{

  @FXML private ChoiceBox<LoginObject> activeMembersChoiceBox;
  @FXML private TableView<MessageObject> chatTable;
  @FXML private TableColumn<MessageObject, String> msgTC;
  @FXML private TableColumn<MessageObject, Time> timeTC;
  @FXML private TextArea chatTextField;
  private String chatTxtFieldDefaultPrompt = "write your message here ... (to everyone)";

  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;
  private LoginObject loginObject;

  private ArrayList<LoginObject> messageToList;


  public void init(ViewHandler vh, ChatViewModel cvm, LoginObject lo)
  {
    viewHandler = vh;
    chatViewModel = cvm;
    loginObject = lo;
    messageToList = new ArrayList<>();

    //binding
    msgTC.setText(loginObject.getUsername());
    msgTC.setCellValueFactory(new PropertyValueFactory<>("messageText"));
    timeTC.setCellValueFactory(new PropertyValueFactory<>("messageTimeStamp"));
    chatTable.setItems(chatViewModel.getTableContentProperty());

    //choiceBox - string converter
    activeMembersChoiceBox.setConverter(new StringConverter<LoginObject>()
    {
      @Override public String toString(LoginObject loginObject)
      {
        return loginObject.getUsername();
      }

      @Override public LoginObject fromString(String s)
      {
        return activeMembersChoiceBox.getItems().stream().filter(ap ->
            ap.getUsername().equals(s)).findFirst().orElse(null);
      }
    });
    //selection action
    activeMembersChoiceBox.setOnAction(e -> {
      LoginObject selectedItem = activeMembersChoiceBox.getSelectionModel().getSelectedItem();
      if (selectedItem!=null && !messageToList.contains(selectedItem))
      {
        messageToList.add(selectedItem);
        String msgTo = "write your message here ... (to: ";
        for (LoginObject l : messageToList) {msgTo += l.getUsername()+",";}
        msgTo += ")";
        chatTextField.setPromptText(msgTo);
      }
    });
    activeMembersChoiceBox.setItems(chatViewModel.getActiveMembersProperty());

    chatViewModel.requestConnections();
  }


  public void sendMsgButtonPressed(ActionEvent actionEvent)
  {
    String msg = chatTextField.getText();
    String sender = loginObject.getUsername();

    MessageObject messageObject = new MessageObject(msg, sender, messageToList);
    chatViewModel.sendMessage(messageObject);

    chatTextField.clear();
    chatTextField.setPromptText(chatTxtFieldDefaultPrompt);
    messageToList.clear();
    activeMembersChoiceBox.getSelectionModel().clearSelection();
  }


  @Override public void init(ViewHandler vh, LoginViewModel lvm)
  {
    //nothing
  }
  @Override public void init(ViewHandler vh, FriendListViewModel fvm, LoginObject loggedUser)
  {
    //nothing
  }


  @Override public void closeWindow()
  {
    chatViewModel.disconnect();
  }
}
