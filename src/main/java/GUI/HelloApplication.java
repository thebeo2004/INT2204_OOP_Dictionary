package GUI;

import static GUI.Utility.*;
import static GUI.Utility.dictionaryManagement;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

import Application.*;
import javafx.stage.StageStyle;

public class HelloApplication extends Application {

  public static Scene scene;
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
    scene = new Scene(fxmlLoader.load(), 960, 600);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  public static void main(String[] args) {
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