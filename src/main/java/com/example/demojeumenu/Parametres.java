package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

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
        } else {
            SoundUtils.playErrorSound();
        }
    }

    @FXML
    private void leftSoundButton(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();
            updateSoundBar();
        }
        else {
            SoundUtils.playErrorSound();
        }
    }

    @FXML
    private void soundButton(){
        if(SoundUtils.getSoundLevel()==0) {
            SoundUtils.allowSound();
            updateSoundBar();
            String imageName = "images/" + tabImgSoundButton.get(1);
            System.out.println(imageName);
            Image image = new Image(getClass().getResource(imageName).toExternalForm());
            Background bg = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, null, BackgroundPosition.CENTER, new BackgroundSize(100,100,true,true,false, false)));
            mute_button.setBackground(bg);
            mute_button.requestLayout();
        }
        else if(SoundUtils.getSoundLevel()>0) {
            SoundUtils.avoidSound();
            updateSoundBar();
            String imageName = "images/" + tabImgSoundButton.get(0);
            System.out.println(imageName);
            Image image = new Image(getClass().getResource(imageName).toExternalForm());
            Background bg = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, null, BackgroundPosition.CENTER, new BackgroundSize(100,100,true,true,false, false)));
            mute_button.setBackground(bg);
            mute_button.requestFocus();
        }
    }

    private void updateSoundBar() {
        if (SoundUtils.getSoundLevel() >= 0 && SoundUtils.getSoundLevel() < tabImgSoundbar.size()) {
            String imageName = "images/" + tabImgSoundbar.get(SoundUtils.getSoundLevel());
            Image image = new Image(Objects.requireNonNull(getClass().getResource(imageName)).toExternalForm());
            soundBar.setImage(image);
        }
    }

    private void initSoundButton(){
        String imgName;
        if(SoundUtils.getSoundLevel() == 0){
            imgName = "images/" + tabImgSoundButton.get(1);
        }
        else{
             imgName = "images/" + tabImgSoundButton.get(0);
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgName)));
        Background bg = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, null, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)));
        mute_button.setBackground();
    }

    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
        updateSoundBar();
        initSoundButton();
        SoundUtils.addHoverSound(info_button);
        SoundUtils.addHoverSound(back_button);
        SoundUtils.addHoverSound(mute_button);
        SoundUtils.addClickSound(soundless_button, this::leftSoundButton);
        SoundUtils.addClickSound(soundmore_button, this::rightSoundButton);
        SoundUtils.addClickSound(back_button, this::backButton);
        SoundUtils.addClickSound(mute_button, this::soundButton);
    }
}