package com.example.demojeumenu;

import javafx.fxml.FXML;

public class AutreMenuController3 extends BaseController{
    @FXML
    private void btn1() {
        GlobalVariables.getUser();
    }
    @FXML
    private void btn2() {
        GlobalVariables.getUser();
    }
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}