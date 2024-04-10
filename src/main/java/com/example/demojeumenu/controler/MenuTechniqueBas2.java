package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuTechniqueBas2 extends BaseController {
    @FXML
    public Button retour;

    @FXML
    public Button suivant;

    public static Stage stage = null;

    public static void setStage(Stage st){
        stage = st;
    }

    @FXML
    private void retour() {
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml") && stage!=null){
            stage.close();
        }
        else{
            FXMLUtils.goBack(scene);
        }
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueBas3.fxml", scene);
    }
}
