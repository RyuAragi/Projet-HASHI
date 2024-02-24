package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe implémentant les interaction avec le menu d'information concernant le projet.
 * @author Thibault COURCOL, Théo DULUARD
 */
public class MenuInfoController extends BaseController{

    /**
     * [Stage] Sous-fenêtre des informations du projet.
     */
    private static Stage stage;

    /**
     * [Button] Bouton de retour au menu des paramètres.
     */
    @FXML
    private Button bouton_retour;


    /**
     * Méthode d'affectation de la sous-fenêtre.
     * @param st [Stage] Sous-fenêtre des informations du projet.
     */
    public static void setStage(Stage st){
        stage = st;
    }

    /**
     * Méthode d'action du bouton retour. Permet de retourner au menu des paramètres.
     */
    @FXML
    private void retour() {
        stage.close();
    }

    /**
     * Méthode d'initilisation de l'action du bouton retour.
     */
    @FXML
    public void initialize(){
        SoundUtils.addClickSound(bouton_retour, this::retour);
    }
}