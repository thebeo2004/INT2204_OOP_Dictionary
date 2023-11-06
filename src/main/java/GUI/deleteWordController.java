package GUI;

import static GUI.LookUpController.isShowDeleteDialog;
import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class deleteWordController {

  @FXML
  public static String deletedWord;
  private double x;
  private double y;

  @FXML
  void dragged(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.setX(event.getScreenX() - x);
    stage.setY(event.getScreenY() - y);
  }

  @FXML
  void pressed(MouseEvent event) {
    x = event.getSceneX();
    y = event.getSceneY();
  }

  @FXML
  void close(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.close();
    isShowDeleteDialog = false;
  }

  @FXML
  void dialogDeleteWord(MouseEvent event) {
//    databaseManagement.deleteWord(dictionary, deletedWord);
    close(event);
  }

  @FXML
  void dialogReturnHome(MouseEvent event) {
    close(event);
  }
}
