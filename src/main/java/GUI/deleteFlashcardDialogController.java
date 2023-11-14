package GUI;

import static GUI.flashCardController.isShowDeleteDialog;
import static GUI.Utility.*;
import static GUI.flashCardController.storage;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class deleteFlashcardDialogController extends basicDialogController{

  private flashCardController controller;
  public static String deletedFlashcard;
  boolean is_delete = false;

  public void setController(flashCardController controller) {
    this.controller = controller;
  }

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowDeleteDialog = false;

  }

  @FXML
  private void dialogDeleteFlashcard(MouseEvent event) {
    flashCardManagement.deleteWord(flashCards, deletedFlashcard);
    storage = flashCardManagement.getFlashCards(flashCards);
    close(event);
    is_delete = true;
    controller.delete();
  }
}
