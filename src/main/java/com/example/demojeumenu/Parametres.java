package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Parametres extends BaseController {
    private Scene scene;

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void back_button() {
        FXMLUtils.goBack(scene);
    }

}
