package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class AutreMenuController extends BaseController {

    private Scene scene;

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void btn1() {
        try {
            FXMLLoader menuPrincipalLoader = new FXMLLoader(getClass().getResource("AutreMenu2.fxml"));
            Parent menuPrincipal = menuPrincipalLoader.load();
            AutreMenuController2 autreMenuController2 = menuPrincipalLoader.getController();
            autreMenuController2.setScene(scene);
            scene.setRoot(menuPrincipal);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.error("Une erreur est survenue lors du chargement du fichier FXML", e);
        }
    }


    @FXML
    private void retour() {
        FXMLUtils.loadFXML("MenuPrincipal.fxml", scene);
    }

}