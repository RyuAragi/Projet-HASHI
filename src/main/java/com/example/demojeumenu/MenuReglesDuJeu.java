package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuReglesDuJeu extends BaseController {



    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("MenuTechnique1.fxml", scene);
    }
}