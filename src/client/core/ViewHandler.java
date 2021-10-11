package client.core;

import client.view.chat.ChatViewController;
import client.view.friendlist.FriendListViewController;
import client.view.friendlist.FriendListViewModel;
import client.view.login.LoginViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.LoginObject;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;

  private Stage primaryStage;
  private Scene currentScene;

  public ViewHandler(ViewModelFactory vmf, Stage s){
    viewModelFactory = vmf;

    primaryStage = s;
    openView("login");
  }

  public void openView(String whichView)
  {
    switch (whichView){
      case "login":
        openLoginView();
        break;
      case "chat":
        openChatView();
        break;
      case "friendList":
        openFriendListView();
      default:
        break;
    }
  }

  private void openLoginView()
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/login/login.fxml"));
      Parent parent = fxmlLoader.load();
      LoginViewController loginViewController = fxmlLoader.getController();
      loginViewController.init(this, viewModelFactory.getLoginViewModel());

      currentScene = new Scene(parent);
      primaryStage.setScene(currentScene);
      primaryStage.setTitle("Log In");
      primaryStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("! ->wrong path");
    }
  }

  private void openFriendListView()
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/friendlist/friendlist.fxml"));
      Parent parent = fxmlLoader.load();
      FriendListViewController friendListViewController = fxmlLoader.getController();
      friendListViewController.init(this, viewModelFactory.getFriendListViewModel());

      currentScene = new Scene(parent);
      primaryStage.setScene(currentScene);
      primaryStage.setTitle("Friend list");
      primaryStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("! ->wrong path");
    }
  }

  private void openChatView()
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/chat/chat.fxml"));
      Parent parent = fxmlLoader.load();
      ChatViewController chatViewController = fxmlLoader.getController();
      chatViewController.init(this, viewModelFactory.getChatViewModel());

      currentScene = new Scene(parent);
      primaryStage.setScene(currentScene);
      primaryStage.setTitle("Chat");
      primaryStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("! ->wrong path");
    }
  }

  public void openChatView(LoginObject loginObject)
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/chat/chat.fxml"));
      Parent parent = fxmlLoader.load();
      ChatViewController chatViewController = fxmlLoader.getController();
      chatViewController.init(this, viewModelFactory.getChatViewModel(), loginObject);

      currentScene = new Scene(parent);
      primaryStage.setScene(currentScene);
      primaryStage.setTitle("Chat");
      primaryStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("! ->wrong path");
    }
  }
}
