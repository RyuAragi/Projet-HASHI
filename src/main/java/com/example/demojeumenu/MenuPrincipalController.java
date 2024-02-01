package com.example.demojeumenu;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class MenuPrincipalController {

    private static final int MAX_CHARS = 20;
    @FXML
    private Button bouton;

    @FXML
    public TextField zoneTexte;

    private Scene scene;
    private String userInput; // Variable pour stocker la saisie de l'utilisateur

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void afficherAutreMenu() {
        userInput = zoneTexte.getText();
        try {
            FXMLLoader autreMenuLoader = new FXMLLoader(getClass().getResource("AutreMenu.fxml"));
            Parent autreMenuRoot = autreMenuLoader.load();
            AutreMenuController autreMenuController = autreMenuLoader.getController();
            autreMenuController.setScene(scene);
            scene.setRoot(autreMenuRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitter() {
        System.out.println("Bouton QUITTER a été cliqué");
        System.exit(0);
    }
    @FXML
    protected void initialize() {
        Platform.runLater(() -> {
            bouton.requestFocus();
        });

        zoneTexte.setText("Entrez votre nom");
        zoneTexte.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ("Entrez votre nom".equals(zoneTexte.getText())) {
                    zoneTexte.setText("");
                }
                int middle = zoneTexte.getText().length() / 2;
                zoneTexte.positionCaret(middle);
            }
        });

        zoneTexte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && zoneTexte.getText().isEmpty()) {
                zoneTexte.setText("Entrez votre nom");
            }
        });

        zoneTexte.setTextFormatter(
                new TextFormatter<String>(change -> change.getControlNewText().length() > MAX_CHARS ? null : change));

    }
}