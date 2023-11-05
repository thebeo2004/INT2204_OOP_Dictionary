package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HelloController {

  @FXML
  private Button lookUp;
  private Button ggtranslate;

  @FXML
  private BorderPane borderPane;

  @FXML
  public void loadPage(String page) {
    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    borderPane.setCenter(root);
  }

  @FXML
  void loadLookUp(MouseEvent event) {
    loadPage("lookUp");
  }

  @FXML
  void loadGGTranslate(MouseEvent event) {
    loadPage("ggtranslate");
  }

}



