package com.example.demojeumenu;

import com.almasb.fxgl.core.collection.Array;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Parametres extends BaseController {
    private static ArrayList<String> tabImgSoundbar = new ArrayList<>();
    static{
        tabImgSoundbar.add("barre_volume_0.png");
        tabImgSoundbar.add("barre_volume_1.png");
        tabImgSoundbar.add("barre_volume_2.png");
        tabImgSoundbar.add("barre_volume_3.png");
        tabImgSoundbar.add("barre_volume_4.png");
        tabImgSoundbar.add("barre_volume_5.png");
    }

    @FXML
    public TextField usernameZone;


    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    @FXML
    private void back_button() {
        FXMLUtils.goBack(scene);
    }
    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
    }


    @FXML
    private void right_sound_button(){
        if(SoundUtils.getSoundLevel()<5){
            SoundUtils.soundUp();
            //incrémenter l'image du son
        }
    }

    @FXML
    private void left_sound_button(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();

            //décrémenter l'image du son
        }
    }

    @FXML
    private void no_sound_button(){
        if(SoundUtils.getSoundLevel()>0) {
            SoundUtils.avoidSound();

        }
    }

    @FXML
    private void sound_button(){
        if(SoundUtils.getSoundLevel()==0) {
            SoundUtils.allowSound();
        }
    }



}
