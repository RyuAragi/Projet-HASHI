package com.example.demojeumenu.Menu;


import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;


@Controller
public class MenuNiveau extends BaseController {
    // Méthodes pour naviguer vers différents menus de niveaux de grille.

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
    // Méthode pour retourner en arrière.

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}