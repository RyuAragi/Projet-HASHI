package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class AutreMenuController2 extends BaseController{

    private Scene scene;

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void retour() {
        FXMLUtils.loadFXML("AutreMenu.fxml", scene);
    }

}