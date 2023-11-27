package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class puzzleCellController {
  private int x;
  private int y;
  private gameController controller;
  private boolean isOff = true;
  private boolean isChosen = false;
  private boolean isClose = false;
  @FXML
  private Label label;

  public void setController(gameController controller) {
    this.controller = controller;
  }

  public void setText(char c) {
    String s = "";
    s += c;
    label.setText(s.toUpperCase());
  }

  public void setClose() {
    isClose = true;
    isChosen = false;
    label.setStyle("-fx-background-color: #f4e7a3");
  }

  public void turnOff() {
    if (isClose) {
      return;
    }
    isChosen = false;
    isOff = true;
    label.setStyle("-fx-background-color: #cdcdcd");
  }

  public void turnOn() {
    if (isClose) {
      return;
    }
    isChosen = false;
    isOff = false;
    label.setStyle("-fx-background-color: #c6ffc6");
  }

  public void setChosen() {
    if (isClose) {
      return;
    }
    isChosen = true;
    isOff = false;
    label.setStyle("-fx-background-color: #88fea6");
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @FXML
  void pressed(MouseEvent event) {
    controller.changeStatus(x, y);
  }
}
