package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTailleGrille extends BaseController{
    @FXML
    private void btnS() {
        FXMLUtils.loadFXML("MenuTailleGrille2.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

}


