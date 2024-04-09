package com.example.demo.technique;

import com.example.demo.FXMLUtils;
import com.example.demo.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class MenuTechniqueBas4 extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void quitter() {
        FXMLUtils.loadFXML("/MenuReglesDuJeu.fxml", scene);
    }

}