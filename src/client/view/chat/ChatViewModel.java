package client.view.chat;

import client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.MessageObject;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private final Model chatModelManager;
  private final ObservableList<MessageObject> tableContentProperty;

  public ChatViewModel(Model cmm)
  {
    chatModelManager = cmm;
    tableContentProperty = FXCollections.observableArrayList();

    //subscription
    chatModelManager.addListener("chat", this::receiveMsg);
  }

  private void receiveMsg(PropertyChangeEvent evt)
  {
    tableContentProperty.addAll((MessageObject) evt.getNewValue());
  }


  public void sendMessage(MessageObject msg)
  {
    chatModelManager.processMessage(msg);
  }

  public ObservableList<MessageObject> getTableContentProperty()
  {
    return tableContentProperty;
  }
}
