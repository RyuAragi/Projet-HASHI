package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuInfoController extends BaseController{
    private static Stage stage;

    public static void setStage(Stage st){
        stage = st;
    }

    @FXML
    private Button bouton_retour;

    @FXML
    private void retour() {
        stage.close();
    }

    @FXML
    public void initialize(){
        SoundUtils.addClickSound(bouton_retour, this::retour);
    }
}