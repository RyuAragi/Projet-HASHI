package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTailleGrille extends BaseController{
    @FXML
    private void btnS() {
        FXMLUtils.loadFXML("MenuTailleGrille2.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("MenuTailleGrilleMoyen2.fxml", scene);
    }
    @FXML
    private void btn3() {
        FXMLUtils.loadFXML("MenuTailleGrilleDif2.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

}


