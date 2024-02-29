package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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

    private void zoomMethod(){
        if(grillePane.getHeight()<0.9*scene.getHeight() && grillePane.getWidth()<0.8*scene.getWidth()) {
            grillePane.setPrefWidth(grillePane.getPrefWidth() + 10 * grille.getNbColonne());
            grillePane.setPrefHeight(grillePane.getPrefHeight() + 10 * grille.getNbLigne());
            grillePane.resize(grillePane.getPrefWidth(), grillePane.getPrefHeight());

            for (int i = 0; i < grillePane.getColumnConstraints().size(); i++) {
                ColumnConstraints column = grillePane.getColumnConstraints().get(i);
                column.setPrefWidth(column.getPrefWidth() + 10); // Augmenter la largeur de la colonne
            }
            for (int i = 0; i < grillePane.getRowConstraints().size(); i++) {
                RowConstraints row = grillePane.getRowConstraints().get(i);
                row.setPrefHeight(row.getPrefHeight() + 10); // Augmenter la hauteur de la ligne
            }
        }
    }

    private void dezoomMethod(){
        if(grillePane.getHeight()>0.1*scene.getHeight() && grillePane.getWidth()>0.15*scene.getWidth()) {
            grillePane.setPrefWidth(grillePane.getPrefWidth() - 10 * grille.getNbColonne());
            grillePane.setPrefHeight(grillePane.getPrefHeight() - 10 * grille.getNbLigne());
            grillePane.resize(grillePane.getPrefWidth(), grillePane.getPrefHeight());

            for (int i = 0; i < grillePane.getColumnConstraints().size(); i++) {
                ColumnConstraints column = grillePane.getColumnConstraints().get(i);
                column.setPrefWidth(column.getPrefWidth() - 10); // Augmenter la largeur de la colonne
            }
            for (int i = 0; i < grillePane.getRowConstraints().size(); i++) {
                RowConstraints row = grillePane.getRowConstraints().get(i);
                row.setPrefHeight(row.getPrefHeight() - 10); // Augmenter la hauteur de la ligne
            }
        }
    }


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

        SoundUtils.addClickSound(zoom, this::zoomMethod);
        SoundUtils.addClickSound(dezoom, this::dezoomMethod);

        this.grille = new GrilleJeu("./src/main/java/com/example/demojeumenu/niveaux/facile/Facile-1.txt");
        System.out.print(this.grille.getNbColonne() + " - " + this.grille.getNbLigne());

        int pixelSize=0;
        if(this.grille.getNbColonne()<10 && this.grille.getNbLigne()<10){
            pixelSize = 50;
        }
        else{
            pixelSize = 25;
        }
        /*
            A compléter
         */

        for (int i = 0; i < this.grille.getNbLigne(); i++) {
            RowConstraints row = new RowConstraints(pixelSize);
            grillePane.getRowConstraints().add(row);
        }
        for (int i = 0; i < this.grille.getNbColonne(); i++) {
            ColumnConstraints column = new ColumnConstraints(pixelSize);
            grillePane.getColumnConstraints().add(column);
        }
        grillePane.setAlignment(Pos.CENTER);
        grillePane.setPrefHeight(pixelSize*this.grille.getNbLigne());
        grillePane.setPrefWidth(pixelSize*this.grille.getNbColonne());

        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                if(grille.getIleGrilleJoueur(i,j)!=null){
                    Button ile = new Button(grille.getIleGrilleJoueur(i,j).getValIle()+"");
                    ile.getStyleClass().add("boutonIle");
                    grillePane.add(ile, j, i);
                }
            }
        }
    }
}
