package GUI;

import static GUI.LookUpController.isShowDeleteDialog;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class deleteWordDialogController extends basicDialogController {

  public static String deletedWord;

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowDeleteDialog = false;
  }

  @FXML
  void dialogDeleteWord(MouseEvent event) {
//    databaseManagement.deleteWord(dictionary, deletedWord);
    close(event);
  }

}
