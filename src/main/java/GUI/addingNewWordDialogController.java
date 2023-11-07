package GUI;

import static GUI.contributeWordController.isShowingDialog;
import static GUI.Utility.*;

import Application.Word;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class addingNewWordDialogController extends basicDialogController{

  public static Word addedWord;
  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowingDialog = false;
  }

  @FXML
  void addWordAction(MouseEvent event) {
    super.close(event);
//    databaseManagement.addWord(dictionary, addedWord);
    isShowingDialog = false;
  }
}
