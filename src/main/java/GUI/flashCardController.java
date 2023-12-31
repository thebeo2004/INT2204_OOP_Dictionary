package GUI;

import Application.Word;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import static GUI.Utility.*;
import static GUI.deleteFlashcardDialogController.deletedFlashcard;

public class flashCardController extends showDialog implements Initializable {

  public static List<Word> storage;
  private boolean isHead = true;
  private int curIndex = 0;
  private flashCardController controller;
  public static boolean isShowDeleteDialog = false;
  public static boolean isShowAllList = false;
  @FXML
  private HBox flipAction;

  @FXML
  private HBox nextAction;

  @FXML
  private Label position;

  @FXML
  private Label status;

  @FXML
  private TextArea textArea;

  @FXML
  private TextField typingIndex;

  public void setController(flashCardController controller) {
    this.controller = controller;
  }

  private void setEmpty() {
    position.setText("0/0");
    textArea.setText("Your storage is empty now\n"
        + "Let's add more new words to storage");
  }

  private void setHeadShow(String target, String ipa) {
    textArea.setText("\n\n\n" + target
        + "\n\n\n" + ipa);
  }

  private void setTailShow(String function, String explain) {
    textArea.setText("\n" + function
        + "\n\n" + explain);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    storage = flashCardManagement.getFlashCards(flashCards);

    if (storage.isEmpty()) {
      setEmpty();
    } else {
      position.setText("1/" + storage.size());
      curIndex = 0;
      setHeadShow(storage.get(0).getTargetWord(), storage.get(0).getIpa());
    }
  }

  @FXML
  void flipAction(MouseEvent event) {
    if (storage.isEmpty()) {
      return;
    }

    if (isHead) {
      isHead = false;
      status.setText("Explain");
      setTailShow(storage.get(curIndex).getFunction(), storage.get(curIndex).getExplainWord());
    } else {
      isHead = true;
      status.setText("Head");
      setHeadShow(storage.get(curIndex).getTargetWord(), storage.get(curIndex).getIpa());
    }
  }

  private void switchIndex() {
    position.setText((curIndex + 1) + "/" + storage.size());
    isHead = true;
    status.setText("Word");
    setHeadShow(storage.get(curIndex).getTargetWord(), storage.get(curIndex).getIpa());
  }

  @FXML
  void nextAction(MouseEvent event) {
    if (storage.isEmpty()) {
      return;
    }

    curIndex = (curIndex + 1) % storage.size();
    switchIndex();
  }

  @FXML
  void backAction(MouseEvent event) {
    if (storage.isEmpty()) {
      return;
    }

    curIndex = (curIndex - 1 + storage.size()) % storage.size();
    switchIndex();
  }

  private int convert(String text) {
    int ans = 0;

    for(int i = 0; i < text.length(); i++) {
      int digit = (int)text.charAt(i);

      if (digit >= 48 && (digit - 48) <= 9) {
        ans = ans * 10 + (digit - 48);
      } else {
        return -1;
      }
    }

    return ans;
  }

  @FXML
  void findIndex(ActionEvent event) {
    if (typingIndex.getText().equals("") || storage.isEmpty()) {
      return;
    }

    int id = convert(typingIndex.getText());
    System.out.println(id);
    if (id <= 0) {
      return;
    }

    id = Math.min(id, storage.size());
    curIndex = id - 1;
    switchIndex();
  }

  @FXML
  void deleteAction(MouseEvent event) throws IOException {
    if (storage.isEmpty() || isShowDeleteDialog) {
      return;
    }

    isShowDeleteDialog = true;
    deleteFlashcardDialogController OwO = showAsDialog("deleteFlashcardDialog.fxml").getController();
    OwO.setController(controller);
    deletedFlashcard = storage.get(curIndex).getTargetWord();

//    deleteFlashcardDialogController owo = loader
  }

  public void delete() {
    if (storage.isEmpty()) {
      setEmpty();
    } else {
      curIndex = curIndex % storage.size();
      switchIndex();
    }
  }

  @FXML
  void showAction(MouseEvent event) throws IOException {

    if (isShowAllList) {
      return;
    }

    isShowAllList = true;
    flashcardListController OwO = showAsDialog("flashcardList.fxml").getController();
    OwO.setController(controller);
  }

  public void choosingFromList(int id) {
    curIndex = id;
    switchIndex();
  }

}
