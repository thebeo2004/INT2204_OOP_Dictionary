package GUI;

import static GUI.flashCardController.storage;

import Application.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class basicListController extends basicDialogController implements Initializable {

  @FXML
  protected TableColumn<String, String> tableColumn;

  @FXML
  protected TableView<String> tableView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    tableView.getItems().clear();
  }

  @FXML
  public void choosingFromList(MouseEvent event) {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      return;
    }
  }
}
