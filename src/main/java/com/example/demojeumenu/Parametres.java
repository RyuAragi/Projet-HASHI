package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Parametres extends BaseController {
    @FXML
    public TextField usernameZone;

    @FXML
    public ImageView soundBar;

    private static final ArrayList<String> tabImgSoundbar = new ArrayList<>();
    static{
        tabImgSoundbar.add("barre_volume_0.png");
        tabImgSoundbar.add("barre_volume_1.png");
        tabImgSoundbar.add("barre_volume_2.png");
        tabImgSoundbar.add("barre_volume_3.png");
        tabImgSoundbar.add("barre_volume_4.png");
        tabImgSoundbar.add("barre_volume_5.png");
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
        }
    }

    @FXML
    private void leftSoundButton(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();
            updateSoundBar();
        }
    }

    @FXML
    private void noSoundButton(){
        if(SoundUtils.getSoundLevel()>0) {
            SoundUtils.avoidSound();
            updateSoundBar();
        }
    }

    @FXML
    private void soundButton(){
        if(SoundUtils.getSoundLevel()==0) {
            SoundUtils.allowSound();
            updateSoundBar();
        }
    }

    private void updateSoundBar() {
        String imageName = "images/barre_volume_" + SoundUtils.getSoundLevel() + ".png";
        Image image = new Image(getClass().getResource(imageName).toExternalForm());
        soundBar.setImage(image);
    }

    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
        updateSoundBar();
    }
}