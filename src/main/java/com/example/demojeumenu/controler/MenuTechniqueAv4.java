package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;

public class MenuTechniqueAv4 extends BaseController {

    /**
     * Méthode d'action du bouton retour. Permet de retourner au menu précédant.
     */
    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    /**
     * Méthode d'action du bouton retour. Permet d'acceder au menu suivant.
     */

    @FXML
    private void quitter() {
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void param() {FXMLUtils.loadFXML("Parametres.fxml", scene);
    }
}
