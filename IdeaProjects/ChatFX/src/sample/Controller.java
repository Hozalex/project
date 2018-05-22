package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;


    public void sendMessage(ActionEvent actionEvent) {
        if (!textField.getText().isEmpty()) {
            textArea.appendText("nick1: " + textField.getText() + "\n");
            textField.clear();
            textField.requestFocus();
        }

    }


}
