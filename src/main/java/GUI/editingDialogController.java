package GUI;


import static GUI.LookUpController.isShowEditingDialog;
import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;

import Application.Word;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class editingDialogController extends basicDialogController{

  public static Word editingWord;

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowEditingDialog = false;
  }

  @FXML
  void updateDictionary(MouseEvent event) {
//    databaseManagement.editWord(dictionary, editingWord);
    close(event);
  }
}
