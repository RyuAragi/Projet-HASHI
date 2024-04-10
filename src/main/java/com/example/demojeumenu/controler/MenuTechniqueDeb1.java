package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Objects;

public class MenuTechniqueDeb1 extends BaseController {

    @FXML
    private void retour() {
        if(!Objects.equals(FXMLUtils.topHistory(), "/GrilleDisplay.fxml")) {
            FXMLUtils.goBack(scene);
        }
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueDeb2.fxml", scene);
    }
}
