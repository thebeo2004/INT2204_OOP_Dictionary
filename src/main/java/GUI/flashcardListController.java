package GUI;

import Application.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;
import static GUI.flashCardController.storage;
import static GUI.flashCardController.isShowAllList;

public class flashcardListController extends basicDialogController implements Initializable {

  private flashCardController controller;
  @FXML
  private TableColumn<String, String> tableColumn;

  @FXML
  private TableView<String> tableView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    tableView.getItems().clear();
    for(Word w : storage) {
      tableView.getItems().add(w.getTargetWord());
    }
  }

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowAllList = false;
  }

  @FXML
  @Override
  protected void dragged(MouseEvent event) {
    super.dragged(event);
  }

  @FXML
  @Override
  protected void pressed(MouseEvent event) {
    super.pressed(event);
  }

  @FXML
  void choosingFromList(MouseEvent event) {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      return;
    }

    String s = tableView.getSelectionModel().getSelectedItem();

    for(int i = 0; i < storage.size(); i++) {
      if (storage.get(i).getTargetWord().equals(s)) {
        controller.choosingFromList(i);
        return;
      }
    }
  }

  public void setController(flashCardController controller) {
    this.controller = controller;
  }
}
