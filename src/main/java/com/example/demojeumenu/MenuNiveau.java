package com.example.demojeumenu;


import javafx.fxml.FXML;

public class MenuNiveau extends BaseController{

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("MenuTailleGrilleClassique.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("MenuTailleGrilleMoyen.fxml", scene);
    }
    @FXML
    private void btn3() {
        FXMLUtils.loadFXML("MenuTailleGrilleDif.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}