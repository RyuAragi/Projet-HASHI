package com.example.demojeumenu;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SoundUtils {

    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private static MediaPlayer mediaPlayer = null;
    private static int previousSoudLevel = 0;
    private static int soundLevel = 2;
    private static PauseTransition hoverSoundDelay;

    public static void addHoverSound(Button button) {
        button.setOnMouseEntered(event -> {
            if (!isPlaying.get()) {
                playHoverSound();
            }
        });

        button.setOnMouseExited(event -> {
            if (isPlaying.get()) {
                stopHoverSound();
            }
        });
    }

    private static void playHoverSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Stop the previous sound
        }
        Media sound = new Media(SoundUtils.class.getResource("sounds/test-hover.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
        isPlaying.set(true);
        mediaPlayer.play();
    }

    private static void stopHoverSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying.set(false);
        }
    }

    public static void addClickSound(Button button, Runnable additionalAction) {
        button.setOnAction(event -> {
            if (!isPlaying.get()) {
                playClickSound();
            }
            additionalAction.run();
        });
    }

    private static void playClickSound() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Stop the previous sound
            }
            Media sound = new Media(SoundUtils.class.getResource("sounds/click.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playErrorSound() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Stop the previous sound
            }
            Media sound = new Media(SoundUtils.class.getResource("sounds/error.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private SoundUtils() {
    }

}