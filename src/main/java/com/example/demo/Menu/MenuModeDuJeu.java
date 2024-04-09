package com.example.demo.Menu;

import com.example.demo.FXMLUtils;
import com.example.demo.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;


@Controller
public class MenuModeDuJeu extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
    public void btn2(ActionEvent actionEvent) {
    }
    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("/MenuNiveau.fxml", scene);
    }
}