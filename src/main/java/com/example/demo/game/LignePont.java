package com.example.demo.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class LignePont extends Line {
    private final RectPontPossible pontPossible;

    public LignePont(RectPontPossible pontPossible, double debut_x, double debut_y, double fin_x, double fin_y){
        this.setStrokeWidth(2.0);

        this.setStartX(debut_x);
        this.setStartY(debut_y);
        this.setEndX(fin_x);
        this.setEndY(fin_y);
        this.pontPossible = pontPossible;

        this.setOnMouseClicked(getClickedAction());
    }

    private EventHandler<MouseEvent> getClickedAction() {
        return event -> {
            if (((IleJoueur) pontPossible.ileSrc).getValPontDir(pontPossible.dir) == 1) {
                if (!pontPossible.ileSrc.ileComplete() && !pontPossible.ileDest.ileComplete()) {
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest);
                    if(pontPossible.dir.equals("N") || pontPossible.dir.equals("S")) {
                        this.setTranslateX(-5);
                    }
                    else{
                        this.setTranslateY(-5);
                    }

                    pontPossible.line2 = new LignePont(pontPossible, this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY());
                    if(pontPossible.dir.equals("N") || pontPossible.dir.equals("S")) {
                        pontPossible.line2.setTranslateX(5);
                    }
                    else{
                        pontPossible.line2.setTranslateY(5);
                    }


                    pontPossible.line2.addToGridPane();

                    if (pontPossible.ileSrc.ileComplete()) {
                        pontPossible.boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (pontPossible.ileDest.ileComplete()) {
                        pontPossible.boutonDest.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    pontPossible.boutonDest.setStyle("-fx-background-color: transparent");
                    pontPossible.boutonSrc.setStyle("-fx-background-color: transparent");
                    pontPossible.removeFromGridPane(pontPossible.grillePane);
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest);
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest);
                }
            }
            else if (((IleJoueur) pontPossible.ileSrc).getValPontDir(pontPossible.dir) == 2) {
                pontPossible.boutonDest.setStyle("-fx-background-color: transparent");
                pontPossible.boutonSrc.setStyle("-fx-background-color: transparent");
                pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest);
            }
        };
    }

    public void addToGridPane(){
        switch (pontPossible.dir){
            case "N" -> {
                int height = pontPossible.ileSrc.getX() - pontPossible.ileDest.getX() - 1;
                pontPossible.grillePane.add(this, pontPossible.ileDest.getY(), pontPossible.ileDest.getX() + 1, 1, height);
            }
            case "S" -> {
                int height = pontPossible.ileDest.getX() - pontPossible.ileSrc.getX() - 1;
                pontPossible.grillePane.add(this, pontPossible.ileSrc.getY(), pontPossible.ileSrc.getX() + 1, 1, height);
            }
            case "O" -> {
                int width = pontPossible.ileSrc.getY() - pontPossible.ileDest.getY() - 1;
                pontPossible.grillePane.add(this, pontPossible.ileDest.getY() + 1, pontPossible.ileDest.getX(), width, 1);
            }
            case "E" -> {
                int width = pontPossible.ileDest.getY() - pontPossible.ileSrc.getY() - 1;
                pontPossible.grillePane.add(this, pontPossible.ileSrc.getY() + 1, pontPossible.ileSrc.getX(), width, 1);
            }
        }
    }
}
