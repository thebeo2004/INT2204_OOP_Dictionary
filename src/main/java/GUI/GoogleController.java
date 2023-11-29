package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

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
    private static final TreeMap<String, String> languages = new TreeMap<>();

    public static void loadList() {
        try {
            languages.put("Auto-detect", "");
            Scanner scanner = new Scanner(new File("src/main/resources/Data/languages.txt"));
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                String lang = temp.substring(0, temp.indexOf('|'));
                String key = temp.substring(temp.indexOf('|') + 1);
                languages.put(lang, key);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        targetBox.getItems().addAll(languages.keySet());
        definitionBox.getItems().addAll(languages.keySet());
        definitionBox.getItems().remove("Auto-detect");

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
        String target = languages.get(targetBox.getValue());
        String definition = languages.get(definitionBox.getValue());

        Thread thread = new Thread(() -> {
            output = translator.translate(input, target, definition);
            if (output == null) {
                return;
            }
        });

        Thread wait = new Thread(() -> {
            try {
                thread.join();
                definitionTextArea.setText(output);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        if (target.equals(definition)) {
            definitionTextArea.setText(input);
        } else {
            thread.start();
            wait.start();
        }
    }

    @FXML
    void targetSound(MouseEvent event) {
        if (input == null) {
            return;
        }
        Thread thread = new Thread(() -> {
            textToSpeech.speak(input, languages.get(targetBox.getValue()));
        });
        thread.start();
    }

    @FXML
    void definitionSound(MouseEvent event) {
        if (input == null) {
            return;
        }
        Thread thread = new Thread(() -> {
            textToSpeech.speak(output, languages.get(definitionBox.getValue()));
        });
        thread.start();
    }
}
