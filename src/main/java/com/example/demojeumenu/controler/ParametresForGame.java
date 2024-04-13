package com.example.demojeumenu.controler;

import com.example.demojeumenu.FXMLUtils;
import com.example.demojeumenu.Parametres;
import com.example.demojeumenu.SoundUtils;
import com.example.demojeumenu.utils.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import com.example.demojeumenu.GrilleControler;

@Controller
public class ParametresForGame extends Parametres {

    @FXML
    private Button continueButton;

    @FXML
    private Button newGameButton;

    private static Stage stage;

    @FXML
    private Label usernameLabel;
    public static void setStage(Stage st){
        stage = st;
    }

    @FXML
    private void backButton() {
        // Code pour fermer la fenêtre du popup
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    @Override
    public void showInfo() {
        super.showInfo();
    }

    @FXML
    public void initialize(){
        updateSoundBar();
        updateSoundButton();
        SoundUtils.addClickSound(continueButton, this::backButton);
        SoundUtils.addClickSound(newGameButton, this::backButton);
        usernameLabel.setText(GlobalVariables.getUserInput());
        newGameButton.setOnAction(event -> {
            FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, GrilleControler.loadedFile, false);
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
        });
    }
}