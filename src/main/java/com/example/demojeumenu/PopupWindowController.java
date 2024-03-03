package com.example.demojeumenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupWindowController extends BaseController {

    @FXML
    private Button continueButton;

    @FXML
    private Button newGameButton;
    private StringProperty username = new SimpleStringProperty();

    private static Stage stage;
    @FXML
    private Label usernameLabel;
    public static void setStage(Stage st){
        stage = st;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    @FXML
    private void backButton() {
        stage.close();
    }

    @FXML
    public void initialize(){
        usernameLabel.textProperty().bind(username);
        SoundUtils.addClickSound(continueButton, this::backButton);
        SoundUtils.addClickSound(newGameButton, this::backButton);
    }
}