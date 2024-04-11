package com.example.demojeumenu;

import com.example.demojeumenu.controler.PopupWindowController;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



public class JsonApp extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(JsonApp.class.getName());
    private static final HashMap<String, Boolean> shownPopups = new HashMap<>();


    private JsonApp() {
        throw new IllegalStateException("Classe utilitaire");
    }

    public static void checkJsonFileExists(String username) {
        InputStream resourceStream = JsonApp.class.getResourceAsStream("/json/" + username + ".json");
        if (resourceStream == null) {
            LOGGER.severe("Ressource introuvable: json/" + username + ".json");
            return;
        }

        try {
            resourceStream.close();
        } catch (IOException e) {
            LOGGER.severe("Error closing resource stream: " + e.getMessage());
        }

        if (!shownPopups.containsKey(username)) {
            showPopupWindow(username);
            shownPopups.put(username, true);
        } else {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(String.format("Le fichier pour l'utilisateur %s n'existe pas", username));
            }
        }
    }

    private static void showPopupWindow(String username) {
        // Load the FXML file for the external frame
        FXMLLoader loader = new FXMLLoader(JsonApp.class.getResource("/PopupWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the controller and set the username
       loader.getController();
        //controller.setUsername(username);

        // Create the scene for the external frame
        Scene scenePopup = new Scene(root);
        scenePopup.setFill(Color.TRANSPARENT);
        scenePopup.getStylesheets().add(Objects.requireNonNull(JsonApp.class.getResource("/css/styles.css")).toExternalForm());
        scenePopup.getRoot().setEffect(new DropShadow());

        // Create a new window for the external frame
        Stage popupWindow = new Stage();
        popupWindow.setResizable(false);
        popupWindow.setWidth(800);
        popupWindow.setHeight(700);
        popupWindow.setAlwaysOnTop(true);
        popupWindow.initStyle(StageStyle.TRANSPARENT);
        if (scene != null && scene.getWindow() != null) {
            popupWindow.initOwner(scene.getWindow());
        }
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setUserData(false);

        // Set the stage in the controller
        PopupWindowController.setStage(popupWindow);

        // Set the effect when the window is hidden
        popupWindow.setOnHidden(event -> scene.getRoot().setEffect(null));

        // Set the scene for the popup window
        popupWindow.setScene(scenePopup);

        // Apply the darkening effect to the main scene
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);

        // Show the popup window
        popupWindow.showAndWait();
    }

    public static void removeShownPopup(String username) {
        shownPopups.remove(username);
    }
}