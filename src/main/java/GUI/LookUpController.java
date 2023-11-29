package GUI;

import static GUI.Utility.*;
import static GUI.deleteWordDialogController.deletedWord;
import static GUI.editingDialogController.editingWord;
import static GUI.synonymListController.synonymTarget;

import Application.Word;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

public class LookUpController extends showDialog implements Initializable {

  private LookUpController controller;
  public static boolean isShowSynonymDialog;
  public static boolean isShowDeleteDialog;
  public static boolean isShowEditingDialog;
  public static boolean isShowFlashcardDialog;
  private boolean isEditing = false;
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

  @FXML
  private HBox editButton;

  @FXML
  private HBox updateButton;

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
    String text = typing.getText().toLowerCase();
    for(String s : databaseManagement.search(dictionary, text)) {
      tableView.getItems().add(s);
    }
  }

  void show(Word word) {
    searchingHistory.add(word.getTargetWord());

    clear();

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

  public void show(String text) {
    Word word = databaseManagement.lookUp(dictionary, text);
    if (word != null) {
      show(word);
    }
  }
  @FXML
  void choosingFromList(MouseEvent event) {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      return;
    }

    Word word = databaseManagement.lookUp(dictionary, tableView.getSelectionModel().getSelectedItem());
    if (word != null) {
      show(word);
    } else {
      targetWord.setText("This word doesn't exist");
      ipa.setText("");
      function.setText("");
      explainWord.setText("");
    }
  }

  public void clear() {
    targetWord.setText("");
    ipa.setText("");
    function.setText("");
    explainWord.setText("");
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

  @FXML
  void deleteWord(MouseEvent event) throws IOException {
    if (targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
      return;
    }
    if (!isShowDeleteDialog) {
      deletedWord = targetWord.getText();
      deleteWordDialogController OwO = showAsDialog("deleteWordDialog.fxml").getController();
      OwO.setController(controller);
      isShowDeleteDialog = true;
    }
  }

  void turnOnEdit() {
    ipa.setEditable(true);
    function.setEditable(true);
    explainWord.setEditable(true);
    updateButton.setVisible(true);

    ipa.setStyle("-fx-background-color: #a0d6ff;\n"
        + "  -fx-border-color: #a0d6ff;\n"
        + "  -fx-border-width: 1 1 1 1;");
    function.setStyle("-fx-background-color: #a0d6ff;\n"
        + "  -fx-border-color: #a0d6ff;\n"
        + "  -fx-border-width: 1 1 1 1;");
    explainWord.setStyle("-fx-background-color: #a0d6ff;\n"
        + "  -fx-border-color: #a0d6ff;\n"
        + "  -fx-border-width: 1 1 1 1;");
  }

  void turnOffEdit() {
    ipa.setEditable(false);
    function.setEditable(false);
    explainWord.setEditable(false);
    updateButton.setVisible(false);
    ipa.setStyle("-fx-background-color: #bfbfbf;\n"
        + "  -fx-border-color: #bfbfbf;\n"
        + "  -fx-border-width: 1 1 1 1;");
    function.setStyle("-fx-background-color: #bfbfbf;\n"
        + "  -fx-border-color: #bfbfbf;\n"
        + "  -fx-border-width: 1 1 1 1;");
    explainWord.setStyle("-fx-background-color: #bfbfbf;\n"
        + "  -fx-border-color: #bfbfbf;\n"
        + "  -fx-border-width: 1 1 1 1;");

    show(databaseManagement.lookUp(dictionary, targetWord.getText()));
  }
  @FXML
  void editWordAction(MouseEvent event) {

    if (!isEditing) {

      if(targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
        return;
      }
      editButton.setOnMouseEntered(e -> editButton.setStyle(""));
      editButton.setOnMouseExited(e -> editButton.setStyle("-fx-background-color: #003d5f;\n"
          + "    -fx-border-width: 3 0 0 0;\n"
          + "    -fx-border-color: #FFFF;"));
      editButton.setStyle("-fx-background-color: #003d5f;\n"
          + "    -fx-border-width: 3 0 0 0;\n"
          + "    -fx-border-color: #FFFF;");

      turnOnEdit();
      isEditing = true;
    } else {
      editButton.setStyle("-fx-background-color: #0078ba;\n"
          + "    -fx-border-width: 0 0 0 0;\n");

      editButton.setOnMouseEntered(e -> editButton.setStyle("-fx-background-color: #003d5f;\n"
          + "    -fx-border-width: 3 0 0 0;\n"
          + "    -fx-border-color: #FFFF;"));
      editButton.setOnMouseExited(e -> editButton.setStyle("-fx-background-color: #0078ba;\n"
          + "    -fx-border-width: 0 0 0 0;\n"));

      turnOffEdit();
      isEditing = false;
    }
  }

  @FXML
  void updateButtonAction(MouseEvent event) throws IOException {
    if(!updateButton.isVisible()) {
      return;
    }

    if (!isShowEditingDialog) {
      showAsDialog("editingDialog.fxml");
      editingWord = new Word(targetWord.getText(), ipa.getText(), function.getText(), explainWord.getText());
      isShowEditingDialog = true;
    }
  }

  @FXML
  void volumeAction(MouseEvent event) {
    if (targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
      return;
    }
    Thread thread = new Thread(() -> {
      textToSpeech.speakEn(targetWord.getText());
    });
    thread.start();

  }

  @FXML
  void addingFlashCardAction(MouseEvent event) throws IOException {
    if(targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
      return;
    }

    if (!isShowFlashcardDialog) {
      showAsDialog("flashcardDialog.fxml");
      isShowFlashcardDialog = true;
      Word word = new Word(targetWord.getText(), ipa.getText(), function.getText(), explainWord.getText());
      flashCardManagement.addWord(flashCards, word);
      //System.out.println(databaseManagement.lookUp(dictionary, targetWord.getText()));
    }
  }

  @FXML
  void showSynonymAction(MouseEvent event) throws IOException {
    if(targetWord.getText().equals("") || targetWord.getText().equals("This word doesn't exist")) {
      return;
    }
    if (!isShowSynonymDialog) {
      synonymTarget = targetWord.getText();
      synonymListController OwO = showAsDialog("synonymList.fxml").getController();
      OwO.setController(controller);
      isShowSynonymDialog = true;
    }
  }

  public void setController(LookUpController controller) {
    this.controller = controller;
  }
}