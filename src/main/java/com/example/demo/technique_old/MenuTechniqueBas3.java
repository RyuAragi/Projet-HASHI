package com.example.demo.technique_old;

import com.example.demo.FXMLUtils;
import com.example.demo.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class MenuTechniqueBas3 extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueBas4.fxml", scene);
    }
}
