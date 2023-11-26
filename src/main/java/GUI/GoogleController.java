package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static GUI.Utility.textToSpeech;
import static GUI.Utility.translator;

public class GoogleController extends basicDialogController implements Initializable {

    @FXML
    private ChoiceBox<String> definitionBox;

    @FXML
    private TextArea definitionTextArea;

    @FXML
    private ChoiceBox<String> targetBox;

    @FXML
    private TextArea targetTextArea;

    private String input;
    private String output;
    private Map<String, String> languages = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languages.put("English", "en");
        languages.put("Vietnamese", "vi");
        languages.put("Japanese", "ja");
        languages.put("Chinese", "zh");
        targetBox.getItems().addAll(languages.keySet());
        definitionBox.getItems().addAll(languages.keySet());
        targetBox.setValue("English");
        definitionBox.setValue("Vietnamese");
    }

    @FXML
    void type(KeyEvent event) {
        input = targetTextArea.getText();
    }

    @FXML
    void choose(MouseEvent event) {

    }

    @FXML
    void translate(MouseEvent event) {
        output = translator.translate(input, languages.get(targetBox.getValue()), languages.get(definitionBox.getValue()));
        if (output == null) {
            return;
        }
        definitionTextArea.setText(output);
    }

    @FXML
    void targetSound(MouseEvent event) {
        if (input == null) {
            return;
        }
        textToSpeech.speak(input, languages.get(targetBox.getValue()));
    }

    @FXML
    void definitionSound(MouseEvent event) {
        if (input == null) {
            return;
        }
        textToSpeech.speak(input, languages.get(definitionBox.getValue()));
    }
}
