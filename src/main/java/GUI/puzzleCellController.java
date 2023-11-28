package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class puzzleCellController {
  private int x;
  private int y;
  private gameController controller;
  private boolean off = true;
  private boolean chosen = false;
  private boolean close = false;
  @FXML
  private Label label;

  public void setController(gameController controller) {
    this.controller = controller;
  }

  public void setText(String s) {
    label.setText(s.toUpperCase());
  }

  public void setClose() {
    close = true;
    chosen = false;
    label.setStyle("-fx-background-color: #f4e7a3");
  }

  public void turnOff() {
    if (close) {
      return;
    }
    chosen = false;
    off = true;
    label.setStyle("-fx-background-color: #cdcdcd");
  }

  public void turnOn() {
    if (close) {
      return;
    }
    chosen = false;
    off = false;
    label.setStyle("-fx-background-color: #c6ffc6");
  }

  public void setChosen() {
    if (close) {
      return;
    }
    chosen = true;
    off = false;
    label.setStyle("-fx-background-color: #88fea6");
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @FXML
  void pressed(MouseEvent event) {
    controller.changeStatus(x, y);
  }

  public boolean isOff() {
    return off;
  }

  public boolean isClose() {
    return close;
  }

  public boolean isChosen() {
    return chosen;
  }
}
