package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Parametres extends BaseController {
    @FXML
    public TextField usernameZone;

    @FXML
    public ImageView soundBar;

    @FXML
    private Button info_button;

    @FXML
    private Button back_button;

    @FXML
    private Button soundless_button;

    @FXML
    private Button soundmore_button;

    @FXML
    private Button mute_button;

    private static final ArrayList<String> tabImgSoundbar = new ArrayList<>();
    static{
        tabImgSoundbar.add("barre_volume_0.png");
        tabImgSoundbar.add("barre_volume_1.png");
        tabImgSoundbar.add("barre_volume_2.png");
        tabImgSoundbar.add("barre_volume_3.png");
        tabImgSoundbar.add("barre_volume_4.png");
        tabImgSoundbar.add("barre_volume_5.png");
    }

    private static final ArrayList<String> tabImgSoundButton = new ArrayList<>();
    static{
        tabImgSoundButton.add("bouton_mute.png");
        tabImgSoundButton.add("bouton_volume.png");
    }

    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    @FXML
    private void backButton() {
        FXMLUtils.goBack(scene);
    }

    @FXML
    private void rightSoundButton(){
        if(SoundUtils.getSoundLevel()<5){
            SoundUtils.soundUp();
            updateSoundBar();
            updateSoundButton();
        } else {
            SoundUtils.playErrorSound();
        }
    }

    @FXML
    private void leftSoundButton(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();
            updateSoundBar();
            updateSoundButton();
        } else {
            SoundUtils.playErrorSound();
        }
    }

    @FXML
    private void soundButton(){
        if(SoundUtils.getPreviousSoundLevel() > 0) {
            SoundUtils.allowSound();
        } else {
            SoundUtils.avoidSound();
        }
        updateSoundBar();
        updateSoundButton();
    }

    private void updateSoundBar() {
        if (SoundUtils.getSoundLevel()>=0 && SoundUtils.getSoundLevel() < tabImgSoundbar.size()) {
            String imageName = "images/" + tabImgSoundbar.get(SoundUtils.getSoundLevel());
            Image image = new Image(Objects.requireNonNull(getClass().getResource(imageName)).toExternalForm());
            soundBar.setImage(image);
        }
    }

    private void updateSoundButton(){
        String imgName;
        if(SoundUtils.getSoundLevel() == 0){
            imgName = "images/" + tabImgSoundButton.get(1); // volume image -> pour remettre le son
        }
        else{
            imgName = "images/" + tabImgSoundButton.get(0); // mute image   -> pour supprimer le son
        }
        String imageUrl = Objects.requireNonNull(getClass().getResource(imgName)).toExternalForm();
        mute_button.setStyle("-fx-background-image: url('" + imageUrl + "');");
    }

    @FXML
    private void showInfo() {
        // Charger le fichier FXML de l'external frame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menuInfo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Créer la scène de l'external frame
        Scene sceneInfo = new Scene(root);

        // Créer une nouvelle fenêtre pour l'external frame
        Stage externalFrame = new Stage();
        externalFrame.initStyle(StageStyle.UNDECORATED);
        externalFrame.initOwner(scene.getWindow());
        externalFrame.getOwner().setOpacity(0.5);
        FXMLUtils.addHistory("menuInfo.fxml");
        externalFrame.setScene(sceneInfo);
        externalFrame.show();
    }

    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
        updateSoundBar();
        updateSoundButton();
        SoundUtils.addHoverSound(info_button);
        SoundUtils.addHoverSound(back_button);
        SoundUtils.addHoverSound(mute_button);
        SoundUtils.addClickSound(soundless_button, this::leftSoundButton);
        SoundUtils.addClickSound(soundmore_button, this::rightSoundButton);
        SoundUtils.addClickSound(back_button, this::backButton);
        SoundUtils.addClickSound(mute_button, this::soundButton);
        SoundUtils.addClickSound(info_button, this::showInfo);
    }
}