package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueAv4 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void quitter() {
        FXMLUtils.loadFXML("MenuReglesDuJeu.fxml", scene);
    }
}
