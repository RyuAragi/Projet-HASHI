package com.example.demojeumenu;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MenuTailleGrille extends BaseController{
   /* @FXML
    private Button jouerGrille15x15Button;*/


    @FXML
    private void jouerGrille15x15() {
        /*
        try {
            ColorAdjust darkColorAdjust = new ColorAdjust();
            darkColorAdjust.setBrightness(-0.5);
            scene.getRoot().setEffect(darkColorAdjust);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupWindowLB.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle fenêtre Stage pour le popup
            Stage modalStage = new Stage();
            //modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Popup");
            modalStage.setAlwaysOnTop(true);
            modalStage.initOwner(scene.getWindow()); // Définir la fenêtre principale comme propriétaire de la fenêtre modale
            PopupWindowControllerLB.setStage(modalStage);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            modalStage.setScene(scene);

            // Supprimer la barre d'outils
            modalStage.initStyle(StageStyle.TRANSPARENT);
            modalStage.setOnHidden(event -> {
                scene.getRoot().setEffect(null);
            });
            // Afficher la fenêtre modale
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);
        // Charger le fichier FXML de l'external frame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupWindowLB.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Créer la scène de l'external frame
        Scene sceneInfo = new Scene(root);
        sceneInfo.setFill(Color.TRANSPARENT);
        sceneInfo.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        sceneInfo.getRoot().setEffect(new DropShadow());

        // Créer une nouvelle fenêtre pour l'external frame
        Stage externalFrame = new Stage();
        externalFrame.setResizable(false);
        externalFrame.setWidth(800);
        externalFrame.setHeight(700);
        externalFrame.setAlwaysOnTop(true);
        externalFrame.initStyle(StageStyle.TRANSPARENT);
        externalFrame.initOwner(scene.getWindow());
        PopupWindowControllerLB.setStage(externalFrame);
        externalFrame.initModality(Modality.APPLICATION_MODAL);
        externalFrame.setUserData(false);

        externalFrame.setOnHidden(event -> scene.getRoot().setEffect(null));

        externalFrame.setScene(sceneInfo);
        externalFrame.show();
    }


    @FXML
    private void btnS() {
        FXMLUtils.loadFXML("MenuTailleGrilleClassique2.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("MenuTailleGrilleMoyen2.fxml", scene);
    }
    @FXML
    private void btn3() {
        FXMLUtils.loadFXML("MenuTailleGrilleDif2.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }



}



