package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueIso3 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("MenuTechniqueIso4.fxml", scene);
    }
}
