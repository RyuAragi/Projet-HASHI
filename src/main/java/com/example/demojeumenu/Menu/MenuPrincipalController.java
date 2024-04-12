package com.example.demojeumenu.Menu;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.JsonApp;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.controler.GlobalVariables;
import com.example.demojeumenu.utils.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Controller;


/**
 * Classe implémentant les action du menu principal.
 * @author Thibault COURCOL, Théo DULUARD
 */

@Controller
public class MenuPrincipalController extends BaseController {

    /**
     * [Button] Bouton d'accès au menu des différents modes de jeux.
     */
    @FXML
    private Button jouer;

    /**
     * [Button] Bouton d'accès au menu des didacticiels.
     */
    @FXML
    private Button didacticiel;

    /**
     * [TextField] Zone de texte où le joueur peut saisir son nom d'utilisateur.
     */
    @FXML
    public TextField zoneTexte;

    /**
     * [Button] Bouton pour quitter l'application.
     */
    @FXML
    private Button quitter;

    /**
     * Méthode d'execution des actions en fonction du bouton cliqué.
     * @param event [ActionEvent] Evenement récupéré pour connaitre l'identifiant du bouton cliqué.
     */
    @FXML
    private void buttonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        switch (buttonId) {
            case "jouer":
                jouer();
                break;
            case "didacticiel":
                didacticiel();
                break;
            case "quitter":
                quitter();
                break;
            default:
                break;
        }
    }

    /**
     * Méthode permettant de charger le menu des modes de jeu.
     */
    private void jouer() {
        FXMLUtils.loadFXML("/MenuModeDuJeu.fxml", scene);
    }

    /**
     * Méthode permettant de charger le menu des didacticiels.
     */

    private void didacticiel() {
        FXMLUtils.loadFXML("/MenuReglesDuJeu.fxml", scene);
    }

    /**
     * Méthode permettant de quitter l'application.
     */


    private void quitter() {
        System.exit(0);
    }

    /**
     * Méthode de focus sur la fenêtre lors d'un clic.
     * @param event [MouseEvent] Evenement de la souris.
     */
    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    /**
     * Méthode d'initialisation des actions des boutons et zone de texte du menu.
     */

    @FXML
    public void initialize() {
        SoundUtils.initMusic();
        SoundUtils.addHoverSound(jouer);
        SoundUtils.addHoverSound(didacticiel);
        SoundUtils.addHoverSound(quitter);
        FXMLUtils.initializeTextField(zoneTexte);

        // Ajout de l'écouteur sur la propriété text de la zone de texte
        zoneTexte.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie si le nouveau texte est vide ou égal au texte par défaut
            // Désactive le bouton jouer
            // Active le bouton jouer
            jouer.setDisable(newValue.isEmpty() || newValue.equals(GlobalVariables.getDefaultUserInput()));

            JsonApp.removeShownPopup(oldValue);
        });

        // Désactive le bouton jouer initialement si le texte de la zone de texte est vide ou égal au texte par défaut
        if (zoneTexte.getText().isEmpty() || zoneTexte.getText().equals(GlobalVariables.getDefaultUserInput())) {
            jouer.setDisable(true);
        }
    }

}