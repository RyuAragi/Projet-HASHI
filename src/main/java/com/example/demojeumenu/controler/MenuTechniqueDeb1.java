package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.Objects;
/**
 * Contrôleur pour le premier menu du menu technique débutant.
 */
@Controller
public class MenuTechniqueDeb1 extends BaseController {

    /**
     * Bouton retour au menu précedent
     */
    @FXML
    public Button bouton_retour;

    /**
     * Bouton pour aller au menu suivant
     */
    @FXML
    public Button bouton_suivant;

    /**
     *  Stage permettant de recuperer la fenêtre de l'application.
     */
    public static Stage stage = null;

    /**
     * Setter du stage
     * @param st nouveau stage
     */
    public static void setStage(Stage st){
        stage = st;
    }


    /**
     * Méthode d'action du bouton retour. Permet de retourner au menu précédant.
     */

    @FXML
    private void retour() {
        System.out.println("retour");
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml") && stage!=null){
            stage.close();
        }
        else{
            FXMLUtils.goBack(scene);
        }
    }

    /**
     * Méthode d'action du bouton retour. Permet d'acceder au menu suivant.
     */

    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueDeb2.fxml", scene);
    }

    /**
     * Initialisation du menu
     */
    @FXML
    private void initialize(){
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml")){
            bouton_suivant.setVisible(false);
            bouton_suivant.setDisable(true);
        }
    }
}
