package GUI;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class basicDialogController {

  private double x;
  private double y;

  @FXML
  protected void close(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  protected void dragged(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.setX(event.getScreenX() - x);
    stage.setY(event.getScreenY() - y);
  }

  @FXML
  protected void pressed(MouseEvent event) {
    x = event.getSceneX();
    y = event.getSceneY();
  }
}
