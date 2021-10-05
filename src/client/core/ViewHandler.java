package client.core;

import client.view.login.LoginViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;

  private Stage primaryStage;
  private Scene currentScene;

  private FXMLLoader fxmlLoader;

  public ViewHandler(ViewModelFactory vmf, Stage s){
    viewModelFactory = vmf;

    fxmlLoader = new FXMLLoader();
    primaryStage = s;
    openView("login");
  }

  private void openView(String whichView)
  {
    switch (whichView){
      case "login":
        openLoginView();
        break;
      case "chat":
        openChatView();
        break;
      default:
        break;
    }
  }

  private void openLoginView()
  {
    try
    {
      fxmlLoader.setLocation(getClass().getResource("../view/login/login.fxml"));
      Parent parent = fxmlLoader.load();
      LoginViewController loginViewController = fxmlLoader.getController();
      loginViewController.init(this, viewModelFactory.getLoginViewModel());

      currentScene = new Scene(parent);
      primaryStage.setScene(currentScene);
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
  }
}
