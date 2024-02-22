package com.example.demojeumenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MenuPrincipalController extends BaseController {

    @FXML
    private Button jouer;

    @FXML
    private Button didacticiel;

    @FXML
    public TextField zoneTexte;

    @FXML
    private Button quitter;

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

    private void jouer() {
        FXMLUtils.loadFXML("AutreMenu.fxml", scene);
    }

    private void didacticiel() {
        FXMLUtils.loadFXML("AutreMenu2.fxml", scene);
    }

    private void quitter() {
        System.exit(0);
    }

    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    @FXML
    public void initialize() {
        SoundUtils.initMenuMusic();
        SoundUtils.addHoverSound(jouer);
        SoundUtils.addHoverSound(didacticiel);
        SoundUtils.addHoverSound(quitter);
        FXMLUtils.initializeTextField(zoneTexte);
    }
}