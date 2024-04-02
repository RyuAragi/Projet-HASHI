package com.example.demojeumenu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GrilleControler extends BaseController {
    /**
     * Enumérateur de directions parmis Nord, Sud, Ouest et Est
     */
    enum Direction {None, Nord, Est, Ouest, Sud}
    private GrilleJeu grille;
    private int fontSize;
    private int pixelSize;
    private boolean enModeHypothese;
    private Direction onYellowBridge;
    private static Timeline timeline;
    HashMap<Direction, HashMap<Button,Rectangle>> rectFromMousedIle;

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

    private void incrementeChrono() {
        String text = chrono.getText();
        String[] sepChrono = text.split(":");

        int secondes = Integer.parseInt(sepChrono[1]);
        int minutes = Integer.parseInt(sepChrono[0]);

        secondes += 1;
        if (secondes == 60) {
            secondes = 0;
            minutes += 1;
        }

        chrono.setText(String.format("%02d", minutes) + ":" + String.format("%02d", secondes));
    }

    private void initChrono(){
        chrono.setText("00:00");
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Appeler la méthode pour incrémenter le chrono chaque seconde
            incrementeChrono();
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE); // Pour répéter indéfiniment
    }

    private void startChrono(){
        this.timeline.play();
    }

    private void stopChrono(){
        this.timeline.stop();
    }

    private String chronoToString(){
        return this.chrono.getText();
    }

    public static Timeline getChrono(){ return timeline; }

    private Button createButton(Ile ile, int x, int y) {
        Button boutonIle = new Button(ile.getValIle()+"");
        boutonIle.toFront();
        boutonIle.getStyleClass().add("boutonIle");
        boutonIle.setStyle("-fx-font-size: " + this.fontSize + "px;");
        boutonIle.setPrefSize(this.pixelSize, this.pixelSize);

        return boutonIle;
    }

    @FXML
    private void testMethod() {
        System.out.println("test");
    }

    @FXML
    private void hypotheseMethod() {
        System.out.println("hypothèse");
        if (this.enModeHypothese) {
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
        } else {
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

    private void initButtons() {
        this.enModeHypothese = true;
        valid_hypo.setDisable(true);
        valid_hypo.setVisible(false);
        supp_hypo.setDisable(true);
        supp_hypo.setVisible(false);
        /*
            Changer la musique...
         */
    }

    private void createBridges(Button buttonSrc, int row, int col){
        onYellowBridge = Direction.None;
        this.rectFromMousedIle = new HashMap<>();
        Ile ileSrc = grille.getIleGrilleJoueur(row, col);
        this.rectFromMousedIle.put(Direction.Nord, createNorthBridge(ileSrc, buttonSrc));
        this.rectFromMousedIle.put(Direction.Sud, createSouthBridge(ileSrc, buttonSrc));
        this.rectFromMousedIle.put(Direction.Ouest, createWestBridge(ileSrc, buttonSrc));
        this.rectFromMousedIle.put(Direction.Est, createEastBridge(ileSrc, buttonSrc));
    }

    private void createLine2(Line line1, Line line2){
        line2.setStartX(line1.getStartX());
        line2.setStartY(line1.getStartY());
        line2.setEndX(line1.getEndX());
        line2.setEndY(line1.getEndY());
        line2.setStrokeWidth(2.0);
        line2.toFront();
    }

    // ------------------------------------------------------------------- //
    //                - GESTION DES ACTIONS SUR LA GRILLE -                //
    //                       !!! CODE SENSIBLE !!!                         //
    //   Le programme passera beaucoup de temps sur cette portion de code  //
    // ------------------------------------------------------------------- //

    private HashMap<Button, Rectangle> createNorthBridge(Ile ileSrc, Button boutonSrc) {
        Ile ileNord = ileSrc.getIleNord(grille);

        if (ileNord != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileNord) && !ileNord.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur)ileSrc).getValPontDir("N") == 0) {
            Button buttonDestNord = findButtonByCoord(ileNord.getY(), ileNord.getX());

            if (buttonDestNord != null) {
                buttonDestNord.setStyle("-fx-background-color: yellow;");
                int height = ileSrc.getX() - ileNord.getX() - 1;
                Rectangle rectangle = createYellowRectangle(height,Direction.Nord);

                setNorthRectangleMouseEvents(rectangle, buttonDestNord, ileSrc, ileNord, boutonSrc);

                grillePane.add(rectangle, ileNord.getY(), ileNord.getX() + 1, 1, height);
                HashMap<Button, Rectangle> northRectAndButton = new HashMap<>();
                northRectAndButton.put(buttonDestNord, rectangle);

                return northRectAndButton;
            }
        }
        return null;
    }

    private Button findButtonByCoord(int column, int row) {
        for (Node node : grillePane.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            if (columnIndex != null && rowIndex != null && rowIndex == row && columnIndex == column) {
                return (Button) node;
            }
        }
        return null;
    }

    private Rectangle createYellowRectangle(int size, Direction dir) {
        Rectangle rectangle = new Rectangle();
        if(dir==Direction.Sud || dir==Direction.Nord){
            rectangle.setWidth((double) this.pixelSize /2);
            rectangle.setHeight(this.pixelSize * size);
        }
        else if (dir==Direction.Est || dir==Direction.Ouest){
            rectangle.setHeight((double) this.pixelSize /2);
            rectangle.setWidth(this.pixelSize * size);
        }
        rectangle.setFill(Color.YELLOW);
        rectangle.toFront();
        return rectangle;
    }



    private void setNorthRectangleMouseEvents(Rectangle rectangle, Button finalButtonDestNord, Ile ileSrc, Ile ileNord, Button boutonSrc) {
        rectangle.setOnMouseEntered(event -> {
            this.onYellowBridge = Direction.Nord;
            if (rectangle.getFill() != Color.TRANSPARENT) finalButtonDestNord.setStyle("-fx-background-color: transparent;");
        });

        int height = ileSrc.getX() - ileNord.getX() - 1;
        Line line1 = new Line();
        Line line2 = new Line();
        rectangle.setOnMouseClicked(event -> {
            int valPontDirN = ((IleJoueur) ileSrc).getValPontDir("N");
            if (valPontDirN == 0) {
                grille.poserPont(ileSrc, ileNord);
                rectangle.setFill(Color.TRANSPARENT);

                line1.setStrokeWidth(2.0);
                line1.setStartX(rectangle.getX() + rectangle.getWidth() / 2);
                line1.setStartY(rectangle.getY());
                line1.setEndX(rectangle.getX() + rectangle.getWidth() / 2);
                line1.setEndY(rectangle.getY() + rectangle.getHeight());

                // ACTIONS LINE 1
                line1.setOnMouseClicked(event1 -> {
                    //S'il existe un pont vers l'ile au nord
                    if(((IleJoueur) ileSrc).getValPontDir("N") == 1){
                        //Si ni l'ile source ni l'ile au nord n'est complète
                        if(!ileSrc.ileComplete() && !ileNord.ileComplete()) {
                            grille.poserPont(ileSrc, ileNord);

                            line1.setTranslateX(-5);

                            createLine2(line1, line2);
                            line2.setTranslateX(5);
                            line2.setOnMouseClicked(event2 -> {
                                finalButtonDestNord.setStyle("-fx-background-color: transparent");
                                boutonSrc.setStyle("-fx-background-color: transparent");
                                grillePane.getChildren().removeAll(line1, line2, rectangle);
                                grille.poserPont(ileSrc, ileNord);

                                if (ileSrc.ileComplete()) {
                                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                                }
                                if (ileNord.ileComplete()) {
                                    finalButtonDestNord.setStyle("-fx-background-color: lightgrey;");
                                }
                            });

                            grillePane.add(line2, ileNord.getY(), ileNord.getX() + 1, 1, height);

                            if (ileSrc.ileComplete()) {
                                boutonSrc.setStyle("-fx-background-color: lightgrey;");
                            }
                            if (ileNord.ileComplete()) {
                                finalButtonDestNord.setStyle("-fx-background-color: lightgrey;");
                            }
                        }
                        else {
                            finalButtonDestNord.setStyle("-fx-background-color: transparent");
                            boutonSrc.setStyle("-fx-background-color: transparent");
                            grillePane.getChildren().removeAll(line1, rectangle);
                            grille.poserPont(ileSrc, ileNord);
                            grille.poserPont(ileSrc, ileNord);
                        }
                    }
                    //S'il existe un pont double vers l'ile du nord
                    else if(((IleJoueur) ileSrc).getValPontDir("N") == 2){
                        finalButtonDestNord.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileNord);
                    }
                });
                // FIN ACTION LINE 1

                grillePane.add(line1, ileNord.getY(), ileNord.getX() + 1, 1, height);
                if(ileSrc.ileComplete()){
                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                }
                if(ileNord.ileComplete()){
                    finalButtonDestNord.setStyle("-fx-background-color: lightgrey;");
                }
            } else if (valPontDirN == 1) {
                //Si ni l'ile source ni l'ile au nord n'est complète
                if(!ileSrc.ileComplete() && !ileNord.ileComplete()) {
                    grille.poserPont(ileSrc, ileNord);

                    line1.setTranslateX(-5);

                    createLine2(line1, line2);
                    line2.setTranslateX(5);

                    line2.setOnMouseClicked(event2 -> {
                        finalButtonDestNord.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileNord);
                    });

                    grillePane.add(line2, ileNord.getY(), ileNord.getX() + 1, 1, height);

                    if (ileSrc.ileComplete()) {
                        boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (ileNord.ileComplete()) {
                        finalButtonDestNord.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    finalButtonDestNord.setStyle("-fx-background-color: transparent");
                    boutonSrc.setStyle("-fx-background-color: transparent");
                    grillePane.getChildren().removeAll(line1, rectangle);
                    grille.poserPont(ileSrc, ileNord);
                    grille.poserPont(ileSrc, ileNord);
                    this.onYellowBridge = Direction.None;
                }
            } else if (valPontDirN == 2) {
                finalButtonDestNord.setStyle("-fx-background-color: transparent");
                boutonSrc.setStyle("-fx-background-color: transparent");
                grillePane.getChildren().removeAll(line1, line2, rectangle);
                grille.poserPont(ileSrc, ileNord);
                this.onYellowBridge = Direction.None;
            }
        });

        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                grillePane.getChildren().removeAll(rectangle);
                finalButtonDestNord.setStyle("-fx-background-color: transparent;");
            }
            this.onYellowBridge = Direction.None;
        });
    }


    /**
     * Méthode permettant la gestion du pont au Sud de l'ile source. Cette méthode affiche les ponts jaunes, les ponts simple, double et supprime les ponts.
     */
    private HashMap<Button, Rectangle> createSouthBridge(Ile ileSrc, Button boutonSrc) {
        Ile ileSud = ileSrc.getIleSud(grille);

        if (ileSud != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileSud) && !ileSud.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur)ileSrc).getValPontDir("S") == 0) {
            Button buttonDestSud = findButtonByCoord(ileSud.getY(), ileSud.getX());

            if (buttonDestSud != null) {
                buttonDestSud.setStyle("-fx-background-color: yellow;");
                int height = ileSud.getX() - ileSrc.getX() - 1;
                Rectangle rectangle = createYellowRectangle(height,Direction.Sud);

                setSouthRectangleMouseEvents(rectangle, buttonDestSud, ileSrc, ileSud, boutonSrc);

                grillePane.add(rectangle, ileSrc.getY(), ileSrc.getX() + 1, 1, height);
                HashMap<Button, Rectangle> southRectAndButton = new HashMap<>();
                southRectAndButton.put(buttonDestSud, rectangle);

                return southRectAndButton;
            }
        }
        return null;
    }

    private void setSouthRectangleMouseEvents(Rectangle rectangle, Button finalButtonDestSud, Ile ileSrc, Ile ileSud, Button boutonSrc) {
        rectangle.setOnMouseEntered(event -> {
            this.onYellowBridge = Direction.Sud;
            if (rectangle.getFill() != Color.TRANSPARENT) finalButtonDestSud.setStyle("-fx-background-color: transparent;");
        });

        int height = ileSud.getX() - ileSrc.getX() - 1;
        Line line1 = new Line();
        Line line2 = new Line();
        rectangle.setOnMouseClicked(event -> {
            int valPontDirN = ((IleJoueur) ileSrc).getValPontDir("S");
            if (valPontDirN == 0) {
                grille.poserPont(ileSrc, ileSud);
                rectangle.setFill(Color.TRANSPARENT);

                line1.setStrokeWidth(2.0);
                line1.setStartX(rectangle.getX() + rectangle.getWidth() / 2);
                line1.setStartY(rectangle.getY());
                line1.setEndX(rectangle.getX() + rectangle.getWidth() / 2);
                line1.setEndY(rectangle.getY() + rectangle.getHeight());

                // ACTIONS LINE 1
                line1.setOnMouseClicked(event1 -> {
                    //S'il existe un pont vers l'ile au nord
                    if(((IleJoueur) ileSrc).getValPontDir("S") == 1){
                        //Si ni l'ile source ni l'ile au nord n'est complète
                        if(!ileSrc.ileComplete() && !ileSud.ileComplete()) {
                            grille.poserPont(ileSrc, ileSud);

                            line1.setTranslateX(-5);

                            createLine2(line1, line2);
                            line2.setTranslateX(5);
                            line2.setOnMouseClicked(event2 -> {
                                finalButtonDestSud.setStyle("-fx-background-color: transparent");
                                boutonSrc.setStyle("-fx-background-color: transparent");
                                grillePane.getChildren().removeAll(line1, line2, rectangle);
                                grille.poserPont(ileSrc, ileSud);

                                if (ileSrc.ileComplete()) {
                                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                                }
                                if (ileSud.ileComplete()) {
                                    finalButtonDestSud.setStyle("-fx-background-color: lightgrey;");
                                }
                            });

                            grillePane.add(line2, ileSrc.getY(), ileSrc.getX() + 1, 1, height);

                            if (ileSrc.ileComplete()) {
                                boutonSrc.setStyle("-fx-background-color: lightgrey;");
                            }
                            if (ileSud.ileComplete()) {
                                finalButtonDestSud.setStyle("-fx-background-color: lightgrey;");
                            }
                        }
                        else {
                            finalButtonDestSud.setStyle("-fx-background-color: transparent");
                            boutonSrc.setStyle("-fx-background-color: transparent");
                            grillePane.getChildren().removeAll(line1, rectangle);
                            grille.poserPont(ileSrc, ileSud);
                            grille.poserPont(ileSrc, ileSud);
                        }
                    }
                    //S'il existe un pont double vers l'ile du nord
                    else if(((IleJoueur) ileSrc).getValPontDir("S") == 2){
                        finalButtonDestSud.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileSud);
                    }
                });
                // FIN ACTION LINE 1

                grillePane.add(line1, ileSrc.getY(), ileSrc.getX() + 1, 1, height);
                if(ileSrc.ileComplete()){
                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                }
                if(ileSud.ileComplete()){
                    finalButtonDestSud.setStyle("-fx-background-color: lightgrey;");
                }
            } else if (valPontDirN == 1) {
                //Si ni l'ile source ni l'ile au nord n'est complète
                if(!ileSrc.ileComplete() && !ileSud.ileComplete()) {
                    grille.poserPont(ileSrc, ileSud);

                    line1.setTranslateX(-5);

                    createLine2(line1, line2);
                    line2.setTranslateX(5);
                    line2.setOnMouseClicked(event2 -> {
                        finalButtonDestSud.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileSud);
                    });

                    grillePane.add(line2, ileSrc.getY(), ileSrc.getX() + 1, 1, height);

                    if (ileSrc.ileComplete()) {
                        boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (ileSud.ileComplete()) {
                        finalButtonDestSud.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    finalButtonDestSud.setStyle("-fx-background-color: transparent");
                    boutonSrc.setStyle("-fx-background-color: transparent");
                    grillePane.getChildren().removeAll(line1, rectangle);
                    grille.poserPont(ileSrc, ileSud);
                    grille.poserPont(ileSrc, ileSud);
                    this.onYellowBridge = Direction.None;
                }
            } else if (valPontDirN == 2) {
                finalButtonDestSud.setStyle("-fx-background-color: transparent");
                boutonSrc.setStyle("-fx-background-color: transparent");
                grillePane.getChildren().removeAll(line1, line2, rectangle);
                grille.poserPont(ileSrc, ileSud);
                this.onYellowBridge = Direction.None;
            }
        });

        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                grillePane.getChildren().removeAll(rectangle);
                finalButtonDestSud.setStyle("-fx-background-color: transparent;");
            }
            this.onYellowBridge = Direction.None;
        });
    }

    private HashMap<Button, Rectangle> createWestBridge(Ile ileSrc, Button boutonSrc) {
        Ile ileOuest = ileSrc.getIleOuest(grille);

        if (ileOuest != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileOuest) && !ileOuest.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur)ileSrc).getValPontDir("O") == 0) {
            Button buttonDestOuest = findButtonByCoord(ileOuest.getY(), ileOuest.getX());

            if (buttonDestOuest != null) {
                buttonDestOuest.setStyle("-fx-background-color: yellow;");
                int width = ileSrc.getY() - ileOuest.getY() - 1;
                Rectangle rectangle = createYellowRectangle(width,Direction.Ouest);

                setWestRectangleMouseEvents(rectangle, buttonDestOuest, ileSrc, ileOuest, boutonSrc);

                grillePane.add(rectangle, ileOuest.getY() + 1, ileOuest.getX(), width, 1);
                HashMap<Button, Rectangle> westRectAndButton = new HashMap<>();
                westRectAndButton.put(buttonDestOuest, rectangle);

                return westRectAndButton;
            }
        }
        return null;
    }

    private void setWestRectangleMouseEvents(Rectangle rectangle, Button finalButtonDestOuest, Ile ileSrc, Ile ileOuest, Button boutonSrc) {
        rectangle.setOnMouseEntered(event -> {
            this.onYellowBridge = Direction.Ouest;
            if (rectangle.getFill() != Color.TRANSPARENT) finalButtonDestOuest.setStyle("-fx-background-color: transparent;");
        });

        int width = ileSrc.getY() - ileOuest.getY() - 1;
        Line line1 = new Line();
        Line line2 = new Line();
        rectangle.setOnMouseClicked(event -> {
            int valPontDirN = ((IleJoueur) ileSrc).getValPontDir("O");
            if (valPontDirN == 0) {
                grille.poserPont(ileSrc, ileOuest);
                rectangle.setFill(Color.TRANSPARENT);

                line1.setStrokeWidth(2.0);
                line1.setStartX(rectangle.getX());
                line1.setStartY(rectangle.getY() + rectangle.getHeight()/2);
                line1.setEndX(rectangle.getX() + rectangle.getWidth());
                line1.setEndY(rectangle.getY() + rectangle.getHeight()/2);

                // ACTIONS LINE 1
                line1.setOnMouseClicked(event1 -> {
                    //S'il existe un pont vers l'ile au nord
                    if(((IleJoueur) ileSrc).getValPontDir("O") == 1){
                        //Si ni l'ile source ni l'ile au nord n'est complète
                        if(!ileSrc.ileComplete() && !ileOuest.ileComplete()) {
                            grille.poserPont(ileSrc, ileOuest);

                            line1.setTranslateY(-5);

                            createLine2(line1, line2);
                            line2.setTranslateY(5);
                            line2.setOnMouseClicked(event2 -> {
                                finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                                boutonSrc.setStyle("-fx-background-color: transparent");
                                grillePane.getChildren().removeAll(line1, line2, rectangle);
                                grille.poserPont(ileSrc, ileOuest);

                                if (ileSrc.ileComplete()) {
                                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                                }
                                if (ileOuest.ileComplete()) {
                                    finalButtonDestOuest.setStyle("-fx-background-color: lightgrey;");
                                }
                            });

                            grillePane.add(line2, ileOuest.getY()+1, ileOuest.getX(), width, 1);

                            if (ileSrc.ileComplete()) {
                                boutonSrc.setStyle("-fx-background-color: lightgrey;");
                            }
                            if (ileOuest.ileComplete()) {
                                finalButtonDestOuest.setStyle("-fx-background-color: lightgrey;");
                            }
                        }
                        else {
                            finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                            boutonSrc.setStyle("-fx-background-color: transparent");
                            grillePane.getChildren().removeAll(line1, rectangle);
                            grille.poserPont(ileSrc, ileOuest);
                            grille.poserPont(ileSrc, ileOuest);
                        }
                    }
                    //S'il existe un pont double vers l'ile du nord
                    else if(((IleJoueur) ileSrc).getValPontDir("O") == 2){
                        finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileOuest);
                    }
                });
                // FIN ACTION LINE 1

                grillePane.add(line1, ileOuest.getY()+1, ileOuest.getX(), width, 1);
                if(ileSrc.ileComplete()){
                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                }
                if(ileOuest.ileComplete()){
                    finalButtonDestOuest.setStyle("-fx-background-color: lightgrey;");
                }
            } else if (valPontDirN == 1) {
                //Si ni l'ile source ni l'ile au nord n'est complète
                if(!ileSrc.ileComplete() && !ileOuest.ileComplete()) {
                    grille.poserPont(ileSrc, ileOuest);
                    line1.setTranslateY(-5);

                    createLine2(line1, line2);
                    line2.setTranslateY(5);

                    line2.setOnMouseClicked(event2 -> {
                        finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileOuest);
                    });



                    grillePane.add(line2, ileOuest.getY() + 1, ileOuest.getX(), width, 1);

                    if (ileSrc.ileComplete()) {
                        boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (ileOuest.ileComplete()) {
                        finalButtonDestOuest.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                    boutonSrc.setStyle("-fx-background-color: transparent");
                    grillePane.getChildren().removeAll(line1, rectangle);
                    grille.poserPont(ileSrc, ileOuest);
                    grille.poserPont(ileSrc, ileOuest);
                    this.onYellowBridge = Direction.None;
                }
            } else if (valPontDirN == 2) {
                finalButtonDestOuest.setStyle("-fx-background-color: transparent");
                boutonSrc.setStyle("-fx-background-color: transparent");
                grillePane.getChildren().removeAll(line1, line2, rectangle);
                grille.poserPont(ileSrc, ileOuest);
                this.onYellowBridge = Direction.None;
            }
        });

        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                grillePane.getChildren().removeAll(rectangle);
                finalButtonDestOuest.setStyle("-fx-background-color: transparent;");
            }
            this.onYellowBridge = Direction.None;
        });
    }

    private HashMap<Button, Rectangle> createEastBridge(Ile ileSrc, Button boutonSrc) {
        Ile ileEst = ileSrc.getIleEst(grille);

        if (ileEst != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileEst) && !ileEst.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur)ileSrc).getValPontDir("E") == 0) {
            Button buttonDestEst = findButtonByCoord(ileEst.getY(), ileEst.getX());

            if (buttonDestEst != null) {
                buttonDestEst.setStyle("-fx-background-color: yellow;");
                int width = ileEst.getY() - ileSrc.getY() - 1;
                Rectangle rectangle = createYellowRectangle(width,Direction.Est);

                setEastRectangleMouseEvents(rectangle, buttonDestEst, ileSrc, ileEst, boutonSrc);

                grillePane.add(rectangle, ileSrc.getY() + 1, ileSrc.getX(), width, 1);
                HashMap<Button, Rectangle> eastRectAndButton = new HashMap<>();
                eastRectAndButton.put(buttonDestEst, rectangle);

                return eastRectAndButton;
            }
        }
        return null;
    }

    private void setEastRectangleMouseEvents(Rectangle rectangle, Button finalButtonDestEst, Ile ileSrc, Ile ileEst, Button boutonSrc) {
        rectangle.setOnMouseEntered(event -> {
            this.onYellowBridge = Direction.Est;
            if (rectangle.getFill() != Color.TRANSPARENT) finalButtonDestEst.setStyle("-fx-background-color: transparent;");
        });

        int width =  ileEst.getY() - ileSrc.getY() - 1;
        Line line1 = new Line();
        Line line2 = new Line();
        rectangle.setOnMouseClicked(event -> {
            int valPontDirN = ((IleJoueur) ileSrc).getValPontDir("E");
            if (valPontDirN == 0) {
                grille.poserPont(ileSrc, ileEst);
                rectangle.setFill(Color.TRANSPARENT);

                line1.setStrokeWidth(2.0);
                line1.setStartX(rectangle.getX());
                line1.setStartY(rectangle.getY() + rectangle.getHeight()/2);
                line1.setEndX(rectangle.getX() + rectangle.getWidth());
                line1.setEndY(rectangle.getY() + rectangle.getHeight()/2);

                // ACTIONS LINE 1
                line1.setOnMouseClicked(event1 -> {
                    //S'il existe un pont vers l'ile au nord
                    if(((IleJoueur) ileSrc).getValPontDir("E") == 1){
                        //Si ni l'ile source ni l'ile au nord n'est complète
                        if(!ileSrc.ileComplete() && !ileEst.ileComplete()) {
                            grille.poserPont(ileSrc, ileEst);

                            line1.setTranslateY(-5);

                            createLine2(line1, line2);
                            line2.setTranslateY(5);
                            line2.setOnMouseClicked(event2 -> {
                                finalButtonDestEst.setStyle("-fx-background-color: transparent");
                                boutonSrc.setStyle("-fx-background-color: transparent");
                                grillePane.getChildren().removeAll(line1, line2, rectangle);
                                grille.poserPont(ileSrc, ileEst);

                                if (ileSrc.ileComplete()) {
                                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                                }
                                if (ileEst.ileComplete()) {
                                    finalButtonDestEst.setStyle("-fx-background-color: lightgrey;");
                                }
                            });

                            grillePane.add(line2, ileSrc.getY()+1, ileSrc.getX(), width, 1);

                            if (ileSrc.ileComplete()) {
                                boutonSrc.setStyle("-fx-background-color: lightgrey;");
                            }
                            if (ileEst.ileComplete()) {
                                finalButtonDestEst.setStyle("-fx-background-color: lightgrey;");
                            }
                        }
                        else {
                            finalButtonDestEst.setStyle("-fx-background-color: transparent");
                            boutonSrc.setStyle("-fx-background-color: transparent");
                            grillePane.getChildren().removeAll(line1, rectangle);
                            grille.poserPont(ileSrc, ileEst);
                            grille.poserPont(ileSrc, ileEst);
                        }
                    }
                    //S'il existe un pont double vers l'ile du nord
                    else if(((IleJoueur) ileSrc).getValPontDir("E") == 2){
                        finalButtonDestEst.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileEst);
                    }
                });
                // FIN ACTION LINE 1

                grillePane.add(line1, ileSrc.getY()+1, ileSrc.getX(), width, 1);
                if(ileSrc.ileComplete()){
                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                }
                if(ileEst.ileComplete()){
                    finalButtonDestEst.setStyle("-fx-background-color: lightgrey;");
                }
            } else if (valPontDirN == 1) {
                //Si ni l'ile source ni l'ile au nord n'est complète
                if(!ileSrc.ileComplete() && !ileEst.ileComplete()) {
                    grille.poserPont(ileSrc, ileEst);
                    line1.setTranslateY(-5);

                    createLine2(line1, line2);
                    line2.setTranslateY(5);

                    line2.setOnMouseClicked(event2 -> {
                        finalButtonDestEst.setStyle("-fx-background-color: transparent");
                        boutonSrc.setStyle("-fx-background-color: transparent");
                        grillePane.getChildren().removeAll(line1, line2, rectangle);
                        grille.poserPont(ileSrc, ileEst);
                    });

                    grillePane.add(line2, ileSrc.getY() + 1, ileSrc.getX(), width, 1);

                    if (ileSrc.ileComplete()) {
                        boutonSrc.setStyle("-fx-background-color: lightgrey;");
                    }
                    if (ileEst.ileComplete()) {
                        finalButtonDestEst.setStyle("-fx-background-color: lightgrey;");
                    }
                }
                else{
                    finalButtonDestEst.setStyle("-fx-background-color: transparent");
                    boutonSrc.setStyle("-fx-background-color: transparent");
                    grillePane.getChildren().removeAll(line1, rectangle);
                    grille.poserPont(ileSrc, ileEst);
                    grille.poserPont(ileSrc, ileEst);
                    this.onYellowBridge = Direction.None;
                }
            } else if (valPontDirN == 2) {
                finalButtonDestEst.setStyle("-fx-background-color: transparent");
                boutonSrc.setStyle("-fx-background-color: transparent");
                grillePane.getChildren().removeAll(line1, line2, rectangle);
                grille.poserPont(ileSrc, ileEst);
                this.onYellowBridge = Direction.None;
            }
        });

        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                grillePane.getChildren().removeAll(rectangle);
                finalButtonDestEst.setStyle("-fx-background-color: transparent;");
            }
            this.onYellowBridge = Direction.None;
        });
    }

    private void deleteBridges() {
        System.out.println(onYellowBridge);

        /*
        if(onYellowBridge!=Direction.None){
            rectFromMousedIle.remove(onYellowBridge);
        }
         */

        List<Node> listObj = grillePane.getChildren();
        for (Rectangle rec : rectFromMousedIle.get(onYellowBridge).values()) {
            if(rec!=null){
                listObj.remove(rec);
            }
        }


        for (Node node: listObj) {
            if(node instanceof Button){
                node.setStyle("-fx-background-color: transparent;");
            }
            else if(node instanceof Rectangle && ((Rectangle) node).getFill()==Color.YELLOW){
                grillePane.getChildren().removeAll(node);
            }
        }

    }

