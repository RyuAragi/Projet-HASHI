package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
//je pense que c'est à supprimer vue qu'on a fait qu'un leaderboard
@Controller
public class PopupWindowControllerLBGrille extends BaseController {

    @FXML
    private Button continueButton;// Bouton pour continuer.
    private static Stage stage;
    // Méthode pour définir la fenêtre de l'application.

    public static void setStage(Stage st){
        stage = st;
    }
    // Méthode pour revenir à l'écran d'accueil.

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