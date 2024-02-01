package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;
//import javafx.scene.control.Button;

/**
 * Contrôleur de la vue principale.
 */

public class MenuPrincipalController {
    /**
     * Le nombre maximum de caractères dans la zone de texte.
     */
    private static final int MAX_CHARS = 20;

    /**
     * Le bouton pour quitter l'application.
     * Il est lié à la méthode {@link #onButton3Click()}.
     */


    /**
     * La zone de texte pour le nom du joueur.
     */
    @FXML
    public TextField zoneTexte;

    @FXML
    private Button bouton;


    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    /**
     * Charge la vue du jeu dans la fenêtre principale.
     * 
     * @throws IOException si le fichier FXML n'a pas pu être chargé
     */

    @FXML
    protected void onButton2Click() throws IOException {
        Stage currentStage = (Stage) bouton.getScene().getWindow();
        WindowManager windowManager = new WindowManager();
        windowManager.showView(currentStage, "game-view");
        System.out.println("Bouton 2 a été cliqué");
    }

    /**
     * Quitte l'application.
     */

    @FXML
    protected void onButton3Click() {
        System.out.println("Bouton 3 a été cliqué");
        System.exit(0);
    }


    /**
     * Méthode appelée après que le fichier FXML ait été chargé.
     * Elle permet de limiter le nombre de caractères dans la zone de texte.
     */
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