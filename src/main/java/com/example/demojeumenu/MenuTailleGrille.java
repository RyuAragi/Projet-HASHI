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
    private Button jouerGrille15x15Button;


    @FXML
    private void jouerGrille15x15() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupWindowLB.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle fenêtre Stage pour le popup
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Popup");
            modalStage.initOwner(jouerGrille15x15Button.getScene().getWindow()); // Définir la fenêtre principale comme propriétaire de la fenêtre modale
            modalStage.setScene(new Scene(root));

            // Supprimer la barre d'outils
            modalStage.initStyle(StageStyle.UNDECORATED);

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



