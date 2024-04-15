package com.example.demojeumenu.game;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class RectPontCassant extends RectPontPossible{

    private Timeline avantDestruction;

    /**
     * Méthode d'instanciation d'un RectPontPossible.
     *
     * @param grille     [GrilleJeu] La grille du backend.
     * @param grillePane [GridPane] La grille affichée à l'utilisateur (au frontend).
     * @param width      [Integer] Longueur du rectangle.
     * @param height     [Integer] Largeur du rectangle.
     * @param boutonSrc  [Button] Bouton de l'ile source inclu dans grillePane.
     * @param boutonDest [Button] Bouton de l'ile destination inclu dans grillePane.
     * @param ileSrc     [Ile] Ile source du RectPontPossible.
     * @param ileDest    [Ile] Ile destination du RectPontPossible.
     * @param dir        [String] Direction du pont.
     * @param hypothese  [Boolean] Indicateur vérifiant s'il s'agit d'un pont hypothèse ou pas.
     */
    public RectPontCassant(GrilleJeu grille, GridPane grillePane, int width, int height, Button boutonSrc, Button boutonDest, Ile ileSrc, Ile ileDest, String dir, boolean hypothese) {
        super(grille, grillePane, width, height, boutonSrc, boutonDest, ileSrc, ileDest, dir, hypothese);
        avantDestruction = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            ParallelTransition parallelTransition = new ParallelTransition();

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), this.getLine1());
            fadeTransition1.setFromValue(1.0);
            fadeTransition1.setToValue(0.0);

            if(this.getLine2()!=null) {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), this.getLine2());
                fadeTransition2.setFromValue(1.0);
                fadeTransition2.setToValue(0.0);
                parallelTransition.getChildren().addAll(fadeTransition1, fadeTransition2);
            }
            else{
                parallelTransition.getChildren().addAll(fadeTransition1);
            }

            parallelTransition.setOnFinished(event1 -> {
                if(this.ileSrc.ileComplete()){
                    this.boutonSrc.setStyle("-fx-background-color: transparent");
                }
                if(this.ileDest.ileComplete()){
                    this.boutonDest.setStyle("-fx-background-color: transparent");
                }
                removeFromGridPane(this.grillePane);
                this.grille.poserPont(this.ileSrc, this.ileDest, this.estHypothese());
                if(getLine2() == null){
                    this.grille.poserPont(this.ileSrc, this.ileDest, this.estHypothese());
                }
            });

            parallelTransition.play();
        }));

        avantDestruction.play();
    }
}
