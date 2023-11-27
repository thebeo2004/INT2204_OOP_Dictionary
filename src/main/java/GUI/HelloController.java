package GUI;

import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;

import Application.Word;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class HelloController extends basicDialogController implements Initializable {

  @FXML
  private BorderPane borderPane;

  @FXML
  private Button contribute;

  @FXML
  private Button download;

  @FXML
  private Button flashCard;

  @FXML
  private Button ggTranslate;

  @FXML
  private Button game;

  @FXML
  private Button lookUp;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    loadLookUp(null);
  }

  @FXML
  public FXMLLoader loadPage(String page) {
    Parent root = null;
    FXMLLoader loader = null;
    try {
      loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
      root = loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    borderPane.setCenter(root);
    return loader;
  }

  void turnOnHover(Button button) {
    button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #0c2f43;"));
    button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #035e8c;\n"
        + "    color: #cbe1ea;"));
  }

  void turnOff(Button button) {
    button.setStyle("-fx-background-color: #035e8c;\n"
        + "    color: #cbe1ea;");
    turnOnHover(button);
  }

  void turnOn(Button button) {
    button.setOnMouseEntered(e -> button.setStyle(""));
    button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #1d2a57;\n"
        + "  color: #cbe1ea;\n"
        + "  -fx-border-color: #ffff;\n"
        + "  -fx-border-width: 0px 0px 0px 3px;"));
    button.setStyle("-fx-background-color: #1d2a57;\n"
        + "  color: #cbe1ea;\n"
        + "  -fx-border-color: #ffff;\n"
        + "  -fx-border-width: 0px 0px 0px 3px;");
//    turnOnHover(button);
  }

  void turnOffAll() {
    turnOff(lookUp);
    turnOff(download);
    turnOff(game);
    turnOff(contribute);
    turnOff(flashCard);
    turnOff(ggTranslate);
  }

  @FXML
  void loadLookUp(MouseEvent event) {
    LookUpController OwO = loadPage("lookUp").getController();
    OwO.setController(OwO);
    turnOffAll();
    turnOn(lookUp);
  }

  @FXML
  void loadContribute(MouseEvent event) {
    loadPage("contributeWord");
    turnOffAll();
    turnOn(contribute);
  }

  private void saveFile(File file) throws IOException {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file, false));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (Word word : dictionary.getWordList()) {
      if (dictionary.getId(word.getTargetWord()) != -1) {
        writer.write(word.getInfo());
      }
    }
    try {
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @FXML
  void loadDownload(MouseEvent event) throws IOException {
    turnOffAll();
    turnOn(download);

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extensionFilter = new ExtensionFilter("TXT files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extensionFilter);

    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    File file = fileChooser.showSaveDialog(stage);

    if (file != null) {
      saveFile(file);
    }
  }

  @FXML
  void loadFlashCard(MouseEvent event) {
    flashCardController OwO = loadPage("flashCard").getController();
    OwO.setController(OwO);
    turnOffAll();
    turnOn(flashCard);
  }

  @FXML
  void loadGame(MouseEvent event) {
    turnOffAll();
    loadPage("crossword");
    turnOn(game);
  }

  @FXML
  void loadggTranslate(MouseEvent event) {
    loadPage("ggtranslate");
    turnOffAll();
    turnOn(ggTranslate);
  }

  @FXML
  void exitWindow(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.close();
  }

}



