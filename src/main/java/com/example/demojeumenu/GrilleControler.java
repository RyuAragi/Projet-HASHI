package com.example.demojeumenu;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.utils.BaseController;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.*;

public class GrilleControler extends BaseController {
    private GrilleJeu grille;
    private ClassLoader classLoader;
    public String levelFileNameCorrected;

    @FXML
    private Button quit;

    @FXML
    private Button zoom;

    @FXML
    private Button dezoom;

    @FXML
    private Button restart;

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

    private void fillCaseToNextIle(int column, int row, Ile ileDest, boolean versHautOuDroite, boolean highlight){
        if(column==ileDest.getY()){
            if (versHautOuDroite) {     //en haut
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && columnIndex == column && rowIndex < row && rowIndex >= ileDest.getX()) {
                        if(highlight) node.setStyle("-fx-background-color: yellow;");
                        else node.setStyle("-fx-background-color: transparent;");
                    }
                }
            }
            else {   //en bas
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && columnIndex == column && rowIndex > row && rowIndex <= ileDest.getX()) {
                        if(highlight) node.setStyle("-fx-background-color: yellow;");
                        else node.setStyle("-fx-background-color: transparent;");
                    }
                }
            }
        }
        else if(row==ileDest.getX()){
            if (versHautOuDroite) {     //à droite
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && rowIndex == row && columnIndex > column && columnIndex <= ileDest.getY()) {
                        if(highlight) node.setStyle("-fx-background-color: yellow;");
                        else node.setStyle("-fx-background-color: transparent;");

                    }
                }
            }
            else {   //à gauche
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && rowIndex == row && columnIndex < column && columnIndex >= ileDest.getY()) {
                        if(highlight) node.setStyle("-fx-background-color: yellow;");
                        else node.setStyle("-fx-background-color: transparent;");

                    }
                }
            }
        }
    }


    private void highlightRowAndColumn(int column, int row, GrilleJeu grille, boolean highlight) {
        Ile ileSrc = grille.getIleGrilleJoueur(row, column);
        Ile ileNord = ileSrc.getIleNord(grille);
        Ile ileSud = ileSrc.getIleSud(grille);
        Ile ileEst = ileSrc.getIleEst(grille);
        Ile ileOuest = ileSrc.getIleOuest(grille);

        if(ileNord!=null) {
            fillCaseToNextIle(column, row, ileNord, true, highlight);
        }
        if(ileSud!=null){
            fillCaseToNextIle(column, row, ileSud, false, highlight);
        }
        if(ileEst!=null){
            fillCaseToNextIle(column, row, ileEst, true, highlight);
        }
        if(ileOuest!=null){
            fillCaseToNextIle(column, row, ileOuest, false, highlight);
        }
    }

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

            for (Node node : grillePane.getChildren()) {
                if (node instanceof Button bouton) {
                    bouton.setPrefWidth(bouton.getPrefWidth() + 10);
                    bouton.setPrefHeight(bouton.getPrefHeight() + 10);

                    double nouvelleTaillePolice = bouton.getFont().getSize() + 3; // Ajustez selon vos besoins
                    bouton.setStyle("-fx-font-size: " + nouvelleTaillePolice + "px");
                }
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
                column.setPrefWidth(column.getPrefWidth() - 10);
            }
            for (int i = 0; i < grillePane.getRowConstraints().size(); i++) {
                RowConstraints row = grillePane.getRowConstraints().get(i);
                row.setPrefHeight(row.getPrefHeight() - 10);
            }

            for (Node node : grillePane.getChildren()) {
                if (node instanceof Button bouton) {
                    // Réduire la taille du bouton
                    bouton.setPrefWidth(bouton.getPrefWidth() - 10);
                    bouton.setPrefHeight(bouton.getPrefHeight() - 10);

                    double nouvelleTaillePolice = bouton.getFont().getSize() - 3; // Ajustez selon vos besoins
                    bouton.setStyle("-fx-font-size: " + nouvelleTaillePolice + "px");
                }
            }

        }
    }

    public void initData(String levelFileName) {
        this.levelFileNameCorrected = levelFileName;
        System.out.println("LevelFileNameCorrected: " + levelFileNameCorrected);

        // Get the resource as a stream
        InputStream resourceStream = getClass().getResourceAsStream("niveaux/" + levelFileNameCorrected);

        if (resourceStream == null) {
            System.err.println("Resource not found: " + levelFileNameCorrected);
            // Handle the error, for example by throwing an exception or returning null
        } else {
            // Pass the InputStream to GrilleJeu
            this.grille = new GrilleJeu(new InputStreamReader(resourceStream));
            System.out.println("Grille: " + this.grille);
            initializeGrille();
        }
    }




    public void initializeGrille() {

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
        SoundUtils.addHoverSound(restart);

        SoundUtils.addClickSound(zoom, this::zoomMethod);
        SoundUtils.addClickSound(dezoom, this::dezoomMethod);

        //this.grille = new GrilleJeu(levelFileNameCorrected);
        System.out.print("Grille charger :"+this.grille.getNbColonne() + " - " + this.grille.getNbLigne()+"\n");

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
            row.setValignment(VPos.CENTER);
            grillePane.getRowConstraints().add(row);
        }
        for (int i = 0; i < this.grille.getNbColonne(); i++) {
            ColumnConstraints column = new ColumnConstraints(pixelSize);
            column.setHalignment(HPos.CENTER);
            grillePane.getColumnConstraints().add(column);
        }
        grillePane.setAlignment(Pos.CENTER);
        grillePane.setPrefHeight(pixelSize*this.grille.getNbLigne());
        grillePane.setPrefWidth(pixelSize*this.grille.getNbColonne());
        grillePane.toFront();

        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                if(grille.getIleGrilleJoueur(i,j)!=null) {
                    Button ile = new Button(grille.getIleGrilleJoueur(i, j).getValIle() + "");
                    ile.getStyleClass().add("boutonIle");
                    ile.setPrefSize(pixelSize - 2, pixelSize - 4);

                    ile.setOnMouseEntered(event -> {
                        ile.setStyle("-fx-background-color: yellow;");

                        int row = GridPane.getRowIndex(ile);
                        int col = GridPane.getColumnIndex(ile);

                        highlightRowAndColumn(col, row, grille, true);
                    });


                    ile.setOnMouseExited(event -> {
                        ile.setStyle("-fx-background-color: transparent;");

                        int row = GridPane.getRowIndex(ile);
                        int col = GridPane.getColumnIndex(ile);

                        highlightRowAndColumn(col, row, grille, false);

                    });

                    grillePane.add(ile, j, i);
                }
                else{
                    Button ile = new Button("");
                    ile.getStyleClass().add("undisplayButton");
                    ile.setPrefSize(pixelSize - 2, pixelSize - 4);
                    ile.setDisable(true);
                    grillePane.add(ile, j, i);
                }
            }
        }
    }

    @FXML
    public void initialize() {

    }
}
