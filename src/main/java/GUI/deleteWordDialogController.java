package GUI;

import static GUI.LookUpController.isShowDeleteDialog;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class deleteWordDialogController extends basicDialogController {

  private LookUpController controller;
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
    controller.clear();
    close(event);
  }

  public void setController(LookUpController controller) {
    this.controller = controller;
  }
}
