package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueDeb1 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueDeb2.fxml", scene);
    }
}
