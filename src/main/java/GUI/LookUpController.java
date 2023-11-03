package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class LookUpController {

    @FXML
    private TextField textField;
    private ListView<String> listView;


    @FXML
    void prefixSearch(MouseEvent event) {
        //List<String> list = HelloApplication.databaseManagement.prefixList(HelloApplication.dictionary, textField.getText());
        //listView.getItems().clear();
        //listView = new ListView<String>();
        String[] list = {"a", "b", "c"};
        listView.getItems().addAll(list);
        System.out.println(listView.getItems());
        //System.out.println(list.get(0));
    }

    public void initialize() {
        // Initialize the ListView field.
        this.listView = new ListView<>();

        // Add items to the ListView.
        this.listView.getItems().addAll("Item 1", "Item 2", "Item 3");
    }


}