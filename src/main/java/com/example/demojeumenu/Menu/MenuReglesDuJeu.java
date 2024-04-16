package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;


/**
 * Classe permettant de gérer le menu des règles du jeu.
 */

@Controller
public class MenuReglesDuJeu extends BaseController {

    /**
     * Méthode d'action pour afficher les règles du jeu.
     */

    @FXML
    private void showRules() {
        reglePopUP();
    }


    /**
     * Méthode d'action pour retourner au menu précédant.
     */

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }

    /**
     * Méthode d'action pour accéder au menu des techniques de jeu.
     */

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("/MenuTechniqueDeb1.fxml", scene);
    }

    /**
     * Méthode d'action pour accéder au menu des techniques de jeu.
     */
    @FXML
    private void btn2() { FXMLUtils.loadFXML("/MenuTechniqueBas1.fxml", scene);
    }

    /**
     * Méthode d'action pour accéder au menu des techniques de jeu.
     */
    @FXML
    private void btn3() { FXMLUtils.loadFXML("/MenuTechniqueIso1.fxml", scene);
    }

    /**
     * Méthode d'action pour accéder au menu des techniques de jeu.
     */
    @FXML
    private void btn4() { FXMLUtils.loadFXML("/MenuTechniqueAv1.fxml", scene);
    }
}