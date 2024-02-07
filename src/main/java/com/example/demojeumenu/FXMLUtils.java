package com.example.demojeumenu;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilitaire pour charger les fichiers FXML
 */

public class FXMLUtils {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(FXMLUtils.class.getName());
    /**
     * Cache des fichiers FXML
     */
    private static final Map<String, Parent> sceneCache = new HashMap<>();

    /**
     * Historique des fichiers FXML
     */
    private static final Deque<String> fxmlHistory = new ArrayDeque<>();

    /**
     * Charge le fichier FXML
     *
     * @param fxmlFileName nom du fichier FXML
     * @param scene        scène
     */

    public static void loadFXML(String fxmlFileName, Scene scene) {
        Parent root;
        try {
            if (sceneCache.containsKey(fxmlFileName)) {
                root = sceneCache.get(fxmlFileName);
            } else {
                FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlFileName));
                root = loader.load();
                BaseController controller = loader.getController();
                controller.setScene(scene);
                sceneCache.put(fxmlFileName, root);
            }
            scene.setRoot(root);

            if (fxmlHistory.isEmpty() || !fxmlHistory.peek().equals(fxmlFileName)) {
                fxmlHistory.push(fxmlFileName);
            }

            applySceneStyles(scene);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(FXMLUtils.class);
            logger.error("Une erreur est survenue lors du chargement du fichier FXML", e);
        }
    }

    /**
     * Ajoute le fichier FXML à l'historique
     * @param fxmlFileName nom du fichier FXML
     */
    public static void addHistory(String fxmlFileName) {
        if (fxmlHistory.isEmpty() || !fxmlHistory.peek().equals(fxmlFileName)) {
            fxmlHistory.push(fxmlFileName); // Ajoute le fichier FXML à l'historique
        }
    }

    /**
     * Retourne à la page précédente
     * @param scene scène
     */

    public static void goBack(Scene scene) {
        LOGGER.info(() -> "fxmlHistory.size() : "+fxmlHistory.size());
        if (fxmlHistory.size() > 1) { // On ne peut pas revenir en arrière si on est sur la première page
            fxmlHistory.pop(); // Remove the current page from the history
            loadFXML(fxmlHistory.peek(), scene); // Load the previous page
            LOGGER.info(() -> "fxmlHistory.peek() : "+fxmlHistory.peek()); // Display the previous page in the terminal
        }
    }

    /**
     * Applique les styles à la scène
     * @param scene scène
     */

    public static void applySceneStyles(Scene scene) {

        // recupère la hauteur de l'écran
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenHeight = screenBounds.getHeight();

        LOGGER.info(()-> "screenHeight : " + screenHeight); // Affiche la hauteur de l'écran dans le terminal

        Node titreNode = scene.lookup(".titre"); // Récupère le noeud avec le style .titre

        // calcule -fx-pref-height par rapport à la hauteur de l'écran
        double prefHeight = getPrefHeight(screenHeight);

        if (titreNode != null) {
            titreNode.setStyle("-fx-pref-height: " + prefHeight + "; -fx-text-fill: white; -fx-pref-width: 600;"); // Applique le style calculé
        }
    }

    private static double getPrefHeight(double screenHeight) {
        double prefHeight;
        if (screenHeight <= 805.0) {
            prefHeight = 145;
        } else if (screenHeight <= 1032.0) {
            prefHeight = 145 + ((screenHeight - 805.0) * (170 - 145)) / (1032.0 - 805.0);
        } else if (screenHeight <= 1097.0) {
            prefHeight = 170 + ((screenHeight - 1032.0) * (190 - 170)) / (1097.0 - 1032.0);
        } else if (screenHeight <= 1392.0) {
            prefHeight = 190 + ((screenHeight - 1097.0) * (215 - 190)) / (1392.0 - 1097.0);
        } else if (screenHeight <= 1497.0) {
            prefHeight = 215 + ((screenHeight - 1392.0) * (240 - 215)) / (1497.0 - 1392.0);
        } else {
            prefHeight = 240;
        }
        return prefHeight;
    }

    private FXMLUtils() {
        throw new IllegalStateException("Utility class");
    }
}