package com.example.demojeumenu;

import com.example.demojeumenu.Menu.MenuInfoController;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe implémentant le contrôle et les évènements du menu des paramètres.
 * @author Thibault COURCOL, Théo DULUARD
 */

@Controller
public class Parametres extends BaseController {
    /**
     * [Textfield] Zone de texte en entrée où le joueur saisi son pseudo.
     */
    @FXML
    public TextField usernameZone;

    /**
     * [Imageview] Barre affichant le niveau du son.
     */
    @FXML
    public ImageView soundBar;

    /**
     * [Button] Button d'accès de l'interface des informations du projet.
     */
    @FXML
    private Button info_button;

    /**
     * [Button] Button de retour à la page précédente.
     */
    @FXML
    private Button back_button;

    /**
     * Bouton de réduction du son.
     */
    @FXML
    private Button soundless_button;

    /**
     * Bouton d'augmentation du son.
     */
    @FXML
    private Button soundmore_button;

    /**
     * Bouton de suppression/rétablissement du son.
     */
    @FXML
    private Button mute_button;

    /**
     * [ArrayList<String>] Tableau static des noms d'image de la barre de son.
     */
    private static final ArrayList<String> tabImgSoundbar = new ArrayList<>();
    static{
        tabImgSoundbar.add("barre_volume_0.png");
        tabImgSoundbar.add("barre_volume_1.png");
        tabImgSoundbar.add("barre_volume_2.png");
        tabImgSoundbar.add("barre_volume_3.png");
        tabImgSoundbar.add("barre_volume_4.png");
        tabImgSoundbar.add("barre_volume_5.png");
    }

    /**
     * [ArrayList<String>] Tableau static des noms d'images du bouton mute.
     */
    private static final ArrayList<String> tabImgSoundButton = new ArrayList<>();
    static{
        tabImgSoundButton.add("bouton_mute.png");
        tabImgSoundButton.add("bouton_volume.png");
    }

    /**
     * Méthode de focus sur la fenêtre lors d'un clic.
     * @param event [MouseEvent] Evenement de la souris.
     */
    @FXML
    private void background(MouseEvent event) {
        //detecte le clic sur le bouton background
        ((Node) event.getSource()).requestFocus();
    }

    /**
     * Méthode permettant de revenir à la page précédente avant l'entrée dans le menu des paramètres.
     */
    @FXML
    private void backButton() {
        FXMLUtils.goBack(scene);
    }

    /**
     * Méthode d'action du bouton d'augmentation du volume sonore.
     */
    @FXML
    private void rightSoundButton(){
        if(SoundUtils.getSoundLevel()<5){
            SoundUtils.soundUp();
            updateSoundBar();
            updateSoundButton();
        } else {
            SoundUtils.playErrorSound();
        }
    }

    /**
     * Méthode d'action du bouton de diminution du volume sonore.
     */
    @FXML
    private void leftSoundButton(){
        if(SoundUtils.getSoundLevel()>0){
            SoundUtils.soundDown();
            updateSoundBar();
            updateSoundButton();
        } else {
            SoundUtils.playErrorSound();
        }
    }

    /**
     * Méthode d'action du bouton mute.
     */
    @FXML
    private void soundButton(){
        if(SoundUtils.getPreviousSoundLevel() > 0) {
            SoundUtils.allowSound();
        } else {
            SoundUtils.avoidSound();
        }
        updateSoundBar();
        updateSoundButton();
    }

    /**
     * Méthode de mise à jour du son lorsque l'un des boutons agissant sur le son est cliqué.
     */
    protected void updateSoundBar() {
        if (SoundUtils.getSoundLevel()>=0 && SoundUtils.getSoundLevel() < tabImgSoundbar.size()) {
            String imageName = "/images/" + tabImgSoundbar.get(SoundUtils.getSoundLevel());
            Image image = new Image(Objects.requireNonNull(getClass().getResource(imageName)).toExternalForm());
            soundBar.setImage(image);
        }
    }

    /**
     * Méthode de mise à jour de l'image du bouton mute.
     */
    protected void updateSoundButton(){
        String imgName;
        if(SoundUtils.getSoundLevel() == 0){
            imgName = "/images/" + tabImgSoundButton.get(1); // volume image -> pour remettre le son
        }
        else{
            imgName = "/images/" + tabImgSoundButton.get(0); // mute image   -> pour supprimer le son
        }
        String imageUrl = Objects.requireNonNull(getClass().getResource(imgName)).toExternalForm();
        mute_button.setStyle("-fx-background-image: url('" + imageUrl + "');");
    }

    /**
     * Méthode d'affichage de la fenêtre d'informations du projet.
     */
    @FXML
    protected void showInfo() {
        ColorAdjust darkColorAdjust = new ColorAdjust();
        darkColorAdjust.setBrightness(-0.5);
        scene.getRoot().setEffect(darkColorAdjust);
        // Charger le fichier FXML de l'external frame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuInfo.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Créer la scène de l'external frame
        Scene sceneInfo = new Scene(root);
        sceneInfo.setFill(Color.TRANSPARENT);
        sceneInfo.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        sceneInfo.getRoot().setEffect(new DropShadow());

        // Créer une nouvelle fenêtre pour l'external frame
        Stage externalFrame = new Stage();
        externalFrame.setResizable(false);
        externalFrame.setWidth(800);
        externalFrame.setHeight(700);
        externalFrame.setAlwaysOnTop(true);
        externalFrame.initStyle(StageStyle.TRANSPARENT);
        externalFrame.initOwner(scene.getWindow());
        MenuInfoController.setStage(externalFrame);

        externalFrame.setOnHidden(event -> scene.getRoot().setEffect(null));

        externalFrame.setScene(sceneInfo);
        externalFrame.show();
    }

    /**
     * Méthode d'initialisation des actions des éléments graphiques du menu des paramètres.
     */
    @FXML
    public void initialize() {
        FXMLUtils.initializeTextField(usernameZone);
        updateSoundBar();
        updateSoundButton();
        SoundUtils.addHoverSound(info_button);
        SoundUtils.addHoverSound(back_button);
        SoundUtils.addHoverSound(mute_button);
        SoundUtils.addClickSound(soundless_button, this::leftSoundButton);
        SoundUtils.addClickSound(soundmore_button, this::rightSoundButton);
        SoundUtils.addClickSound(back_button, this::backButton);
        SoundUtils.addClickSound(mute_button, this::soundButton);
        SoundUtils.addClickSound(info_button, this::showInfo);
    }
}