package com.example.demojeumenu;

import javafx.fxml.FXML;

public class AutreMenuController3 extends BaseController{
    @FXML
    private void btn1() {
        GlobalVariables.getUserInput();
    }
    @FXML
    private void btn2() {
        GlobalVariables.getUserInput();
    }
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}