package com.example.demojeumenu;

import javafx.fxml.FXML;

public class AutreMenuController2 extends BaseController {

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("AutreMenu3.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("Parametres.fxml", scene);
    }
}