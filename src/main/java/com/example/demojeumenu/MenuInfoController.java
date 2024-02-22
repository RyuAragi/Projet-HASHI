package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class MenuInfoController extends BaseController{

    private Stage pageInfo;

    @FXML
    private Button bouton_retour;

    public MenuInfoController(Window window){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInfo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene sceneInfo = new Scene(root);
        sceneInfo.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());

        pageInfo = new Stage();
        pageInfo.setAlwaysOnTop(true);
        pageInfo.initStyle(StageStyle.UNDECORATED);
        pageInfo.initOwner(window);
        pageInfo.setHeight(500);
        pageInfo.setWidth(500);
        pageInfo.setScene(sceneInfo);
        pageInfo.show();
    }

    @FXML
    private void retour() {
        FXMLUtils.goBack(scene);
    }
}