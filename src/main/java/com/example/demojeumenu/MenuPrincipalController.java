package com.example.demojeumenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class MenuPrincipalController {

    private static final int MAX_CHARS = 20;
    @FXML
    private Button bouton;

    @FXML
    public TextField zoneTexte;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void afficherAutreMenu() {
        // Variable pour stocker la saisie de l'utilisateur
        zoneTexte.getText();
        try {
            FXMLLoader autreMenuLoader = new FXMLLoader(getClass().getResource("AutreMenu.fxml"));
            Parent autreMenuRoot = autreMenuLoader.load();
            AutreMenuController autreMenuController = autreMenuLoader.getController();
            autreMenuController.setScene(scene);
            scene.setRoot(autreMenuRoot);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.error("Une erreur est survenue lors du chargement du fichier FXML", e);
        }
    }

    @FXML
    private void quitter() {
        System.out.println("Bouton QUITTER a été cliqué");
        System.exit(0);
    }
    @FXML
    protected void initialize() {
        Platform.runLater(() -> bouton.requestFocus());

        if (GlobalVariables.userInput != null && !GlobalVariables.userInput.isEmpty()) {
            zoneTexte.setText(GlobalVariables.userInput);
            zoneTexte.end(); // Place le curseur à la fin du texte
        } else {
            zoneTexte.setText("Entrez votre nom");
        }

        zoneTexte.setOnMouseClicked(event -> {
            if ("Entrez votre nom".equals(zoneTexte.getText())) {
                zoneTexte.setText("");
            }
        });

        zoneTexte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Si la zone de texte reçoit le focus
                if (zoneTexte.getText().equals("Entrez votre nom")) {
                    zoneTexte.end(); // Place le curseur à la fin du texte
                }
                //System.out.println("Zone de texte a le focus");
            } else if (zoneTexte.getText().isEmpty()) {
                zoneTexte.setText("Entrez votre nom");
            }
        });

        zoneTexte.setTextFormatter(
                new TextFormatter<>(change -> change.getControlNewText().length() > MAX_CHARS ? null : change));

        zoneTexte.textProperty().addListener((observable, oldValue, newValue) -> GlobalVariables.userInput = newValue);
    }

}