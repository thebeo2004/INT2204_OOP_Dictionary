package GUI;

import static GUI.Utility.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.stage.StageStyle;

public class HelloApplication extends Application {

  public static Scene scene;
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
    scene = new Scene(fxmlLoader.load(), 960, 600);

    HelloController OwO = fxmlLoader.getController();
    OwO.setMainController(OwO);

    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  public static void main(String[] args) {
    GoogleController.loadList();
    databaseManagement.loadDictionary(dictionary);
    flashCardManagement.loadDictionary(flashCards);
    for(char c = 'A'; c <= 'Z'; c++) {
      String s = "";
      s += c;
      acceptedCharacter.add(s);
    }

    launch();
  }
}