package com.example.demojeumenu;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GrilleControler2 extends BaseController {
    private GrilleJeu grille;
    private int fontSize;
    private int pixelSize;
    private boolean enModeHypothese;

    @FXML
    private Button quit;

    @FXML
    private Button param;

    @FXML
    private Label chrono;

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
    private Button hypothese;

    @FXML
    private Button valid_hypo;

    @FXML
    private Button supp_hypo;

    @FXML
    private GridPane grillePane;

    private void incrementeChrono(){
        String text = chrono.getText();
        String[] sepChrono = text.split(":");

        int secondes = Integer.parseInt(sepChrono[1]);
        int minutes = Integer.parseInt(sepChrono[0]);

        secondes+=1;
        if(secondes==60){
            secondes = 0;
            minutes += 1;
        }

        chrono.setText(minutes+":"+secondes);
    }

    private void restartChrono(){
        chrono.setVisible(true);
        chrono.setText("00:00");
    }

    private void fillCaseToNextIle(int column, int row, Ile ileDest, boolean versHautOuDroite, boolean highlight){
        if(column==ileDest.getY()){
            if (versHautOuDroite) {     //en haut
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && columnIndex == column && rowIndex < row && rowIndex >= ileDest.getX()) {
                        if(highlight){
                            node.setStyle("-fx-background-color: yellow;");
                            if(rowIndex == ileDest.getX()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: yellow;");
                            }
                        }
                        else{
                            node.setStyle("-fx-background-color: transparent;");
                            if(rowIndex == ileDest.getX()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: transparent;");
                            }
                        }
                    }
                }
            }
            else {   //en bas
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && columnIndex == column && rowIndex > row && rowIndex <= ileDest.getX()) {
                        if(highlight){
                            node.setStyle("-fx-background-color: yellow;");
                            if(rowIndex == ileDest.getX()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: yellow;");
                            }
                        }
                        else{
                            node.setStyle("-fx-background-color: transparent;");
                            if(rowIndex == ileDest.getX()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: transparent;");
                            }
                        }
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
                        if(highlight){
                            node.setStyle("-fx-background-color: yellow;");
                            if(columnIndex == ileDest.getY()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: yellow;");
                            }
                        }
                        else{
                            node.setStyle("-fx-background-color: transparent;");
                            if(columnIndex == ileDest.getY()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: transparent;");
                            }
                        }
                    }
                }
            }
            else {   //à gauche
                for (Node node : grillePane.getChildren()) {
                    Integer columnIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);

                    if (columnIndex != null && rowIndex != null && rowIndex == row && columnIndex < column && columnIndex >= ileDest.getY()) {
                        if(highlight){
                            node.setStyle("-fx-background-color: yellow;");
                            if(columnIndex == ileDest.getY()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: yellow;");
                            }
                        }
                        else{
                            node.setStyle("-fx-background-color: transparent;");
                            if(columnIndex == ileDest.getY()){
                                node.setStyle("-fx-font-size: " + this.fontSize + "px; -fx-background-color: transparent;");
                            }
                        }
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
            System.out.println(grille.getIleGrilleJoueur(ileNord.getX(), ileNord.getY()).getValIle());
            System.out.println("création rectangle nord");
            double rectWidth = this.pixelSize/2; // Largeur du rectangle (égale à la taille d'un pixel)
            double rectHeight = (ileNord.getY() - ileSrc.getY()) * this.pixelSize; // Hauteur du rectangle

            // Coordonnées du coin supérieur gauche du rectangle
            double rectX = ileSrc.getX() * this.pixelSize; // Coordonnée X de l'île source
            double rectY = ileSrc.getY() * this.pixelSize; // Coordonnée Y de l'île source

            // Création du rectangle
            Rectangle rect = new Rectangle(ileNord.getX(), ileNord.getY(), rectWidth, rectHeight);
            rect.setFill(Color.YELLOW); // Remplissage en jaune

            // Affichage du rectangle
            grillePane.getChildren().add(rect);


            //fillCaseToNextIle(column, row, ileNord, true, highlight);
        }
        if(ileSud!=null){
            //fillCaseToNextIle(column, row, ileSud, false, highlight);
        }
        if(ileEst!=null){
            //fillCaseToNextIle(column, row, ileEst, true, highlight);
        }
        if(ileOuest!=null){
            //fillCaseToNextIle(column, row, ileOuest, false, highlight);
        }
    }

    @FXML
    private void zoomMethod(){
        if(grillePane.getHeight()<0.8*scene.getHeight() && grillePane.getWidth()<0.8*scene.getWidth()) {
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

            this.fontSize += 3;
            for (Node node : grillePane.getChildren()) {
                if (node instanceof Button bouton) {
                    bouton.setPrefWidth(bouton.getPrefWidth() + 10);
                    bouton.setPrefHeight(bouton.getPrefHeight() + 10);

                    bouton.setStyle("-fx-font-size: " + this.fontSize + "px");
                }
            }
        }
    }

    @FXML
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

            this.fontSize -= 3;
            for (Node node : grillePane.getChildren()) {
                if (node instanceof Button bouton) {
                    // Réduire la taille du bouton
                    bouton.setPrefWidth(bouton.getPrefWidth() - 10);
                    bouton.setPrefHeight(bouton.getPrefHeight() - 10);

                    bouton.setStyle("-fx-font-size: " + this.fontSize + "px");
                }
            }

        }
    }


    private Button createButton(String text, boolean use) {
        Button button = new Button(text);
        if(use) button.getStyleClass().add("boutonIle");
        else{
            button.getStyleClass().add("undisplayedButton");
            button.setVisible(false);
            button.setDisable(true);
        }
        button.setStyle("-fx-font-size: " + this.fontSize + "px;");
        button.setPrefSize(this.pixelSize - 2, this.pixelSize - 4);
        return button;
    }

    private void createRectangles(int column, int row, GrilleJeu grille) {
        Ile ileSrc = grille.getIleGrilleJoueur(row, column);
        Ile ileNord = ileSrc.getIleNord(grille);

        Ile ileSud = ileSrc.getIleSud(grille);
        Ile ileEst = ileSrc.getIleEst(grille);
        Ile ileOuest = ileSrc.getIleOuest(grille);

        if (ileNord != null) {
            System.out.println(ileNord.getX()+" "+ileNord.getY()+" "+grille.getIleGrilleJoueur(ileNord.getX(), ileNord.getY()).getValIle());
            createRectangle(ileSrc, ileNord);
        }
        /*
        if (ileSud != null) {
            createRectangle(ileSrc, ileSud);
        }
        if (ileEst != null) {
            createRectangle(ileSrc, ileEst);
        }
        if (ileOuest != null) {
            createRectangle(ileSrc, ileOuest);
        }
        */
    }

    private void createRectangle(Ile ileSrc, Ile ileDest) {
        double rectWidth = (double) this.pixelSize / 2;
        double rectHeight = (Math.abs(ileDest.getX() - ileSrc.getX())) * this.pixelSize;

        // Calculer les coordonnées du coin supérieur gauche du GridPane dans la scène
        Bounds gridBounds = grillePane.localToScene(grillePane.getBoundsInLocal());
        double gridX = gridBounds.getMinX();
        double gridY = gridBounds.getMinY();

        // Calculer les coordonnées du coin supérieur gauche de l'île source dans le GridPane
        Node srcNode = grillePane.getChildren().get(ileSrc.getX() * grille.getNbColonne() + ileSrc.getY());
        Bounds srcBounds = srcNode.localToScene(srcNode.getBoundsInLocal());
        double srcX = srcBounds.getMinX();
        double srcY = srcBounds.getMinY();

        // Calculer les coordonnées des coins supérieurs gauche du rectangle
        double rectX = Math.min(srcX, gridX) + Math.abs(srcX - gridX);
        double rectY = Math.min(srcY, gridY) + Math.abs(srcY - gridY);

        Rectangle rect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        rect.setFill(Color.YELLOW);

        
    }

    @FXML
    private void testMethod(){
        System.out.println("test");
    }

    private void clearRectangles() {
        grillePane.getChildren().removeIf(node -> node instanceof Rectangle);
    }

    @FXML
    private void hypotheseMethod(){
        System.out.println("hypothèse");
        if(this.enModeHypothese){
            System.out.println("hypothese méthod 1");
            this.enModeHypothese = false;

            valid_hypo.setDisable(true);
            valid_hypo.setVisible(false);
            supp_hypo.setDisable(true);
            supp_hypo.setVisible(false);

            dezoom.setDisable(false);
            dezoom.setVisible(true);
            zoom.setDisable(false);
            zoom.setVisible(true);
            check.setDisable(false);
            check.setVisible(true);
            undo.setDisable(false);
            undo.setVisible(true);
            redo.setDisable(false);
            redo.setVisible(true);
            quit.setDisable(false);
            quit.setVisible(true);
            help.setDisable(false);
            help.setVisible(true);
            restart.setDisable(false);
            restart.setVisible(true);
        }
        else{
            System.out.println("hypothese méthod 2");
            this.enModeHypothese = true;

            valid_hypo.setDisable(false);
            valid_hypo.setVisible(true);
            supp_hypo.setDisable(false);
            supp_hypo.setVisible(true);

            dezoom.setDisable(true);
            dezoom.setVisible(false);
            zoom.setDisable(true);
            zoom.setVisible(false);
            check.setDisable(true);
            check.setVisible(false);
            undo.setDisable(true);
            undo.setVisible(false);
            redo.setDisable(true);
            redo.setVisible(false);
            quit.setDisable(true);
            quit.setVisible(false);
            help.setDisable(true);
            help.setVisible(false);
            restart.setDisable(true);
            restart.setVisible(false);
        }
    }

    private void initButtons(){
        this.enModeHypothese = true;
        valid_hypo.setDisable(true);
        valid_hypo.setVisible(false);
        supp_hypo.setDisable(true);
        supp_hypo.setVisible(false);
        /*
            Changer la musique...
         */
        restartChrono();
    }

    private void createYellowBridges(GridPane gridPane, Button ile, int row, int col){
        Ile ileSrc = grille.getIleGrilleJoueur(row, col);
        Ile ileNord = ileSrc.getIleNord(grille);
        if(ileNord!=null) {
            Button buttonDestNord = null;
            for (Node node : gridPane.getChildren()) {
                Integer columnIndex = GridPane.getColumnIndex(node);
                Integer rowIndex = GridPane.getRowIndex(node);

                if (columnIndex == ileNord.getX() && rowIndex == ileNord.getY()) {
                    buttonDestNord = (Button) node;
                }
            }

            if (buttonDestNord != null) {
                System.out.println(ileNord.getX() + " " + ileNord.getY() + " " + grille.getIleGrilleJoueur(ileNord.getX(), ileNord.getY()).getValIle());
                Line line = new Line();
                line.setStartX(ile.getLayoutX() + ile.getWidth() / 2);
                line.setStartY(ile.getLayoutY() + ile.getHeight() / 2);
                line.setEndX(buttonDestNord.getLayoutX() + buttonDestNord.getWidth() / 2);
                line.setEndY(buttonDestNord.getLayoutY() + buttonDestNord.getHeight() / 2);
                System.out.println("line add");
                gridPane.getChildren().add(line);
            }
        }
    }

    @FXML
    public void initialize(){
        initButtons();

        this.grille = new GrilleJeu("./src/main/java/com/example/demojeumenu/niveaux/facile/Facile-2.txt");
        System.out.print(this.grille.getNbColonne() + " - " + this.grille.getNbLigne());

        if(this.grille.getNbColonne()<10 && this.grille.getNbLigne()<10){
            this.fontSize=15;
            this.pixelSize = 50;
        }
        else{
            this.fontSize=14;
            this.pixelSize = 25;
        }
        /*
            A compléter
         */

        for (int i = 0; i < this.grille.getNbLigne(); i++) {
            RowConstraints row = new RowConstraints(this.pixelSize);
            row.setValignment(VPos.CENTER);
            grillePane.getRowConstraints().add(row);
        }
        for (int i = 0; i < this.grille.getNbColonne(); i++) {
            ColumnConstraints column = new ColumnConstraints(this.pixelSize);
            column.setHalignment(HPos.CENTER);
            grillePane.getColumnConstraints().add(column);
        }
        grillePane.setAlignment(Pos.CENTER);
        grillePane.setPrefHeight(this.pixelSize*this.grille.getNbLigne());
        grillePane.setPrefWidth(this.pixelSize*this.grille.getNbColonne());
        grillePane.toFront();

        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                if(grille.getIleGrilleJoueur(i,j)!=null) {
                    Button ile = createButton(grille.getIleGrilleJoueur(i, j).getValIle() + "", true);
                    grillePane.add(ile, j, i);

                    int row = GridPane.getRowIndex(ile);
                    int col = GridPane.getColumnIndex(ile);
                    ile.setOnMouseEntered(event -> {
                        createYellowBridges(grillePane, ile, row, col);
                    });
                }
                else{
                    Button ile = createButton("", false);
                    grillePane.add(ile, j, i);
                }
            }
        }

    }
}
