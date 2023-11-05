package GUI;

import static GUI.HelloApplication.dictionary;
import static GUI.HelloApplication.dictionaryManagement;

import Application.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;
import javafx.scene.text.Text;

public class LookUpController implements Initializable {

  @FXML
  private TextArea explainWord;

  @FXML
  private Label ipa;

  @FXML
  private Label function;

  @FXML
  private TableColumn<String, String> tableColumn;

  @FXML
  private TableView<String> tableView;

  @FXML
  private Label targetWord;

  @FXML
  private TextField typing;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
  }

  @FXML
  void search(KeyEvent event) {

    tableView.getItems().clear();

    for(String s : dictionaryManagement.search(dictionary, typing.getText())) {
      tableView.getItems().add(s);
    }
  }

  void show(Word word) {
    targetWord.setText("");
    ipa.setText("");
    function.setText("");
    explainWord.setText("");

    targetWord.setText(word.getTargetWord());
    ipa.setText(word.getIpa());
    function.setText(word.getFunction());
    explainWord.setText(word.getExplainWord());
  }

  @FXML
  void show(ActionEvent event) {

    Word word = dictionaryManagement.lookUp(dictionary, typing.getText());

    if (word != null) {
      show(word);
    } else {
      targetWord.setText("This word doesn't exist");
    }

//    tableView.getItems().clear();
//    typing.clear();
  }

  @FXML
  void choosingFromList(MouseEvent event) {
    Word word = dictionaryManagement.lookUp(dictionary, tableView.getSelectionModel().getSelectedItem());
    if (word != null) {
      show(word);
    }

//    tableView.getItems().clear();
//    typing.clear();
  }

}