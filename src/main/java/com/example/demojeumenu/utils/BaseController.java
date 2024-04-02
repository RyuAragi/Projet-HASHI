package com.example.demojeumenu.utils;

import com.example.demojeumenu.FXMLUtils;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public abstract class BaseController {
    protected static Scene scene;
    private boolean EtatParametres = false; // variable pour savoir si on est dans le menu parametres
    protected String levelFileName;

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (EtatParametres) {
                    FXMLUtils.goBack(scene);
                    EtatParametres = false;
                } else if (!scene.getStylesheets().contains("Parametres.fxml")) {
                    FXMLUtils.loadFXML("Parametres.fxml", scene);
                    EtatParametres = true;
                }
            }
        });
    }
}