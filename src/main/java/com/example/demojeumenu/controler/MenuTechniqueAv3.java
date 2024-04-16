package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controler du menu technique avancé 3.
 */
public class MenuTechniqueAv3 extends BaseController {

    /**
     * Bouton de retour.
     */
    @FXML
    public Button bouton_retour;

    /**
     * Bouton suivant.
     */
    @FXML
    public Button bouton_suivant;

    /**
     * Stage.
     */

    /**
     *  Stage permettant de recuperer la fenêtre de l'application.
     */
    public static Stage stage = null;

    /**
     * Méthode permettant de définir la scène.
     * @param st la scène à définir.
     */

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
    /**
     * Bouton retour au menu précedent
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

    /**
     * Bouton pour aller au menu suivant
     */
    @FXML
    private void suivant() {
        FXMLUtils.loadFXML("/MenuTechniqueAv4.fxml", scene);
    }


    /**
     * Méthode d'initialisation.
     */

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
