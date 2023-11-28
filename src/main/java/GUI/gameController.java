package GUI;

import static GUI.Utility.crosswordGenerator;

import Application.Puzzle;
import Application.Word;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class gameController extends basicDialogController implements Initializable {

  private boolean isTransfer = false;
  private gameController controller;
  private static final int MAX_ROW = 11;
  private static final int MAX_COLUMN = 20;
  private int start_x;
  private int start_y;
  private int end_y;
  private puzzleCellController[][] cellController = new puzzleCellController[MAX_ROW][MAX_COLUMN];
  private List<puzzleCellController> controllerList = new ArrayList<>();
  private List<Puzzle> puzzleList = new ArrayList<>();
  private List<String> storage;
  @FXML
  private GridPane cellGrid;
  @FXML
  private AnchorPane pane = new AnchorPane();

  private void setStart_x(int width) {
    start_x = (int) (MAX_COLUMN - width) / 2;
  }

  private void setStart_y(int height) {
    start_y = (int) (MAX_ROW - height) / 2;
  }

  private void setPuzzleList() {

    for (int i = 0; i < puzzleList.size(); i++) {
      puzzleList.get(i).setY(start_y + i);
      puzzleList.get(i)
          .setX(start_x + storage.get(i).length() - puzzleList.get(i).getText().length());
    }
  }

  private String convert(String text) {
    String ans = "";
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != ' ') {
        ans += text.charAt(i);
      }
    }
    return ans;
  }

  private void getData() {
    int max_length = -1;

    for (String txt : storage) {
      max_length = Math.max(max_length, txt.length());
      Puzzle tmp = new Puzzle();
      tmp.setText(convert(txt));
      puzzleList.add(tmp);
    }

    setStart_x(max_length);
    setStart_y(crosswordGenerator.getSolution().length());
  }

  private void loadCell(int i, int j) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("puzzleCell.fxml"));
    try {
      Parent parent = loader.load();
      cellController[i][j] = loader.getController();
      controllerList.add(cellController[i][j]);
      cellController[i][j].setX(j);
      cellController[i][j].setY(i);
      cellGrid.add(parent, j, i);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void setActive() {

    for (int i = 0; i < puzzleList.size(); i++) {
      int x = puzzleList.get(i).getX();
      int y = puzzleList.get(i).getY();
      String s = puzzleList.get(i).getText();
      for (int j = x; j < x + puzzleList.get(i).getLength(); j++) {
        loadCell(y, j);
        cellController[y][j].setText("" + s.charAt(j - x));
      }
    }
  }

  void transferController() {
    if (isTransfer) {
      return;
    }

    for (puzzleCellController p : controllerList) {
      p.setController(controller);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    crosswordGenerator.buildCrossword();
    storage = crosswordGenerator.getCrossword();
    getData();
    setPuzzleList();
    setActive();
  }

  public void setController(gameController controller) {
    this.controller = controller;
  }

  public void changeStatus(int x, int y) {
    for (puzzleCellController p : controllerList) {
      p.turnOff();
    }

    for (int j = 0; j < MAX_COLUMN; j++) {
      if (cellController[y][j] != null) {
        cellController[y][j].turnOn();
      }
    }

    cellController[y][x].setChosen();
  }

  private puzzleCellController chosenCell() {
    for(puzzleCellController p : controllerList) {
      if (p.isChosen()) {
        return p;
      }
    }
    return null;
  }

  public void keyPressed(String s) {
    puzzleCellController OwO = chosenCell();

    if (OwO == null) {
      return;
    }

    OwO.setText(s);
    int x = OwO.getX();
    int y = OwO.getY();

    if (x < MAX_COLUMN - 1 && cellController[y][x + 1] != null) {
      OwO.turnOn();
      cellController[y][x + 1].setChosen();
    }
  }
}
