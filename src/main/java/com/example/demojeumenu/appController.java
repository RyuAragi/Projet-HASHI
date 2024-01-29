package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class appController {

    private static final int MAX_CHARS = 20;
    @FXML
    public Button backButton;
    @FXML
    public Button bouton;
    public Label color;
    @FXML
    public TextField zoneTexte;
    public VBox myVBox;

    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    @FXML
    protected void onButton2Click() throws IOException {
        try {
            loadGameView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onBackButtonClick() ;
        System.out.println("Bouton 2 a été cliqué");
    }

    @FXML
    protected void onButton3Click() {
        System.out.println("Bouton 3 a été cliqué");
        System.exit(0);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(scene);
    }

    private void loadGameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) bouton.getScene().getWindow();
        stage.setTitle("Jacobhashi!");
        stage.setFullScreen(true);
        stage.setScene(scene);
        WindowManager windowManager = new WindowManager(stage);
        windowManager.configureWindow();
        stage.show();
    }

    @FXML
    protected void initialize() {

        zoneTexte.setPromptText("Entrez votre nom");
        // limiter le nombre de caractères dans la zone de texte
        zoneTexte.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() > MAX_CHARS) {
                return null;
            } else {
                return change;
            }
        }));

    }

}