package com.example.demojeumenu;


import javafx.fxml.FXML;

public class MenuNiveau extends BaseController{


    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("MenuTailleGrille.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("Parametres.fxml", scene);
    }
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}