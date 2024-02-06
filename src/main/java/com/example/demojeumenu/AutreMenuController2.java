package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Scene;

public class AutreMenuController2 extends BaseController {

    private Scene scene;

    @FXML

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btn1() {

    }

    @FXML
    private void retour() {
        FXMLUtils.loadFXML("AutreMenu.fxml", scene);
    }

}