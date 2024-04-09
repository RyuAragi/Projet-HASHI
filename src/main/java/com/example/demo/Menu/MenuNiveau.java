package com.example.demo.Menu;


import com.example.demo.FXMLUtils;
import com.example.demo.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;


@Controller
public class MenuNiveau extends BaseController {

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("/MenuTailleGrilleClassique.fxml", scene);
    }
    @FXML
    private void btn2() {
        FXMLUtils.loadFXML("/MenuTailleGrilleMoyen.fxml", scene);
    }
    @FXML
    private void btn3() {
        FXMLUtils.loadFXML("/MenuTailleGrilleDif.fxml", scene);
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}