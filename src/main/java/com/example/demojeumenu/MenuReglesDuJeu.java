package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuReglesDuJeu extends BaseController {



    @FXML
    private void retour() {
        FXMLUtils.loadFXML("MenuPrincipal.fxml", scene);}

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("MenuTechniqueDeb1.fxml", scene);
    }
    @FXML
    private void btn2() { FXMLUtils.loadFXML("MenuTechniqueBas1.fxml", scene);
    }
    @FXML
    private void btn3() { FXMLUtils.loadFXML("MenuTechniqueIso1.fxml", scene);
    }
    @FXML
    private void btn4() { FXMLUtils.loadFXML("MenuTechniqueAv1.fxml", scene);
    }
}