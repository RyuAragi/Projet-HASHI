package com.example.demojeumenu.Menu;



import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuTailleGrille extends BaseController {
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
    @FXML
    private void param() {
        FXMLUtils.loadFXML("Parametres.fxml", scene);
    }



}



