package com.example.demojeumenu;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe implémentant le réglage du son dans le jeu.
 * @author Thibault COURCOL, Théo DULUARD
 */
public class SoundUtils {

    /**
     * [AtomicBoolean] Booléen vérifiant si un son d'interaction est en cours
     */
    private static final AtomicBoolean isPlaying = new AtomicBoolean(false);

    /**
     * [MediaPlayer] Lecteur de son des interactions avec les boutons du jeu.
     */
    private static MediaPlayer interactions = null;

    /**
     * [Integer entre 0-5] Niveau précédent de son. Utilisé pour le bouton mute.
     */
    private static int previousSoudLevel = 1; //1 par défault

    /**
     * [Integer entre 0-5] Niveau courant du son.
     */
    private static int soundLevel = 0;  //0 par défault

    /**
     * [MediaPlayer] Lecteur des musiques du menu et du jeu.
     */
    private static MediaPlayer music;
    private static final CountDownLatch latch = new CountDownLatch(1);

    static {
        new Thread(() -> {
            music = new MediaPlayer(createMedia("/music/musicMenu.mp3"));
            latch.countDown(); // Signal that the music player is ready
        }).start();
    }

    /**
     * Méthode de création d'un média à partir d'un fichier audio.
     * @param resourceName [String] Nom du fichier audio.
     * @return [Media] Média créé à partir du fichier audio.
     */


    private static Media createMedia(String resourceName) {
        URL resourceUrl = SoundUtils.class.getResource(resourceName);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourceName);
        }

        if (resourceUrl.getProtocol().equals("jar")) {
            try {
                Path tempDir = Files.createTempDirectory("audio_resources");
                tempDir.toFile().deleteOnExit();
                Path tempFile = Files.createTempFile(tempDir, "sound", ".wav");
                tempFile.toFile().deleteOnExit();
                try (InputStream audioStream = SoundUtils.class.getResourceAsStream(resourceName)) {
                    Files.copy(audioStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
                return new Media(tempFile.toUri().toString());
            } catch (IOException e) {
                throw new RuntimeException("Failed to extract audio resource to temp file", e);
            }
        } else {
            return new Media(resourceUrl.toString());
        }
    }

    /**
     * Méthode d'ajout de son lorsque la souris passe sur le bouton passé en paramètre.
     * @param button [Button] Button sur lequel la souris passe.
     */
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

    /**
     * Méthode de lancement de l'effet sonore de passage de la souris sur un bouton.
     */
    private static void playHoverSound() {
        if (interactions != null) {
            interactions.stop(); // Stop the previous sound
        }
        Media sound = createMedia("/sounds/hover.wav");
        interactions = new MediaPlayer(sound);
        interactions.setOnEndOfMedia(() -> isPlaying.set(false));
        isPlaying.set(true);
        updateVolume();
        interactions.play();
    }

    /**
     * Méthode d'arrêt de l'effet sonore de passage de la souris sur un bouton.
     */
    private static void stopHoverSound() {
        if (interactions != null) {
            interactions.stop();
            isPlaying.set(false);
        }
    }

    /**
     * Méthode d'ajout d'un effet sonore lorsque le joueur clique sur le bouton passé en paramètre.
     * @param button [Button] Bouton sur lequelle le joueur clique.
     * @param additionalAction [Runnable] Action à réaliser après le clic.
     */
    public static void addClickSound(Button button, Runnable additionalAction) {
        button.setOnAction(event -> {
            if (!isPlaying.get()) {
                playClickSound();
            }
            additionalAction.run();
        });
    }

    /**
     * Méthode de lancement de l'effet sonore lors du clic sur le bouton.
     */
    private static void playClickSound() {
        try {
            Media sound = createMedia("/sounds/click.wav");
            MediaPlayer newMediaPlayer = new MediaPlayer(sound);
            newMediaPlayer.setOnEndOfMedia(() -> isPlaying.set(false));
            updateVolume();
            newMediaPlayer.play();
            // Update the mediaPlayer reference only after the new sound started playing
            interactions = newMediaPlayer;
        } catch (Exception e) {
            System.out.println("Erreur de chargement du son click : " + e);
        }
    }

    /**
     * Méthode de lancement de l'effet sonore d'erreur. Cette méthode est utilisé lorsque le joueur veut baisser le son alors qu'il est au minimum ou lorsqu'il souhaite l'augmenter alors qu'il est au maximum.
     */
    public static void playErrorSound() {
        try {
            if (interactions != null) {
                interactions.stop(); // Stop the previous sound
            }
            Media sound = createMedia("/sounds/erreur.wav");
            interactions = new MediaPlayer(sound);
            interactions.setOnEndOfMedia(() -> isPlaying.set(false));
            interactions.play();
        } catch (Exception e) {
            System.out.println("Erreur de chargement du son erreur : " + e);
        }
    }

    /**
     * Méthode retournant le précédent niveau du son.
     * @return [Integer entre 0 et 5] Le précédent niveau du son enregistré.
     */
    public static int getPreviousSoundLevel() {
        return previousSoudLevel;
    }

    /**
     * Méthode retournant le niveau du son courant.
     * @return [Integer entre 0 et 5] Le niveau du son courant.
     */
    public static int getSoundLevel(){
        return soundLevel;
    }

    /**
     * Méthode augmentant le niveau du son.
     */
    public static void soundUp(){
        soundLevel++;
        updateVolume();
        if(previousSoudLevel!=-1){
            previousSoudLevel=-1;
        }
    }

    /**
     * Méthode baissant le niveau du son.
     */
    public static void soundDown(){
        soundLevel--;
        updateVolume();
        if(soundLevel==0){
            previousSoudLevel=1;
        }
    }

    /**
     * Méthode mettant le niveau du son à 0.
     */
    public static void avoidSound(){
        previousSoudLevel = soundLevel;
        soundLevel = 0;
        updateVolume();
    }

    /**
     * Méthode rétablissant le son à partir du dernier niveau de son enregistré.
     */
    public static void allowSound(){
        soundLevel = previousSoudLevel;
        previousSoudLevel = -1;
        updateVolume();
    }

    /**
     * Méthode de lancement de la musique.
     */
    protected static void playMusic(){
        music.play();
    }

    /**
     * Méthode d'arrêt de la musique.
     */
    protected static void stopMusic(){
        music.stop();
    }

    /**
     * Méthode permettant de changer la musique à partir d'un fichier.
     * @param filepath [String] Chemin du fichier audio (.wav de préférence)
     */
    protected static void setMusic(String filepath){
        music = new MediaPlayer(createMedia(filepath));
        initMusic();
    }

    /**
     * Méthode de mise à jour du volume du son de la musique et des interactions avec les boutons.
     */
    protected static void updateVolume(){
        if(interactions!=null) interactions.setVolume(((double)soundLevel)*2/10);
        if(music!=null) music.setVolume(((double)soundLevel)*2/10);
    }

    /**
     * Méthode d'initialisation de la musique.
     */
    public static void initMusic() {
        try {
            latch.await(); // Wait for the music player to be ready
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle exception
        }
        music.setCycleCount(Integer.MAX_VALUE);
        updateVolume();
        playMusic();
    }
}