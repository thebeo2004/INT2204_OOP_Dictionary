package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import Application.*;
public class HelloApplication extends Application {

  public static Dictionary dictionary = new Dictionary();
  public static DatabaseManagement databaseManagement = new DatabaseManagement();



  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();

    databaseManagement.loadDictionary(dictionary);
  }

  public static void main(String[] args) {
    launch();
  }
}