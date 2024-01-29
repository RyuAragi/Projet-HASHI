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

/**
 * Contrôleur de la vue principale.
 */

public class appController {
    /**
     * Le nombre maximum de caractères dans la zone de texte.
     */
    private static final int MAX_CHARS = 20;

    /**
     * Le bouton pour quitter l'application.
     * Il est lié à la méthode {@link #onButton3Click()}.
     */
    @FXML
    public Button backButton, bouton;

    /**
     * La zone de texte pour le nom du joueur.
     */
    @FXML
    public TextField zoneTexte;


    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    /**
     * Charge la vue du jeu dans la fenêtre principale.
     * @throws IOException si le fichier FXML n'a pas pu être chargé
     */

    @FXML
    protected void onButton2Click() throws IOException {
        loadGameView();
        onBackButtonClick();
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
     * Charge la vue principale dans la fenêtre principale.
     * @throws IOException si le fichier FXML n'a pas pu être chargé
     */

    @FXML
    protected void onBackButtonClick() throws IOException {
        loadScene("hello-view.fxml");
    }

    /**
     * Charge la vue du jeu dans la fenêtre principale.
     * @throws IOException si le fichier FXML n'a pas pu être chargé
     */
    private void loadGameView() throws IOException {
        loadScene("game-view.fxml");
    }

    /**
     * Charge une scène FXML dans la fenêtre principale.
     * @param fxmlFile le fichier FXML à charger
     * @throws IOException si le fichier FXML n'a pas pu être chargé
     */

    private void loadScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) bouton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode appelée après que le fichier FXML ait été chargé.
     * Elle permet de limiter le nombre de caractères dans la zone de texte.
     */
    @FXML
    protected void initialize() {
        zoneTexte.setPromptText("Entrez votre nom");
        zoneTexte.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() > MAX_CHARS ? null : change));
    }
}