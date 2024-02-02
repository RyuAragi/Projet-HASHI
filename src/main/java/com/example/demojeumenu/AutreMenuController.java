package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.error("Une erreur est survenue lors du chargement du fichier FXML", e);
        }
    }

}