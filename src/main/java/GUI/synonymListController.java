package GUI;

import static GUI.LookUpController.isShowSynonymDialog;
import static GUI.Utility.thesaurus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class synonymListController extends basicListController {

  public static String synonymTarget;
  private LookUpController controller;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);
    thesaurus.lookUp(synonymTarget);
    int cnt = 0;
    for(String s: thesaurus.getSynonyms()) {
      cnt++;
      tableView.getItems().add(s);

      if (cnt > 100) {
        break;
      }
    }
  }

  @FXML
  @Override
  protected void close(MouseEvent event) {
    super.close(event);
    isShowSynonymDialog = false;
  }

  @FXML
  @Override
  public void choosingFromList(MouseEvent event) {
    if (tableView.getSelectionModel().getSelectedItem() == null) {
      return;
    }
    String s = tableView.getSelectionModel().getSelectedItem();
    controller.show(s);
  }

  public void setController(LookUpController controller) {
    this.controller = controller;
  }
}
