package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    public Button bouton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onButton1Click() {
        System.out.println("Button 1 was clicked");
    }

    @FXML
    protected void onButton2Click() {
        System.out.println("Button 2 was clicked");
    }

    @FXML
    protected void onButton3Click() {
        System.out.println("Button 3 was clicked");
    }
}