package com.example.demojeumenu;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FXMLUtils {
    private static final Map<String, Parent> sceneCache = new HashMap<>();
    private static final Deque<String> fxmlHistory = new ArrayDeque<>();

    public static Parent loadFXML(String fxmlFileName, Scene scene) {
        Parent root = null;
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
        return root;
    }

    public static void addHistory(String fxmlFileName) {
        if (fxmlHistory.isEmpty() || !fxmlHistory.peek().equals(fxmlFileName)) {
            fxmlHistory.push(fxmlFileName);
        }
    }

    public static void goBack(Scene scene) {
        System.out.println("fxmlHistory.size() : "+fxmlHistory.size());
        if (fxmlHistory.size() > 1) { // Ensure there is a previous FXML to go back to
            fxmlHistory.pop(); // Remove the current FXML from the history
            loadFXML(fxmlHistory.peek(), scene); // Load the previous FXML
            System.out.println("fxmlHistory.peek() : "+fxmlHistory.peek());
        }
    }

    public static void applySceneStyles(Scene scene) {

        // recupère la hauteur de l'écran
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenHeight = screenBounds.getHeight();

        System.out.println("screenHeight : "+screenHeight);

        Node titreNode = scene.lookup(".titre");

        // calcule -fx-pref-height par rapport à la hauteur de l'écran
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

        // Apply the calculated style
        titreNode.setStyle("-fx-pref-height: " + prefHeight + "; -fx-text-fill: white; -fx-pref-width: 600;");
    }
}