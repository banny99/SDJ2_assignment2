package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shared.LoginObject;
import java.io.IOException;
import java.util.Optional;

public class ViewHandler
{
  private final ViewModelFactory viewModelFactory;

  private final Stage primaryStage;
  private Stage chatStage;
  private Scene currentScene;
  private ViewController currController;


  public ViewHandler(ViewModelFactory vmf, Stage s){
    viewModelFactory = vmf;
    primaryStage = s;
  }

  public void start()
  {
    openLoginView();
  }

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

    primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
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

      ViewController chatController = fxmlLoader.getController();
      chatController.init(this, viewModelFactory.getChatViewModel(), loginObject);

      Scene chatScene = new Scene(parent);
      chatStage = new Stage();
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

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Quit application");
    alert.setContentText("Close without saving?");
    alert.initOwner(primaryStage.getOwner());
    Optional<ButtonType> res = alert.showAndWait();

    if(res.isPresent())
    {
      if (res.get().equals(ButtonType.OK))
      {
        if (chatStage != null)
          chatStage.close();
        currController.closeWindow();
      }
      else
      {
        event.consume();
      }
    }
  }
}
