package com.example.demo.technique;

import com.example.demo.FXMLUtils;
import com.example.demo.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class MenuTechniqueDeb1 extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueDeb2.fxml", scene);
    }
}
