package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueBas3 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueBas4.fxml", scene);
    }
}