/*
    public boolean partieFinie(){
        for (Node node: grillePane.getChildren()) {
            if(node instanceof Button){
                if(node)
            }
        }
        return true;
    }

 */

    @FXML
    public void initialize() {
        initButtons();
        initChrono();
        onYellowBridge = Direction.None;

        this.grille = new GrilleJeu("./src/main/java/com/example/demojeumenu/niveaux/facile/Facile-2.txt");
        System.out.print(this.grille.getNbColonne() + " - " + this.grille.getNbLigne());
        if (this.grille.getNbColonne() < 10 && this.grille.getNbLigne() < 10) {
            this.fontSize = 15;
            this.pixelSize = 50;
        } else {
            this.fontSize = 7;
            this.pixelSize = 25;
        }

        grillePane.setPrefSize(this.pixelSize * grille.getNbColonne(), this.pixelSize * grille.getNbLigne());
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

        for (int i = 0; i < this.grille.getNbLigne(); i++) {
            for (int j = 0; j < this.grille.getNbColonne(); j++) {
                if (grille.getIleGrilleJoueur(i, j) != null) {
                    Button button = createButton(this.grille.getIleGrilleJoueur(i, j), i, j);
                    button.toFront();
                    int J = j;
                    int I = i;
                    button.setOnMouseEntered(event -> {
                        createBridges(button, I, J);
                    });

                    button.setOnMouseExited(event -> {
                        Timeline timelineDel = new Timeline(new KeyFrame(Duration.seconds(0.05), eventDel -> {
                            deleteBridges();
                        }));
                        timelineDel.play();
                    });

                    grillePane.getChildren().add(button);
                    GridPane.setColumnIndex(button, j);
                    GridPane.setRowIndex(button, i);
                }
            }
        }
        grillePane.toFront();

        startChrono();
    }
}
