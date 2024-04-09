package com.example.demojeumenu;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.desktop.AboutEvent;

public class RectPontPossible extends Rectangle {
    public boolean mouseOn;
    public LignePont line1;
    public LignePont line2;
    public String dir;
    public Ile ileSrc;
    public Ile ileDest;
    public Button boutonSrc;
    public Button boutonDest;

    public GrilleJeu grille;
    public GridPane grillePane;



    public RectPontPossible(GrilleJeu grille, GridPane grillePane, int width, int height, Button boutonSrc, Button boutonDest, Ile ileSrc, Ile ileDest, String dir){
        super(width,height);
        this.line1 = null;
        this.line2 = null;
        this.mouseOn = false;
        this.boutonSrc = boutonSrc;
        this.boutonDest = boutonDest;
        this.ileDest = ileDest;
        this.ileSrc = ileSrc;
        this.dir = dir;
        this.grille = grille;
        this.grillePane = grillePane;

        this.setFill(Color.valueOf("#F7ECB8"));
        this.toFront();

        this.setOnMouseEntered(getMouseOn(boutonDest));
        this.setOnMouseClicked(getClickedAction());
        this.setOnMouseExited(getMouseOut());
    }

    public void removeFromGridPane(GridPane grillePane){
        if(this.line1 == null && this.line2 == null) {
            if(!mouseOn) grillePane.getChildren().removeAll(this);
        }
        else if (this.line2 == null) {
            grillePane.getChildren().removeAll(this, line1);
            this.line1 = null;
        }
        else{
            grillePane.getChildren().removeAll(this, line1, line2);
            this.line1 = null;
            this.line2 = null;
        }
    }

    public void addToGridPane(){
        switch (dir){
            case "N" -> {
                int height = ileSrc.getX() - ileDest.getX() - 1;
                grillePane.add(this, ileDest.getY(), ileDest.getX()+1, 1, height);
            }
            case "S" -> {
                int height = ileDest.getX() - ileSrc.getX() - 1;
                grillePane.add(this, ileSrc.getY(), ileSrc.getX()+1, 1, height);
            }
            case "O" -> {
                int width = ileSrc.getY() - ileDest.getY() - 1;
                grillePane.add(this, ileDest.getY()+1, ileDest.getX(), width, 1);
            }
            case "E" -> {
                int width = ileDest.getY() - ileSrc.getY() - 1;
                grillePane.add(this, ileSrc.getY()+1, ileSrc.getX(), width, 1);
            }
        }
    }

    private EventHandler<MouseEvent> getMouseOn(Button buttonDest){
        return event -> {
            this.mouseOn = true;
            if (this.getFill() != Color.TRANSPARENT) {
                buttonDest.setStyle("-fx-background-color: transparent;");
            }
        };
    }

    private EventHandler<MouseEvent> getClickedAction(){
        return event -> {
            int valPontDir = ((IleJoueur) ileSrc).getValPontDir(dir);
            switch (valPontDir){
                case 0 -> {
                    this.setFill(Color.TRANSPARENT);

                    grille.poserPont(ileSrc, ileDest);
                    if(dir.equals("N") || dir.equals("S")) {
                        line1 = new LignePont(this, (int) (this.getX() + this.getWidth() / 2), (int) this.getY(), (int) (this.getX() + this.getWidth() / 2), (int) (this.getY() + this.getHeight()));
                    }
                    else{
                        line1 = new LignePont(this, (int) this.getX(), (int) (this.getY() + this.getHeight()/2), (int) (this.getX() + this.getWidth()), (int) (this.getY()+ this.getHeight()/2));
                    }
                    line1.addToGridPane();

                    if (ileSrc.ileComplete()) {
                        boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (ileDest.ileComplete()) {
                        boutonDest.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                case 1 -> {
                    if (!ileSrc.ileComplete() && !ileDest.ileComplete()) {
                        grille.poserPont(ileSrc, ileDest);
                        if(dir.equals("N") || dir.equals("S")) {
                            line1.setTranslateX(-5);
                        }
                        else{
                            line1.setTranslateY(-5);
                        }

                        line2 = new LignePont(this, line1.getStartX(), line1.getStartY(), line1.getEndX(), line1.getEndY());
                        if(dir.equals("N") || dir.equals("S")) {
                            line2.setTranslateX(5);
                        }
                        else{
                            line2.setTranslateY(5);
                        }

                        line2.addToGridPane();

                        if (ileSrc.ileComplete()) {
                            boutonSrc.setStyle("-fx-background-color: lightgrey;");
                        }
                        if (ileDest.ileComplete()) {
                            boutonDest.setStyle("-fx-background-color: lightgrey;");
                        }
                    } else {
                        boutonDest.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        removeFromGridPane(grillePane);
                        grille.poserPont(ileSrc, ileDest);
                        grille.poserPont(ileSrc, ileDest);
                    }
                }
                case 2 -> {
                    boutonDest.setStyle("-fx-background-color: transparent");
                    boutonSrc.setStyle("-fx-background-color: transparent");
                    removeFromGridPane(grillePane);
                    grille.poserPont(ileSrc, ileDest);
                }
            }
        };
    }

    private EventHandler<MouseEvent> getMouseOut(){
        return event -> {
            this.mouseOn = false;
            if (this.getFill() != Color.TRANSPARENT) {
                removeFromGridPane(grillePane);
                boutonDest.setStyle("-fx-background-color: transparent;");
            }
        };
    }

    public void simulerClick(){
        MouseEvent mouseEvent = new MouseEvent(
                MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null
        );

        // Déclencher l'événement sur le rectangle
        this.fireEvent(mouseEvent);
    }
}
