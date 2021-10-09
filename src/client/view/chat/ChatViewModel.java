package client.view.chat;

import client.model.Model;

public class ChatViewModel
{
  private Model chatModelManager;

  public ChatViewModel(Model chatModelManager)
  {
    this.chatModelManager = chatModelManager;
  }

  public void sendMessage(String msg)
  {
    chatModelManager.processMessage(msg);
  }
}
