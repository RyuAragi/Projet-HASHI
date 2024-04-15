package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controler du menu technique bas 1.
 */
public class MenuTechniqueBas1 extends BaseController {

    /**
     * Méthode d'action du bouton retour. Permet de retourner au menu précédant.
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
    public static Stage stage = null;
    /**
     * Méthode permettant de définir la scène.
     * @param st la scène à définir.
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
        FXMLUtils.loadFXML("/MenuTechniqueBas2.fxml", scene);
    }

    /**
     * Méthode initialize qui permet de cacher le bouton suivant si on est sur la grille.
     */
    @FXML
    private void initialize(){
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml")){
            bouton_suivant.setVisible(false);
            bouton_suivant.setDisable(true);
        }
    }
}
