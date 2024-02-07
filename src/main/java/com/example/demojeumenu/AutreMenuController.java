package com.example.demojeumenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class AutreMenuController extends BaseController {

    private Scene scene;

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
    public void btn2(ActionEvent actionEvent) {
    }
    public void btn1(ActionEvent actionEvent) {
    }
}