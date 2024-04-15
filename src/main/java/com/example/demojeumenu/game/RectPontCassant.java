package com.example.demojeumenu.game;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Classe créant un pont cassant. C'est-à-dire un pont ayant une durée de vie limitée.
 */
public class RectPontCassant extends RectPontPossible{

    /**
     * Méthode d'instanciation d'un RectPontCassant.
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
        Timeline avantDestruction = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
            ParallelTransition parallelTransition = new ParallelTransition();

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), this.getLine1());
            fadeTransition1.setFromValue(1.0);
            fadeTransition1.setToValue(0.0);
            parallelTransition.getChildren().add(fadeTransition1);

            if (this.getLine2() != null) {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), this.getLine2());
                fadeTransition2.setFromValue(1.0);
                fadeTransition2.setToValue(0.0);
                parallelTransition.getChildren().add(fadeTransition2);
            }

            parallelTransition.setOnFinished(event1 -> {
                if (this.ileSrc.ileComplete()) {
                    this.boutonSrc.setStyle("-fx-background-color: transparent");
                }
                if (this.getIleDest().ileComplete()) {
                    this.boutonDest.setStyle("-fx-background-color: transparent");
                }

                if (getLine2() != null || this.getIleSrc().getValIle() != 1 || this.getIleDest().getValIle() != 1) {
                    this.grille.poserPont(this.ileSrc, this.ileDest, this.estHypothese());
                } else {
                    this.grille.poserPont(this.ileSrc, this.ileDest, this.estHypothese());
                    this.grille.poserPont(this.ileSrc, this.ileDest, this.estHypothese());
                }
                removeFromGridPane(this.grillePane);
                parallelTransition.stop();
            });

            parallelTransition.play();
        }));

        avantDestruction.play();
    }
}
