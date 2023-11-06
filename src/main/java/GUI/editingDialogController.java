package GUI;


import static GUI.LookUpController.isShowEditingDialog;
import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;

import Application.Word;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class editingDialogController {

  public static Word editingWord;
  private double x;
  private double y;

  void close(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.close();
    isShowEditingDialog = false;
  }

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
  void updateDictionary(MouseEvent event) {
//    databaseManagement.editWord(dictionary, editingWord);
    close(event);
  }

  @FXML
  void exitAction(MouseEvent event) {
    close(event);
  }
}
