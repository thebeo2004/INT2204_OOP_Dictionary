package GUI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import static GUI.gameController.isShowQuitDialog;

public class QuitGameDialogController extends basicDialogController {

  private gameController controller;

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowQuitDialog = false;
  }

  @FXML
  void quitGame(MouseEvent event) {
    close(event);
    controller.quitGame();
  }

  public void setController(gameController controller) {
    this.controller = controller;
  }
}
