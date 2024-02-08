package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.logging.Logger;

public class Parametres extends BaseController {
    @FXML
    public TextField usernameZone;

    @FXML
    private Button bouton;

    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    @FXML
    private void back_button() {
        FXMLUtils.goBack(scene);
    }
    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
    }

}
