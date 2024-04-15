package com.example.demojeumenu.game;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
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
        avantDestruction = new Timeline(new KeyFrame(new Duration(10), event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), this);
            fadeTransition1.setFromValue(1.0);
            fadeTransition1.setToValue(0.0);
            fadeTransition1.setOnFinished(event1 -> {
                this.removeFromGridPane(grillePane);
                grille.poserPont(ileSrc,ileDest,hypothese);
                if(getLine2()!=null){
                    grille.poserPont(ileSrc,ileDest,hypothese);
                }
            });
            fadeTransition1.play();
        }));

        avantDestruction.play();
    }
}
