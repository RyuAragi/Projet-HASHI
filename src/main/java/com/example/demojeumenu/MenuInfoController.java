package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MenuInfoController extends BaseController{

    @FXML
    private Button bouton_retour;

    @FXML
    private void retour() {

    }

    @FXML
    public void initialize(){
        SoundUtils.addClickSound(bouton_retour, this::retour);
    }
}