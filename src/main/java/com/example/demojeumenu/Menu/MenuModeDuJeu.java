package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuModeDuJeu extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
    public void btn2(ActionEvent actionEvent) {
    }
    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("MenuNiveau.fxml", scene);
    }
}