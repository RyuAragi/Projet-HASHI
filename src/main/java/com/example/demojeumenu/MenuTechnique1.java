package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechnique1 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}
