package com.example.demojeumenu;

import javafx.fxml.FXML;

public class MenuTechniqueBas4 extends BaseController{
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

}