package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.controler.PopupWindowController;
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

import java.io.IOException;
import java.util.Objects;


@Controller
public class MenuTailleGrille extends BaseController {
   /* @FXML
    private Button jouerGrille15x15Button;*/

    @FXML
    private void leaderboard(){
        // Load the FXML file for the external frame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PopupWindowLB.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader.getController();

        // Create the scene for the external frame
        Scene scenePopup = new Scene(root);
        scenePopup.setFill(Color.TRANSPARENT);
        scenePopup.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        scenePopup.getRoot().setEffect(new DropShadow());

        // Create a new window for the external frame
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

        // Apply the darkening effect to the main scene
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);

        // Show the popup window
        popupWindow.showAndWait();
    }


    @FXML
    private void jouerGrille15x15(ActionEvent event) {
        //leaderboard();
        Button button = (Button) event.getSource();
        String levelName = button.getId();
        String[] parts = levelName.split("-");
        if (parts.length > 0) {
            String difficulty = parts[0].toLowerCase();
            levelFileName = difficulty + "/" + levelName + ".txt";
            System.out.println("jouerGrille15x15Button levelFileName: " + levelFileName);
            FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, levelFileName, true);
        } else {
            System.err.println("Invalid level name format: " + levelName);
        }
    }



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

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
    @FXML
    private void param() {
        FXMLUtils.loadFXML("Parametres.fxml", scene);
    }



}



