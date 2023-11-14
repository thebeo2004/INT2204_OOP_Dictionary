package GUI;

import static GUI.Utility.databaseManagement;
import static GUI.Utility.dictionary;
import static GUI.addingNewWordDialogController.addedWord;

import Application.Word;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class contributeWordController extends showDialog implements Initializable{

  public static boolean isShowingDialog = false;
  @FXML
  private TextField targetWord;
  @FXML
  private TextField ipa;
  @FXML
  private TextArea explainWord;
  @FXML
  private TextField function;
  @FXML
  private TableColumn<String, String> tableColumn;
  @FXML
  private TableView<String> tableView;
  @FXML
  private TextField typing;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
  }

  @FXML
  void search(KeyEvent event) throws FileNotFoundException {

    if (typing.getText().isEmpty()) {
      return;
    }
    tableView.getItems().clear();
    for(String s : databaseManagement.search(dictionary, typing.getText())) {
      tableView.getItems().add(s);
    }
  }



  boolean checkExistence(String newWord) {

    tableView.getItems().clear();
    typing.setText(newWord);

    if (databaseManagement.lookUp(dictionary, newWord) == null) {
      return false;
    } else {
      tableView.getItems().add(newWord);
      return true;
    }

  }

  @FXML
  void updateButtonAction(MouseEvent event) throws IOException {

    if(targetWord.getText().equals("")) {
      return;
    }

    if (checkExistence(targetWord.getText())) {
      if (!isShowingDialog) {
        showAsDialog("invalidNewWordDialog.fxml");
        isShowingDialog = true;
      }
    } else {
      if (!isShowingDialog) {
        addedWord = new Word(targetWord.getText(),  ipa.getText(), function.getText(), explainWord.getText());
        showAsDialog("addingNewWordDialog.fxml");
        isShowingDialog = true;
      }
    }

  }
}
