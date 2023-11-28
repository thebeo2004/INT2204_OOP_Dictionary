package GUI;

import static GUI.gameController.isNewGameDialog;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class NewGameDialogController extends basicDialogController {
  private gameController controller;

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isNewGameDialog = false;
  }

  @FXML
  void startNewGame(MouseEvent event) {
    close(event);
    controller.startNewGame();
  }

  public void setController(gameController controller) {
    this.controller = controller;
  }
}
