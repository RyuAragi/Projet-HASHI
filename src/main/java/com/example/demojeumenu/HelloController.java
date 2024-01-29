package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;

public class HelloController {
    public Button bouton;

    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    @FXML
    protected void onButton2Click() {
        System.out.println("Bouton 2 a été cliqué");
    }

    @FXML
    protected void onButton3Click() {
        System.out.println("Bouton 3 a été cliqué");
        System.exit(0);
    }

    @FXML
    private AmbientLight ambientLight;

    @FXML
    protected void initialize() {
    }
}