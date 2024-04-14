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

        if(this.pontPossible.estHypothese()){
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
            if ((pontPossible.getIleSrc().getValPontDir(pontPossible.getDir()) == 1 || pontPossible.getLine2()==null) && pontPossible.estHypothese()==GrilleControler.enModeHypothese) {
                if (!pontPossible.getIleSrc().ileComplete() && !pontPossible.getIleDest().ileComplete()) {
                    if(pontPossible.getDir().equals("N") || pontPossible.getDir().equals("S")) {
                        this.setTranslateX(-5);
                    }
                    else{
                        this.setTranslateY(-5);
                    }

                    pontPossible.setLine2(new LignePont(pontPossible, this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY()));
                    if(pontPossible.getDir().equals("N") || pontPossible.getDir().equals("S")) {
                        pontPossible.getLine2().setTranslateX(5);
                    }
                    else{
                        pontPossible.getLine2().setTranslateY(5);
                    }
                    pontPossible.getLine2().addToGridPane();
                    pontPossible.getGrille().poserPont(pontPossible.getIleSrc(), pontPossible.getIleDest(), pontPossible.estHypothese());

                    if (pontPossible.getIleSrc().ileComplete()) {
                        pontPossible.getBoutonSrc().setStyle("-fx-background-color: lightgrey;");
                    }
                    if (pontPossible.getIleDest().ileComplete()) {
                        pontPossible.getBoutonDest().setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    pontPossible.getBoutonDest().setStyle("-fx-background-color: transparent");
                    pontPossible.getBoutonSrc().setStyle("-fx-background-color: transparent");
                    pontPossible.removeFromGridPane(pontPossible.getGrillePane());
                    pontPossible.getGrille().poserPont(pontPossible.getIleSrc(), pontPossible.getIleDest(), pontPossible.estHypothese());
                    pontPossible.getGrille().poserPont(pontPossible.getIleSrc(), pontPossible.getIleDest(), pontPossible.estHypothese());
                }
            }
            else if (pontPossible.getIleSrc().getValPontDir(pontPossible.getDir()) == 2 && pontPossible.estHypothese()==GrilleControler.enModeHypothese) {
                pontPossible.getBoutonDest().setStyle("-fx-background-color: transparent");
                pontPossible.getBoutonSrc().setStyle("-fx-background-color: transparent");
                pontPossible.getGrille().poserPont(pontPossible.getIleSrc(), pontPossible.getIleDest(), pontPossible.estHypothese());
                pontPossible.removeFromGridPane(pontPossible.getGrillePane());
            }
        };
    }

    /**
     * Méthode d'ajout de la ligne LignePont à l'affichage par dessus le RectPontPossible parent.
     */
    public void addToGridPane(){
        switch (pontPossible.getDir()){
            case "N" -> {
                int height = pontPossible.getIleSrc().getX() - pontPossible.getIleDest().getX() - 1;
                pontPossible.getGrillePane().add(this, pontPossible.getIleDest().getY(), pontPossible.getIleDest().getX() + 1, 1, height);
            }
            case "S" -> {
                int height = pontPossible.getIleDest().getX() - pontPossible.getIleSrc().getX() - 1;
                pontPossible.getGrillePane().add(this, pontPossible.getIleSrc().getY(), pontPossible.getIleSrc().getX() + 1, 1, height);
            }
            case "O" -> {
                int width = pontPossible.getIleSrc().getY() - pontPossible.getIleDest().getY() - 1;
                pontPossible.getGrillePane().add(this, pontPossible.getIleDest().getY() + 1, pontPossible.getIleDest().getX(), width, 1);
            }
            case "E" -> {
                int width = pontPossible.getIleDest().getY() - pontPossible.getIleSrc().getY() - 1;
                pontPossible.getGrillePane().add(this, pontPossible.getIleSrc().getY() + 1, pontPossible.getIleSrc().getX(), width, 1);
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
