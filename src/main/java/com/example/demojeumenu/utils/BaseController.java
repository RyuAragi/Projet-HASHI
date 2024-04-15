package com.example.demojeumenu.utils;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.JsonApp;
import com.example.demojeumenu.Menu.MenuInfoController;
import com.example.demojeumenu.controler.GlobalVariables;
import com.example.demojeumenu.controler.PopupWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
/**
 * Classe de base pour les contrôleurs.
 */
public abstract class BaseController {
    /**
     * La scène actuelle.
     */
    protected static Scene scene;
    /**
     * Le nom du fichier de niveau.
     */
    protected static boolean isJouerButtonEnabled = false; // Change this to false
    /**
     * Le nom du fichier de niveau.
     */
    private boolean EtatParametres = false; // variable pour savoir si on est dans le menu parametres
    /**
     * Le nom du fichier de niveau.
     */
    protected String levelFileName;
    /**
     * Le nom du fichier de niveau.
     */
    public void setScene(Scene scene) {
        BaseController.scene = scene;
        BaseController.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (EtatParametres) {
                    FXMLUtils.goBack(scene);
                    EtatParametres = false;
                }
                else if (GlobalVariables.isInGame()) {
                    //  fenêtre de paramètres à la partie
                    ParametreForGame();
                }
                else if (!scene.getStylesheets().contains("/Parametres.fxml")) {
                    FXMLUtils.loadFXML("/Parametres.fxml", scene);
                    EtatParametres = true;
                }
            }
        });
    }

    /**
     * Méthode pour revenir à l'écran d'accueil.
     */

    protected static void ParametreForGame() {
        // Charger le fichier FXML de l'external frame
        FXMLLoader loader = new FXMLLoader(JsonApp.class.getResource("/ParametresForGrille.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader.getController();

        // Créer la scène de l'external frame
        Scene scenePopup = new Scene(root);
        scenePopup.setFill(Color.TRANSPARENT);
        scenePopup.getStylesheets().add(Objects.requireNonNull(JsonApp.class.getResource("/css/styles.css")).toExternalForm());
        scenePopup.getRoot().setEffect(new DropShadow());

        // Créer une nouvelle fenêtre pour l'external frame
        Stage popupWindow = new Stage();
        popupWindow.setResizable(false);
        popupWindow.setWidth(800);
        popupWindow.setHeight(700);
        popupWindow.setAlwaysOnTop(true);
        popupWindow.initStyle(StageStyle.TRANSPARENT);
        popupWindow.initOwner(scene.getWindow());
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setUserData(false);

        PopupWindowController.setStage(popupWindow);

        popupWindow.setOnHidden(event -> scene.getRoot().setEffect(null));

        popupWindow.setScene(scenePopup);

        // Appliquer l'effet d'assombrissement à la scène principale
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);

        // Montrer la fenêtre contextuelle
        popupWindow.showAndWait();
    }

    /**
     * Méthode pour revenir à l'écran d'accueil.
     */
    public void param(ActionEvent actionEvent) {
        FXMLUtils.loadFXML("/Parametres.fxml", scene);
    }
    /**
     * Méthode pour revenir à l'écran d'accueil.
     */
    @FXML
    protected void reglePopUP() {
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);
        // Charger le fichier FXML de l'external frame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Regledejeu.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Créer la scène de l'external frame
        Scene sceneInfo = new Scene(root);
        sceneInfo.setFill(Color.TRANSPARENT);
        sceneInfo.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        sceneInfo.getRoot().setEffect(new DropShadow());

        // Créer une nouvelle fenêtre pour l'external frame
        Stage externalFrame = new Stage();
        externalFrame.setResizable(false);
        externalFrame.setWidth(800);
        externalFrame.setHeight(700);
        externalFrame.setAlwaysOnTop(true);
        externalFrame.initStyle(StageStyle.TRANSPARENT);
        externalFrame.initOwner(scene.getWindow());
        MenuInfoController.setStage(externalFrame);

        externalFrame.setOnHidden(event -> scene.getRoot().setEffect(null));

        externalFrame.setScene(sceneInfo);
        externalFrame.show();
    }

}