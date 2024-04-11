package com.example.demojeumenu.utils;

import com.example.demojeumenu.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public abstract class BaseController {
    protected static Scene scene;
    protected static boolean isJouerButtonEnabled = false; // Change this to false
    private boolean EtatParametres = false; // variable pour savoir si on est dans le menu parametres
    protected String levelFileName;
    public void setScene(Scene scene) {
        BaseController.scene = scene;
        BaseController.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (EtatParametres) {
                    FXMLUtils.goBack(scene);
                    EtatParametres = false;
                } else if (!scene.getStylesheets().contains("/Parametres.fxml")) {
                    FXMLUtils.loadFXML("/Parametres.fxml", scene);
                    EtatParametres = true;
                }
            }
        });
    }

    /**
     * Méthode permettant d'acceder aux parametres.
     */

    @FXML
    private void param() {
        FXMLUtils.loadFXML("/Parametres.fxml", scene);
    }
}