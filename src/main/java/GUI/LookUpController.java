package GUI;

import static GUI.Utility.dictionary;
import static GUI.Utility.dictionaryManagement;
import static GUI.Utility.searchingHistory;

import Application.Word;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  @FXML
  private ImageView timeIcon;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
  }

  void loadTimeIcon(String path) throws FileNotFoundException {
    Image image = new Image(new FileInputStream(path));
    timeIcon.setImage(image);
  }

  @FXML
  void search(KeyEvent event) throws FileNotFoundException {

    tableView.getItems().clear();
    loadTimeIcon("src/main/resources/Icons/delivery_time.png");
    tableColumn.setText("Searching History");
    for(String s : dictionaryManagement.search(dictionary, typing.getText())) {
      tableView.getItems().add(s);
    }
  }

  void show(Word word) {
    searchingHistory.add(word.getTargetWord());

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

  @FXML
  void showSearchingHistory(MouseEvent event) throws FileNotFoundException {
    tableView.getItems().clear();
    tableColumn.setText("Searching History");
    loadTimeIcon("src/main/resources/Icons/delivery_time(1).png");
    for(String s : searchingHistory) {
      tableView.getItems().add(s);
    }
  }
}