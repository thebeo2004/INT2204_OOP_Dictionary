package GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class showDialog {
  protected void showAsDialog(String path) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource(path));
    Scene dialogScene = new Scene(parent);
    Stage dialogStage = new Stage();
    dialogStage.setScene(dialogScene);
    dialogStage.setResizable(false);
    dialogStage.setAlwaysOnTop(true);
    dialogStage.initStyle(StageStyle.UNDECORATED);
    dialogStage.show();
  }
}
