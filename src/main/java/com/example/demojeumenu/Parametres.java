package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static com.example.demojeumenu.SoundUtils.*;

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
        } else {
            SoundUtils.playErrorSound();
        }
    }

    @FXML
    private void leftSoundButton(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();
            updateSoundBar();
        } else {
            SoundUtils.playErrorSound();
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
        if (SoundUtils.getSoundLevel() < tabImgSoundbar.size()) {
            String imageName = "images/" + tabImgSoundbar.get(SoundUtils.getSoundLevel());
            Image image = new Image(getClass().getResource(imageName).toExternalForm());
            soundBar.setImage(image);
        }
    }

    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
        updateSoundBar();
        SoundUtils.addHoverSound(info_button);
        SoundUtils.addHoverSound(back_button);
        SoundUtils.addHoverSound(mute_button);
        SoundUtils.addClickSound(soundless_button, this::leftSoundButton);
        SoundUtils.addClickSound(soundmore_button, this::rightSoundButton);
        SoundUtils.addClickSound(back_button, this::backButton);
        SoundUtils.addClickSound(mute_button, this::noSoundButton);
    }
}