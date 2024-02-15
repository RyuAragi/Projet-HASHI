package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.logging.Logger;


public class MenuPrincipalController extends BaseController{
    private static final Logger LOGGER = Logger.getLogger(MenuPrincipalController.class.getName());

    private static final int MAX_CHARS = 20;

    @FXML
    private Button jouer;

    @FXML
    private Button didacticiel;

    @FXML
    public TextField zoneTexte;

    @FXML
    private Button quitter;

    @FXML
    private void afficherAutreMenu() {
        FXMLUtils.loadFXML("AutreMenu.fxml", scene);
    }

    @FXML
    private void btn1() {
        FXMLUtils.loadFXML("AutreMenu2.fxml", scene);
    }

    @FXML
    private Button quitter() {
        LOGGER.info("Bouton QUITTER a été cliqué");
        System.exit(0);
        return quitter;
    }

    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();}
    @FXML
    public void initialize() {
        SoundUtils.addHoverSound(jouer);
        SoundUtils.addHoverSound(quitter);
        SoundUtils.addHoverSound(didacticiel);
        FXMLUtils.initializeTextField(zoneTexte);
    }

}