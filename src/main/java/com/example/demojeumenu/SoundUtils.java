package com.example.demojeumenu;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundUtils {

<<<<<<< HEAD
=======
    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);

<<<<<<< HEAD
>>>>>>> parent of 1fbda11 (Théo (#15))
=======
    private static int previousSoudLevel = -1;
    private static int soundLevel = 2;


>>>>>>> 1fbda1151bf57c5972b5dee1420483aa2396b88e
    public static void addHoverSound(Button button) {
        button.setOnMouseEntered(event -> playHoverSound());
    }

    private static void playHoverSound() {
        Media sound = new Media(SoundUtils.class.getResource("sounds/hover.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
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