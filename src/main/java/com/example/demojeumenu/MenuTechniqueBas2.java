package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueBas2 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueBas3.fxml", scene);
    }
}
