package com.example.demojeumenu;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
public class MenuTailleGrille extends BaseController{
    @FXML
    private Button jouer;



    @FXML
    private void afficherModalPopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalPopUp.fxml"));
            Parent root = loader.load();

            // Obtenez la scène actuelle du bouton "Jouer cette grille 15x15"

            Scene scene = jouer.getScene();
            // Obtenez la fenêtre principale à partir de la scène
            Stage primaryStage = (Stage) scene.getWindow();

            // Créez une nouvelle fenêtre Stage pour la fenêtre modale
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(primaryStage); // Définissez la fenêtre principale comme propriétaire de la fenêtre modale
            modalStage.setTitle("Modal Pop Up");
            modalStage.setScene(new Scene(root));

            // Afficher la fenêtre modale
            modalStage.show();
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



