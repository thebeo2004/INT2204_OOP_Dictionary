package GUI;


import static GUI.contributeWordController.isShowingDialog;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class invalidNewWordDialogController extends basicDialogController {
  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowingDialog = false;
  }
}
