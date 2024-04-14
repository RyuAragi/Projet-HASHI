package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.controler.GlobalVariables;
import com.example.demojeumenu.controler.PopupWindowController;
import com.example.demojeumenu.controler.PopupWindowControllerLB;
import com.example.demojeumenu.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


@Controller
public class MenuTailleGrille extends BaseController {
   /* @FXML
    private Button jouerGrille15x15Button;*/

    public static String level_info;
    // Méthode pour afficher le leaderboard.

    @FXML
    public void leaderboard(){
        // Charger le fichier FXML pour la fenêtre externe.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PopupWindowLB.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PopupWindowControllerLB controller = loader.getController();

        // Créer la scène pour la fenêtre externe.
        Scene scenePopup = new Scene(root);
        scenePopup.setFill(Color.TRANSPARENT);
        scenePopup.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        scenePopup.getRoot().setEffect(new DropShadow());

        // Créer une nouvelle fenêtre pour la fenêtre externe.
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

        // Appliquer l'effet d'assombrissement à la scène principale.
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);
        PopupWindowControllerLB.setStage(popupWindow);


        // Afficher la fenêtre popup.
        popupWindow.showAndWait();
    }


    @FXML
    private void jouerGrille15x15(ActionEvent event) {
        Button button = (Button) event.getSource();
        String levelName = button.getId();
        String[] parts = levelName.split("-");
        if (parts.length > 0) {
            String difficulty = parts[0].toLowerCase();
            levelFileName = difficulty + "/" + levelName + ".txt";
            System.out.println("jouerGrille15x15Button levelFileName: " + levelFileName);
            level_info = levelName;
            File fichier = new File("JacobHashi/Sauvegarde/niveau/"+ GlobalVariables.getUserInput()+"/"+levelFileName.substring(0,levelFileName.length()-4)+".ser");
            boolean chargement = false;
            if(fichier.exists() && fichier.isFile()){
                chargement = true;
            }
            FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, levelFileName, chargement);

        } else {
            System.err.println("Invalid level name format: " + levelName);
        }
    }


    // Méthodes pour naviguer vers d'autres menus de taille de grille.

    @FXML
    private void btnS() {
        FXMLUtils.loadFXML("/MenuTailleGrilleClassique2.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("/MenuTailleGrilleMoyen2.fxml", scene);
    }
    @FXML
    private void btn3() {
        FXMLUtils.loadFXML("/MenuTailleGrilleDif2.fxml", scene);
    }
    // Méthode pour retourner en arrière.
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}



