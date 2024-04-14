package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;


@Controller
public class MenuModeDuJeu extends BaseController {
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    // Méthode pour charger les diff niveau.

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("/MenuNiveau.fxml", scene);
    }
}