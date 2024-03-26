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
        String levelName = button.getId();
        String[] parts = levelName.split("-");
        if (parts.length > 0) {
            String difficulty = parts[0].toLowerCase();
            levelFileName = difficulty + "/" + levelName + ".txt";
            System.out.println("jouerGrille15x15Button levelFileName: " + levelFileName);
            FXMLUtils.loadFXML("Grille.fxml", scene, levelFileName);
        } else {
            System.err.println("Invalid level name format: " + levelName);
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



