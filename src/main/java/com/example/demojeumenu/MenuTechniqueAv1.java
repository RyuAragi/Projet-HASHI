package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueAv1 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueAv2.fxml", scene);
    }
}
