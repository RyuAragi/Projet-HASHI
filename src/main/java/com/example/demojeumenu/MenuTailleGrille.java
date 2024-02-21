package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTailleGrille extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}
