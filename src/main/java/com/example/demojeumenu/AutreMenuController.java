package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Scene;

public class AutreMenuController extends BaseController {

    private Scene scene;

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("AutreMenu2.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.loadFXML("MenuPrincipal.fxml", scene);
    }

}