package com.example.demojeumenu;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SoundUtils {

    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);

    private static int previousSoudLevel = -1;
    private static int soundLevel = 2;


    public static void addHoverSound(Button button) {
        button.setOnMouseEntered(event -> {
            if (!isPlaying.get()) {
                new Thread(SoundUtils::playHoverSound).start();
            }
        });
    }

    private static void playHoverSound() {
        isPlaying.set(true);
        Media sound = new Media(SoundUtils.class.getResource("sounds/hover.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
        mediaPlayer.play();
    }

    protected static int getSoundLevel(){
        return soundLevel;
    }

    protected static void soundUp(){
        soundLevel++;
        if(previousSoudLevel!=-1){
            previousSoudLevel=-1;
        }
    }

    protected static void soundDown(){
        soundLevel--;
    }

    protected static void avoidSound(){
        previousSoudLevel = soundLevel;
        soundLevel = 0;
    }

    protected static void allowSound(){
        soundLevel = previousSoudLevel;
        previousSoudLevel = -1;
    }
}