package com.example.demojeumenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;


public class AutreMenuController2 extends BaseController{

    private Scene scene;
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btn1() {
        GlobalVariables.getUser();
        FXMLUtils.loadFXML("Parametres.fxml", scene);
    }
    @FXML
    private void btn2() {
        GlobalVariables.getUser();
    }


    @FXML
    private void retour() {
        FXMLUtils.loadFXML("AutreMenu.fxml", scene);
    }


}