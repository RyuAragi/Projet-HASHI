package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class AutreMenuController {

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void retour() {
        try {
            FXMLLoader menuPrincipalLoader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
            Parent menuPrincipal = menuPrincipalLoader.load();
            MenuPrincipalController menuPrincipalController = menuPrincipalLoader.getController();
            menuPrincipalController.setScene(scene);
            scene.setRoot(menuPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}