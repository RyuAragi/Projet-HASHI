package com.example.demojeumenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;


public class AutreMenuController3 extends BaseController{

    private Scene scene;
    @FXML


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btn1() {
        GlobalVariables.getUser();
    }

    @FXML
    private void btn2() {
        GlobalVariables.getUser();
    }


    @FXML
    private void retour() {
        FXMLUtils.loadFXML("AutreMenu2.fxml", scene);
    }


}