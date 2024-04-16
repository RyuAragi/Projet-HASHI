package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Controller du popup window.
 * plus utilisé deprecated
 */
@Controller
public class PopupWindowControllerLBGrille extends BaseController {

    @FXML
    private Button continueButton;// Bouton pour continuer.
    /**
     *  Recupère la fenêtre de l'application.
     */
    private static Stage stage;
    // Méthode pour définir la fenêtre de l'application.

    /**
     * Recupère la fenêtre de l'application.
     * @param st [Stage] La fenêtre de l'application.
     */
    public static void setStage(Stage st){
        stage = st;
    }
    /**
     * Méthode d'action du menu principal.
     */
    @FXML
    private void btnHome() {
        stage.close();
        FXMLUtils.loadFXML("/MenuPrincipal.fxml", scene);
    }
    // Méthode pour gérer le clic sur le bouton de retour.
    @FXML
    private void backButton() {
        // Code pour fermer la fenêtre du popup
        //Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    // Méthode appelée lors de l'initialisation du contrôleur.
    @FXML
    public void initialize(){

        SoundUtils.addClickSound(continueButton, this::backButton);
    }
}