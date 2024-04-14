package com.example.demojeumenu.game;

import com.example.demojeumenu.GrilleControler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class LignePont extends Line {

    /**
     * [RectPontPossible] Référence vers l'instance du pont possible parente de la LignePont.
     */
    public final RectPontPossible pontPossible;

    /**
     * Méthode d'instanciation de la ligne LignePont
     * @param pontPossible [RectPontPossible] Référence vers l'instance du pont possible parente de la LignePont.
     * @param debut_x [Integer] Valeur de l'origine de la ligne sur l'axe x.
     * @param debut_y [Integer] Valeur de l'origine de la ligne sur l'axe y.
     * @param fin_x [Integer] Valeur de fin de la ligne sur l'axe x.
     * @param fin_y [Integer] Valeur de fin de la ligne sur l'axe y.
     */
    public LignePont(RectPontPossible pontPossible, double debut_x, double debut_y, double fin_x, double fin_y){
        this.setStrokeWidth(2.0);

        this.setStartX(debut_x);
        this.setStartY(debut_y);
        this.setEndX(fin_x);
        this.setEndY(fin_y);
        this.pontPossible = pontPossible;

        if(this.pontPossible.hypothese){
            toGrey();
        }
        else{
            toBlack();
        }

        this.setOnMouseClicked(getClickedAction());
        this.setOnMouseEntered(getMouseOn());
        this.setOnMouseExited(getMouseOut());
    }

    /**
     * Méthode permettant d'obtenir l'action lorsque la souris entre sur la ligne LignePont
     * @return [EventHandler<MouseEvent>] Evenement d'entrée.
     */
    private EventHandler<MouseEvent> getMouseOn(){
        return event -> pontPossible.setFill(Paint.valueOf("#F7ECB8"));
    }

    /**
     * Méthode permettant d'obtenir l'action lorsque la souris sort de la ligne LignePont
     * @return [EventHandler<MouseEvent>] Evenement de sortie.
     */
    private EventHandler<MouseEvent> getMouseOut(){
        return event -> pontPossible.setFill(Color.TRANSPARENT);
    }

    /**
     * Méthode permettant d'obtenir l'action lorsque la souris clique sur la ligne LignePont
     * @return [EventHandler<MouseEvent>] Evenement de clique.
     */
    private EventHandler<MouseEvent> getClickedAction() {
        return event -> {
            if ((pontPossible.ileSrc.getValPontDir(pontPossible.dir) == 1 || pontPossible.line2==null) && pontPossible.hypothese==GrilleControler.enModeHypothese) {
                if (!pontPossible.ileSrc.ileComplete() && !pontPossible.ileDest.ileComplete()) {
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
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest, pontPossible.hypothese);

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
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest, pontPossible.hypothese);
                    pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest, pontPossible.hypothese);
                }
            }
            else if (pontPossible.ileSrc.getValPontDir(pontPossible.dir) == 2 && pontPossible.hypothese==GrilleControler.enModeHypothese) {
                pontPossible.boutonDest.setStyle("-fx-background-color: transparent");
                pontPossible.boutonSrc.setStyle("-fx-background-color: transparent");
                pontPossible.grille.poserPont(pontPossible.ileSrc, pontPossible.ileDest, pontPossible.hypothese);
                pontPossible.removeFromGridPane(pontPossible.grillePane);
            }
        };
    }

    /**
     * Méthode d'ajout de la ligne LignePont à l'affichage par dessus le RectPontPossible parent.
     */
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


    public void toRed(){
        this.setStroke(Color.RED);
    }

    public void toBlack(){
        this.setStroke(Color.BLACK);
    }

    public void toGrey(){
        this.setStroke(Color.valueOf("#A8A8A8"));
    }
}
