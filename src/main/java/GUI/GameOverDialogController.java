package GUI;

import Application.Puzzle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameOverDialogController extends basicDialogController implements Initializable {

  @FXML
  private Label crossWord = new Label();
  @FXML
  private Label crossExplain = new Label();

  private gameController controller;
  public static Puzzle crossPuzzle = new Puzzle();
  @FXML
  void newGame(MouseEvent event) {
    controller.startNewGame();
    super.close(event);
  }

  public void setController(gameController controller) {
    this.controller = controller;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    crossWord.setText("Cross Word: " + crossPuzzle.getText());
    crossExplain.setText(crossPuzzle.getExplain());
  }
}
