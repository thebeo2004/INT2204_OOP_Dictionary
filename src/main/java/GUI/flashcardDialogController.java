package GUI;

import static GUI.LookUpController.isShowFlashcardDialog;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class flashcardDialogController extends basicDialogController {

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowFlashcardDialog = false;
  }
}
