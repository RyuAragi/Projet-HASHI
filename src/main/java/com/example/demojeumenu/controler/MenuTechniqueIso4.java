package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * Controller du quatrième menu du menu technique iso.
 */
public class MenuTechniqueIso4 extends BaseController {


    /**
     * Bouton retour au menu précedent
     */
    @FXML
    public Button bouton_retour;

    /**
     * Bouton pour aller au menu suivant
     */
    @FXML
    public Button bouton_quitter;

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

    @FXML
    private void quitter() {
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
        FXMLUtils.goBack(scene);
    }

    /**
     * Méthode d'action du bouton retour. Permet d'acceder au menu suivant.
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
     * Initialisation du menu
     */
    @FXML
    private void initialize(){
        if(FXMLUtils.topHistory().equals("/GrilleDisplay.fxml")){
            bouton_quitter.setVisible(false);
            bouton_quitter.setDisable(true);
        }
    }

}
