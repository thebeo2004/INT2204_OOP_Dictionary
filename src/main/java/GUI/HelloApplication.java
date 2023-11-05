package GUI;

import GoogleBasedFeatures.TextToSpeech;
import GoogleBasedFeatures.Translator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import Application.*;
import GoogleBasedFeatures.*;

public class HelloApplication extends Application {

  public static Dictionary dictionary = new Dictionary();
  public static DatabaseManagement databaseManagement = new DatabaseManagement();
  public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
  public static Translator translator = new Translator();
  public static TextToSpeech textToSpeech = new TextToSpeech();


  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();

//    databaseManagement.loadDictionary(dictionary);
//    //databaseManagement.print(dictionary);

  }

  public static void main(String[] args) {
    databaseManagement.loadDictionary(dictionary);
    launch();
  }
}