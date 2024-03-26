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
    private void jouerGrille15x15(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        String levelDifficulty = buttonId.substring(buttonId.indexOf('_') + 1, buttonId.lastIndexOf('_'));
        String levelNumber = buttonId.substring(buttonId.lastIndexOf('_') + 1);
        String levelFileName = levelDifficulty + "-" + levelNumber + ".txt";
        String levelFileNameCorrected = getLevelFileNameCorrected(levelFileName);
        loadGrille(levelFileNameCorrected, scene);
    }

    private String getLevelFileNameCorrected(String levelFileName) {
        return levelFileName.substring(0, 1).toUpperCase() + levelFileName.substring(1);
    }

    private void loadGrille(String levelFileNameCorrected,Scene scene) {
        System.out.println(levelFileNameCorrected);
        String firstPart = levelFileNameCorrected.substring(0, levelFileNameCorrected.indexOf('-')).toLowerCase();
        GrilleControler.levelFileNameCorrected = firstPart + "/" + levelFileNameCorrected;
        System.out.println(GrilleControler.levelFileNameCorrected);
        FXMLUtils.loadFXML("Grille.fxml", scene);
    }

    public void loadFXML(String fxmlFile, Scene scene) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

            // Set the scene
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
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



