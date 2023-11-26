package GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class showDialog extends basicDialogController {
  protected FXMLLoader showAsDialog(String path) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    Parent parent = loader.load();
    Scene dialogScene = new Scene(parent);
    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.setResizable(false);
    dialogStage.setAlwaysOnTop(true);
    dialogStage.initStyle(StageStyle.UNDECORATED);
    dialogStage.show();
    return loader;
  }
}
