package com.example.demojeumenu;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class SoundUtils {

    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);
    private static MediaPlayer mediaPlayer = null;
    private static int previousSoudLevel = 0;
    private static int soundLevel = 3;  //3 par défault
    private static final MediaPlayer menuMusic = new MediaPlayer(new Media(Objects.requireNonNull(SoundUtils.class.getResource("music/musicMenu.wav")).toExternalForm()));

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
        Media sound = new Media(Objects.requireNonNull(SoundUtils.class.getResource("sounds/hover.wav")).toExternalForm());
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
            Media sound = new Media(Objects.requireNonNull(SoundUtils.class.getResource("sounds/click.wav")).toExternalForm());
            MediaPlayer newMediaPlayer = new MediaPlayer(sound);
            newMediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
            newMediaPlayer.play();
            // Update the mediaPlayer reference only after the new sound started playing
            mediaPlayer = newMediaPlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playErrorSound() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Stop the previous sound
            }
            Media sound = new Media(Objects.requireNonNull(SoundUtils.class.getResource("sounds/erreur.wav")).toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getPreviousSoundLevel() {
        return previousSoudLevel;
    }

    protected static int getSoundLevel(){
        return soundLevel;
    }

    protected static void soundUp(){
        soundLevel++;
        updateVolume();
        if(previousSoudLevel!=-1){
            previousSoudLevel=-1;
        }
    }

    protected static void soundDown(){
        soundLevel--;
        updateVolume();
        if(soundLevel==0){
            previousSoudLevel=1;
        }
    }

    protected static void avoidSound(){
        previousSoudLevel = soundLevel;
        soundLevel = 0;
        updateVolume();
    }

    protected static void allowSound(){
        soundLevel = previousSoudLevel;
        previousSoudLevel = -1;
        updateVolume();
    }

    protected static void playMenuMusic(){
        menuMusic.play();
    }

    protected static void stopMusicMenu(){
        menuMusic.stop();
    }

    protected static void updateVolume(){
        menuMusic.setVolume(((double)soundLevel)*2/10);
    }

    protected static void initMenuMusic(){
        menuMusic.setCycleCount(Integer.MAX_VALUE);
        updateVolume();
        playMenuMusic();
    }
}