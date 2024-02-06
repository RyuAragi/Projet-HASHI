package com.example.demojeumenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class FXMLUtils {

    public static void loadFXML(String fxmlFileName, Scene scene) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlFileName));
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setScene(scene);
            scene.setRoot(root);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(FXMLUtils.class);
            logger.error("Une erreur est survenue lors du chargement du fichier FXML", e);
        }
    }
}