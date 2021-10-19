package client.core;

import client.view.ViewController;
import client.view.chat.ChatViewController;
import client.view.friendlist.FriendListViewController;
import client.view.login.LoginViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shared.LoginObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;

  private Stage primaryStage;
  private Scene currentScene;

  private ViewController currController;

  public ViewHandler(ViewModelFactory vmf, Stage s){
    viewModelFactory = vmf;
    primaryStage = s;
//    openView("login");
    openLoginView();

    primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
  }


//  public void openView(String whichView)
//  {
//    switch (whichView){
//      case "login":
//        openLoginView();
//        break;
//      case "chat":
//        openChatView();
//        break;
//      case "friendList":
//        openFriendListView();
//      default:
//        break;
//    }
//  }

  private void openLoginView()
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/login/login.fxml"));
      Parent parent = fxmlLoader.load();
      currController = fxmlLoader.getController();
      currController.init(this, viewModelFactory.getLoginViewModel());

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

  public void openFriendListView(LoginObject loginObject)
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/friendlist/friendlist.fxml"));
      Parent parent = fxmlLoader.load();
      currController = fxmlLoader.getController();
      currController.init(this, viewModelFactory.getFriendListViewModel(), loginObject);

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


  public void openChatView(LoginObject loginObject)
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("../view/chat/chat.fxml"));
      Parent parent = fxmlLoader.load();

//      currController = fxmlLoader.getController();
//      currController.init(this, viewModelFactory.getChatViewModel(), loginObject);
//
//      currentScene = new Scene(parent);
//      primaryStage.setScene(currentScene);
//      primaryStage.setTitle("Chat");
//      primaryStage.show();

      ViewController chatController = fxmlLoader.getController();
      chatController.init(this, viewModelFactory.getChatViewModel(), loginObject);

      Scene chatScene = new Scene(parent);
      Stage chatStage = new Stage();
      chatStage.setScene(chatScene);
      chatStage.setTitle("Chat");
      chatStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("! ->wrong path");
    }
  }




  private void closeWindowEvent(WindowEvent event) {
    System.out.println("Window close request ...");

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.getButtonTypes().remove(ButtonType.OK);
    alert.getButtonTypes().add(ButtonType.CANCEL);
    alert.getButtonTypes().add(ButtonType.YES);
    alert.setTitle("Quit application");
    alert.setContentText(String.format("Close without saving?"));
    alert.initOwner(primaryStage.getOwner());
    Optional<ButtonType> res = alert.showAndWait();

    if(res.isPresent())
    {
      if (res.get().equals(ButtonType.CANCEL))
        event.consume();
      else
        currController.closeWindow();
    }
  }
}
