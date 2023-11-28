package GUI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import static GUI.gameController.isShowHintDialog;

public class ShowHintDialogController extends basicDialogController {
  private gameController controller;
  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowHintDialog = false;
  }

  @FXML
  void showHint(MouseEvent event) {
    close(event);
    controller.showHint();
  }

  public void setController(gameController controller) {
    this.controller = controller;
  }
}
