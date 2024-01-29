package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;

public class appController {

    private static final int MAX_CHARS = 20;
    @FXML
    public Button backButton, bouton;
    @FXML
    public TextField zoneTexte;

    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    @FXML
    protected void onButton2Click() throws IOException {
        loadGameView();
        onBackButtonClick();
        System.out.println("Bouton 2 a été cliqué");
    }

    @FXML
    protected void onButton3Click() {
        System.out.println("Bouton 3 a été cliqué");
        System.exit(0);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        loadScene("hello-view.fxml");
    }

    private void loadGameView() throws IOException {
        loadScene("game-view.fxml");
    }

    private void loadScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) bouton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void initialize() {
        zoneTexte.setPromptText("Entrez votre nom");
        zoneTexte.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() > MAX_CHARS ? null : change));
    }
}