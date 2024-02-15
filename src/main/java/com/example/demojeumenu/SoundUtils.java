package com.example.demojeumenu;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SoundUtils {

    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);

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
}