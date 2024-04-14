package com.example.demojeumenu;

import com.example.demojeumenu.controler.GlobalVariables;
import com.example.demojeumenu.utils.BaseController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Classe utilitaire pour charger les fichiers FXML
 */

public class FXMLUtils {
    private static final int MAX_CHARS = 20;
    private static final String DEFAULT_TEXT = "Entrez votre nom";
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
     * @param scene scène
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
            Logger logger = Logger.getLogger(FXMLUtils.class.getName());
            logger.severe("Une erreur est survenue lors du chargement du fichier FXML: " + e.getMessage());
        }
    }

    public static void loadFXML(String fxml, Scene scene, String levelFileName,boolean chargement) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxml));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof GrilleControler) {
                ((GrilleControler) controller).initData(levelFileName,chargement);
            }

            if (fxmlHistory.isEmpty() || !fxmlHistory.peek().equals(fxml)) {
                fxmlHistory.push(fxml);
            }

            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String topHistory(){
        return fxmlHistory.peek();
    }

    /**
     * Ajoute le fichier FXML à l'historique
     *
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
        if (fxmlHistory.size() > 1) { // On ne peut pas revenir en arrière si on est sur la première page
            fxmlHistory.pop(); // Remove the current page from the history
            loadFXML(fxmlHistory.peek(), scene); // Load the previous page
        }
    }

    /**
     * Applique les styles à la scène
     * @param scene scène
     */

    public static void applySceneStyles(Scene scene) {
        ObservableList<Screen> screens = Screen.getScreens();
        for (Screen screen : screens) {
            Rectangle2D bounds = screen.getBounds();
            double screenHeight = bounds.getHeight();

            // Affiche la hauteur de l'écran dans le terminal

            Node titreNode = scene.lookup(".titre"); // Récupère le noeud avec le style .titre

            // calcule -fx-pref-height par rapport à la hauteur de l'écran
            double prefHeight = getHeight(screenHeight);
            double prefWidth = getWidth(screenHeight);

            if (titreNode != null) {
                titreNode.setStyle("-fx-pref-height: " + prefHeight + "; -fx-pref-width: " + prefWidth +";"); // Applique le style calculé
            }
        }
    }

    private static double getHeight(double screenHeight) {
        double prefHeight;
        if (screenHeight <= 1032.0) {
            prefHeight = 145;
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

    private static double getWidth(double screenHeight) {
        double prefWidth;
        if (screenHeight <= 1032.0) {
            prefWidth = 600;
        } else if (screenHeight <= 1097.0) {
            prefWidth = 650;
        } else if (screenHeight <= 1392.0) {
            prefWidth = 700;
        } else if (screenHeight <= 1497.0) {
            prefWidth = 750;
        } else {
            prefWidth = 800;
        }
        return prefWidth;
    }

    public static void initializeTextField(TextField zoneTexte) {
        // Liaison bidirectionnelle entre la propriété userInput de GlobalVariables et la propriété text de la zoneTexte
        zoneTexte.textProperty().bindBidirectional(GlobalVariables.userInputProperty());

        // Gestion du clic pour effacer le texte par défaut
        zoneTexte.setOnMouseClicked(event -> {
            if (DEFAULT_TEXT.equals(zoneTexte.getText())) {
                zoneTexte.setText("");
            }
        });

        // Mise à jour de la propriété userInput lorsque le texte de la zone de texte change
        zoneTexte.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(DEFAULT_TEXT)) {
                GlobalVariables.setUserInput(newValue);
                JsonApp.checkJsonFileExists(newValue); // Ajout de la vérification du fichier ici
            }
        });

        // Gestion du focus pour afficher le texte par défaut si nécessaire
        zoneTexte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) { // Si la zone de texte reçoit le focus
                if (zoneTexte.getText().equals(DEFAULT_TEXT)) {
                    zoneTexte.end(); // Place le curseur à la fin du texte
                }
            } else if (zoneTexte.getText().isEmpty()) {
                zoneTexte.setText(DEFAULT_TEXT);
            }
        });

        // Limiter le nombre de caractères dans la zone de texte
        zoneTexte.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() > MAX_CHARS ? null : change));
    }

    private FXMLUtils() {
        throw new IllegalStateException("Utility class");
    }

}
