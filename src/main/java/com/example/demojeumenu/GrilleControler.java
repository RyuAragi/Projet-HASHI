package com.example.demojeumenu;

import com.example.demojeumenu.jeu.GrilleJeu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GrilleControler extends BaseController {

    private GrilleJeu grille;

    @FXML
    private Button quit;

    @FXML
    private Button zoom;

    @FXML
    private Button dezoom;

    @FXML
    private Button undo;

    @FXML
    private Button redo;

    @FXML
    private Button check;

    @FXML
    private Button help;

    @FXML
    private GridPane grillePane;


    @FXML
    public void initialize(){
        /*
            Changer la musique...
         */
        SoundUtils.addHoverSound(quit);
        SoundUtils.addHoverSound(zoom);
        SoundUtils.addHoverSound(dezoom);
        SoundUtils.addHoverSound(undo);
        SoundUtils.addHoverSound(redo);
        SoundUtils.addHoverSound(check);
        SoundUtils.addHoverSound(help);

        this.grille = new GrilleJeu("/niveaux/facile/Facile-1.txt");
        System.out.print(this.grille.getNbColonne() + " - " + this.grille.getNbLigne());
        grillePane.addColumn(this.grille.getNbColonne());
        grillePane.addRow(this.grille.getNbLigne());
    }
}
