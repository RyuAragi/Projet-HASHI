package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeController {

    @FXML
    private Button BoutonRetour;

    @FXML
    protected void onButton1Click() {
        System.out.println("Bouton 1 a été cliqué");
    }

    @FXML
    protected void onButton2Click() {
        System.out.println("Bouton 2 a été cliqué");
    }

    @FXML
    protected void onButton3Click() {
        System.out.println("Bouton 3 a été cliqué");
    }

    @FXML
    protected void onButton4Click() {
        Stage currentStage = (Stage) BoutonRetour.getScene().getWindow();
        WindowManager windowManager = new WindowManager();
        windowManager.showView(currentStage, "hello-view");
        System.out.println("Bouton 4 a été cliqué");
    }


}
