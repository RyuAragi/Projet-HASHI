package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controler du menu technique avancé 4.
 */
public class MenuTechniqueAv4 extends BaseController {

    /**
     * Bouton retour au menu précedent
     */
    /**
     * Méthode d'action du bouton retour. Permet de retourner au menu précédant.
     */
    @FXML
    public Button bouton_retour;

    /**
     * Méthode d'action du bouton quitter. Permet de retourner au menu précédant.
     */

    /**
     * Bouton pour aller au menu suivant
     */
    @FXML
    public Button bouton_quitter;

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
     * Méthode d'action qui permet d'afficher la grille.
     */

    /**
     * Initialisation du menu
     */
    @FXML
    private void initialize(){
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml")){
            bouton_quitter.setVisible(false);
            bouton_quitter.setDisable(true);
        }
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
}