package GUI;

import static GUI.Utility.dictionary;
import static GUI.Utility.databaseManagement;
import static GUI.Utility.searchingHistory;
import static GUI.deleteWordController.deletedWord;

import Application.Word;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LookUpController implements Initializable {

  public static boolean isShowDeleteDialog;
  @FXML
  private TextArea explainWord;

  @FXML
  private TextField ipa;

  @FXML
  private TextField function;

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
  private deleteWordController deleteController;

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

    if (typing.getText().isEmpty()) {
      return;
    }
    tableView.getItems().clear();
    loadTimeIcon("src/main/resources/Icons/delivery_time.png");
    tableColumn.setText("Searching Result");
    for(String s : databaseManagement.search(dictionary, typing.getText())) {
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

    Word word = databaseManagement.lookUp(dictionary, typing.getText());

    if (word != null) {
      show(word);
    } else {
      targetWord.setText("This word doesn't exist");
      ipa.setText("");
      function.setText("");
      explainWord.setText("");
    }

//    tableView.getItems().clear();
//    typing.clear();
  }

  @FXML
  void choosingFromList(MouseEvent event) {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      return;
    }

    Word word = databaseManagement.lookUp(dictionary, tableView.getSelectionModel().getSelectedItem());
    if (word != null) {
      show(word);
    }

//    tableView.getItems().clear();
//    typing.clear();
  }

  @FXML
  void showSearchingHistory(MouseEvent event) throws FileNotFoundException {

    if (searchingHistory.isEmpty()) {
      return;
    }
    tableView.getItems().clear();
    tableColumn.setText("Searching History");
    loadTimeIcon("src/main/resources/Icons/delivery_time(1).png");

    for(int i = searchingHistory.size() - 1; i >= 0; i--) {
      boolean flag = true;

      for(int j = i + 1; j < searchingHistory.size(); j++) {
        if (searchingHistory.get(j).equals(searchingHistory.get(i))) {
          flag = false;
        }
      }
      if (flag) {
        tableView.getItems().add(searchingHistory.get(i));
      }
    }

  }

  private void showAsDialog(String path) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource(path));
    Scene dialogScene = new Scene(parent);
    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.setResizable(false);
    dialogStage.setAlwaysOnTop(true);
    dialogStage.initStyle(StageStyle.UNDECORATED);
    dialogStage.show();
  }

  @FXML
  void deleteWord(MouseEvent event) throws IOException {
    if (targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
      return;
    }
    if (!isShowDeleteDialog) {
      deletedWord = targetWord.getText();
      showAsDialog("deleteWord.fxml");
      isShowDeleteDialog = true;
    }
  }


}