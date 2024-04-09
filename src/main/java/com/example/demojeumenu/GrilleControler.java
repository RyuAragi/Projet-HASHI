package com.example.demojeumenu;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrilleControler extends BaseController {
    /**
     * [Integer] Valeur du niveau d'aide (1,2 ou 3).
     */
    private int niveau_aide;

    /**
     * [GrilleJeu] Référence vers la grille actuelle.
     */
    private GrilleJeu grille;

    /**
     * [Integer] Taille des cellules de la grille
     */
    private int pixelSize;

    /**
     * [Boolean] Indicateur pour vérifier si le bouton hypothèse est cliqué.
     */
    private boolean enModeHypothese;

    /**
     * [Timeline] Timer du jeu
     */
    private static Timeline timeline;

    /**
     * [HBox] Element graphique (Horizontal Box) contenant les boutons valider, vérifier, undo, redo, restart et hypothese.
     */
    @FXML
    public HBox hbox_bouton_HD;

    /**
     * [HBox] Element graphique (Horizontal Box) contenant les boutons réglages et quitter
     */
    @FXML
    public HBox hbox_bouton_HG;

    /**
     * [HBox] Element graphique (Horizontal Box) contenant les boutons zoom et dezoom
     */
    @FXML
    public HBox hbox_bouton_BD;

    /**
     * [VBox] Element graphique (Vertical Box) contenant les elements d'information concernant l'aide cad le label textInfo, et les boutons de suppression de l'aide, de visualidsation de la technique et visualisation de l'aide suivante.
     */
    @FXML
    public VBox vbox_aide_info;

    /**
     * [Label] Element graphique contenant le texte de l'aide au joueur.
     */
    @FXML
    public Label textInfo;

    /**
     * [Button] Bouton permettant d'enlever l'affichage de la technique de l'interface graphique
     */
    @FXML
    public Button ok_button;

    /**
     * [Button] Bouton permettant d'afficher l'aide de niveau suivant. (exemple: 1->2, 2->3)
     */
    @FXML
    public Button next_clue;

    /**
     * [Button] Bouton permettant d'afficher la technique.
     */
    @FXML
    public Button see_tech;

    /**
     * [Button] Bouton permettant de revenir au menu des niveau.
     */
    @FXML
    private Button quit;

    /**
     * [Button] Bouton permettant d'aller au menu des paramètres.
     */
    @FXML
    private Button param;

    /**
     * [Label] Element graphique contenant le chrono de manière textuelle.
     */
    @FXML
    private Label chrono;

    /**
     * [Button] Bouton d'agrandissement de la grille.
     */
    @FXML
    private Button zoom;

    /**
     * [Button] Bouton de rétrécissement de la grille.
     */
    @FXML
    private Button dezoom;

    /**
     * [Button] Bouton de réinitialisation de la grille.
     */
    @FXML
    private Button restart;

    /**
     * [Button] Bouton d'annulation de la dernière action réalisée sur la grille
     */
    @FXML
    private Button undo;

    /**
     * [Button] Bouton d'annulation de l'annulation de la dernière action réalisée sur la grille.
     */
    @FXML
    private Button redo;

    /**
     * [Button] Bouton permettant de vérifier la grille que le joueur a complété.
     */
    @FXML
    private Button check;

    /**
     * [Button] Bouton d'affichage de l'aide au joueur.
     */
    @FXML
    private Button help;

    /**
     * [Button] Bouton d'activation/désactivation du mode hypothèse.
     */
    @FXML
    private Button hypothese;

    /**
     * [Button] Bouton de validation des hypothèses.
     */
    @FXML
    private Button valid_hypo;

    /**
     * [Button] Bouton de suppression des hypothèses.
     */
    @FXML
    private Button supp_hypo;

    /**
     * [GridPane] Element graphique permettant l'affichage de la grille à l'écran.
     */
    @FXML
    private GridPane grillePane;

    /**
     * Méthode d'incrémentation du chrono et change l'affichage de celui-ci
     */
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

    /**
     * Méthode d'initialisation du chrono
     */
    private void initChrono() {
        chrono.setText("00:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Appeler la méthode pour incrémenter le chrono chaque seconde
            incrementeChrono();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Pour répéter indéfiniment
    }

    /**
     * Méthode de démarrage du chrono
     */
    private void startChrono() {
        timeline.play();
    }

    /**
     * Méthode d'arrêt du chrono.
     */
    private void stopChrono() {
        timeline.stop();
    }

    /**
     * Méthode retournant le chrono actuel.
     * @return [Timeline] le chronomètre.
     */
    public static Timeline getChrono() {
        return timeline;
    }

    /**
     * Méthode création d'un bouton ile qui sera inséré dans la grille visuelle.
     * @param ile Ile dans la grille du back-end.
     * @param fontSize Taille de la police.
     * @return [Button] Bouton qui est créé.
     */
    private Button createButton(Ile ile, int fontSize) {
        Button boutonIle = new Button(ile.getValIle() + "");
        boutonIle.toFront();
        boutonIle.getStyleClass().add("boutonIle");
        boutonIle.setStyle("-fx-font-size: "+ fontSize +"px;");
        boutonIle.setPrefSize(this.pixelSize, this.pixelSize);

        return boutonIle;
    }

    /**
     * Méthode de validation d'hypothèses.
     */
    @FXML
    private void validation_hypotheses() {
        System.out.println("test valid");
    }

    /**
     * Méthode de suppression des hypothèses.
     */
    @FXML
    private void suppression_hypotheses() {
        System.out.println("test supp");
    }

    /**
     * Méthode d'affichage de l'aide au joueur.
     */
    @FXML
    private void helpMethod() {
        //Mettre ici condition pour vérifier niveau d'aide
        Rectangle zoneSolution;
        Ile ileSolution = new IleJoueur(2, 5, 3);

        vbox_aide_info.setVisible(true);
        vbox_aide_info.setDisable(false);
        vbox_aide_info.toFront();

        if(niveau_aide==1 || niveau_aide==2) {
            int mid_X = grille.getNbLigne() / 2;
            int mid_Y = grille.getNbColonne() / 2;
            if (grille.getNbLigne() % 2 == 1) {
                mid_X++;
            }
            if (grille.getNbColonne() % 2 == 1) {
                mid_Y++;
            }
            System.out.println("mid : " + mid_X + " - " + mid_Y);


            zoneSolution = new Rectangle();
            zoneSolution.setArcWidth(20);
            zoneSolution.setArcHeight(20);
            zoneSolution.toBack();
            zoneSolution.setStrokeWidth(4.0);
            zoneSolution.setStroke(Color.valueOf("#BC1C58"));
            zoneSolution.setFill(Color.TRANSPARENT);
            zoneSolution.setWidth((grille.getNbColonne() - mid_Y) * this.pixelSize);
            zoneSolution.setHeight((grille.getNbLigne() - mid_X) * this.pixelSize);

            System.out.println("Ile sol : " + ileSolution.getX() + " - " + ileSolution.getY());
            if (ileSolution.getX() < mid_X && ileSolution.getY() < mid_Y) {
                //Coin haut gauche
                zoneSolution.setX(0);
                zoneSolution.setY(0);
            } else if (ileSolution.getX() < mid_X && ileSolution.getY() >= mid_Y) {
                //coin haut droit
                zoneSolution.setX(0);
                zoneSolution.setY(mid_Y);
            } else if (ileSolution.getX() >= mid_X && ileSolution.getY() < mid_Y) {
                //coin bas gauche
                zoneSolution.setX(mid_X);
                zoneSolution.setY(0);
            } else {
                //coin bas droit
                zoneSolution.setX(mid_X);
                zoneSolution.setY(mid_Y);
            }
            System.out.println(zoneSolution.getX() + " " + zoneSolution.getY());
            grillePane.add(zoneSolution, (int) zoneSolution.getY(), (int) zoneSolution.getX(), (int) grille.getNbColonne() - mid_Y, (int) grille.getNbLigne() - mid_X);


            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(5), zoneSolution);
            fadeTransition1.setFromValue(1.0);
            fadeTransition1.setToValue(0.0);
            fadeTransition1.setOnFinished(event -> {
                grillePane.getChildren().remove(zoneSolution); // Supprimer le rectangle après la disparition
            });

            if(niveau_aide==1){
                textInfo.setText("Une technique peut être appliquée dans cette zone !");
                see_tech.setVisible(false);
                see_tech.setDisable(true);
            }
            if(niveau_aide==2){
                textInfo.setText("La technique ... est applicable dans cette zone !");
                see_tech.setVisible(true);
                see_tech.setDisable(false);
            }
            ok_button.setOnMouseClicked(getOkButtonAction(fadeTransition1));
            next_clue.setOnMouseClicked(getNextClueAction(zoneSolution));
        }
        else if(niveau_aide==3){
            textInfo.setText("La technique ... est applicable dans cette ile !");
            next_clue.setDisable(true);
            next_clue.setVisible(false);

            Circle ileCercle = new Circle();
            ileCercle.setRadius((double) this.pixelSize /2);
            ileCercle.setStrokeWidth(4.0);
            ileCercle.setStroke(Color.valueOf("#BC1C58"));
            ileCercle.setFill(Color.TRANSPARENT);
            grillePane.add(ileCercle, ileSolution.getY(), ileSolution.getX(), 1, 1);

            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(5), ileCercle);
            fadeTransition2.setFromValue(1.0);
            fadeTransition2.setToValue(0.0);
            fadeTransition2.setOnFinished(event -> {
                grillePane.getChildren().remove(ileCercle);
            });
            ok_button.setOnMouseClicked(getOkButtonAction(fadeTransition2));
        }
    }


    /**
     * Méthode retournant l'action du bouton permettant d'accéder à l'aide suivante.
     * @param zoneSolution Rectangle de la zone contenant l'ile sur laquel une technique est applicable
     * @return [EventHandler<Event>] Action du bouton.
     */
    private EventHandler<Event> getNextClueAction(Rectangle zoneSolution){
        return event -> {
            vbox_aide_info.setVisible(false);
            grillePane.getChildren().remove(zoneSolution);
            niveau_aide++;
            helpMethod();
        };
    }

    /**
     * Méthode retournant l'action du bouton permettant de ne plus afficher l'aide.
     * @param fadeTransition Effet de disparition du rectangle de la zone-solution ou de l'ile-solution.
     * @return [EventHandler<Event>] Action du bouton.
     */
    private EventHandler<Event> getOkButtonAction(FadeTransition fadeTransition){
        return event -> {
            fadeTransition.play();
            vbox_aide_info.setVisible(false);
        };
    }

    /**
     * Méthode retournant l'action du bouton permettant l'affichage de la technique à utiliser.
     * @return [EventHandler<Event>] Action du bouton.
     */
    private EventHandler<Event> getTechniqueAction(){
        return event -> {
            // A compléter avec l'affichage de la technique adéquate.
            //FXMLUtils.loadFXML();
        };
    }

    /**
     * Méthode d'activation et désactivation du mode hypothèse.
     */
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

    /**
     * Méthode d'initialisation de boutons et d'éléments graphiques.
     */
    private void initButtons() {
        this.enModeHypothese = false;

        hbox_bouton_HD.toFront();

        valid_hypo.setDisable(true);
        valid_hypo.setVisible(false);

        supp_hypo.setDisable(true);
        supp_hypo.setVisible(false);

        vbox_aide_info.setDisable(true);
        vbox_aide_info.setVisible(false);
    }

    /**
     * Méthode de recherche d'un bouton ile en fonction des coordonnées de l'ile source.
     * @param column Colonne sur laquelle se trouve l'ile source.
     * @param row Ligne sur laquelle se trouve l'ile source.
     * @return [Button] Le bouton destination au nord.
     */
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

    /**
     * Méthode permettant de créer des ponts en surbrillance (ponts possibles) vers les iles voisines
     * @param buttonSrc Bouton sur lequel la souris se trouve
     * @param row Ligne sur laquelle se trouve l'ile
     * @param col Colonne sur laquelle se trouve l'ile
     */
    private void createBridges(Button buttonSrc, int row, int col) {
        Ile ileSrc = grille.getIleGrilleJoueur(row, col);
        creerPontPossibleNord(ileSrc, buttonSrc);
        creerPontPossibleSud(ileSrc, buttonSrc);
        creerPontPossibleOuest(ileSrc, buttonSrc);
        creerPontPossibleEst(ileSrc, buttonSrc);
    }

    /**
     * Méthode de creation du rectangle de pont possible au nord avec actions associées (= pose de pont simple et double)
     * @param ileSrc Ile source de la grille du backend
     * @param boutonSrc Bouton ile source du frontend
     */
    private void creerPontPossibleNord(Ile ileSrc, Button boutonSrc) {
        Ile ileNord = ileSrc.getIleNord(grille);

        if (ileNord != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileNord) && !ileNord.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur) ileSrc).getValPontDir("N") == 0) {
            Button buttonDestNord = findButtonByCoord(ileNord.getY(), ileNord.getX());

            if (buttonDestNord != null) {
                buttonDestNord.setStyle("-fx-background-color: #F7ECB8;");
                int height = ileSrc.getX() - ileNord.getX() - 1;
                RectPontPossible pontNord = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonSrc, buttonDestNord, ileSrc, ileNord, "N" );
                pontNord.addToGridPane();
            }
        }
    }

    /**
     * Méthode permettant la gestion du pont au Sud de l'ile source. Cette méthode affiche les ponts jaunes, les ponts simple, double et supprime les ponts.
     */
    private void creerPontPossibleSud(Ile ileSrc, Button boutonSrc) {
        Ile ileSud = ileSrc.getIleSud(grille);

        if (ileSud != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileSud) && !ileSud.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur) ileSrc).getValPontDir("S") == 0) {
            Button buttonDestSud = findButtonByCoord(ileSud.getY(), ileSud.getX());

            if (buttonDestSud != null) {
                buttonDestSud.setStyle("-fx-background-color: #F7ECB8;");
                int height = ileSud.getX() - ileSrc.getX() - 1;
                RectPontPossible pontSud = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonSrc, buttonDestSud, ileSrc, ileSud, "S" );
                pontSud.addToGridPane();
            }
        }
    }

    private void creerPontPossibleOuest(Ile ileSrc, Button boutonSrc) {
        Ile ileOuest = ileSrc.getIleOuest(grille);

        if (ileOuest != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileOuest) && !ileOuest.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur) ileSrc).getValPontDir("O") == 0) {
            Button buttonDestOuest = findButtonByCoord(ileOuest.getY(), ileOuest.getX());

            if (buttonDestOuest != null) {
                buttonDestOuest.setStyle("-fx-background-color: #F7ECB8;");
                int width = ileSrc.getY() - ileOuest.getY() - 1;
                RectPontPossible pontOuest = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonSrc, buttonDestOuest, ileSrc, ileOuest, "O" );
                pontOuest.addToGridPane();
            }
        }
    }

    private void creerPontPossibleEst(Ile ileSrc, Button boutonSrc) {
        Ile ileEst = ileSrc.getIleEst(grille);

        if (ileEst != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileEst) && !ileEst.ileComplete() && !ileSrc.ileComplete() && ((IleJoueur) ileSrc).getValPontDir("E") == 0) {
            Button buttonDestEst = findButtonByCoord(ileEst.getY(), ileEst.getX());

            if (buttonDestEst != null) {
                buttonDestEst.setStyle("-fx-background-color: #F7ECB8;");
                int width = ileEst.getY() - ileSrc.getY() - 1;
                RectPontPossible pontEst = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonSrc, buttonDestEst, ileSrc, ileEst, "E" );
                pontEst.addToGridPane();
            }
        }
    }

    private void simulerRectPontPossible(Button boutonSrc){
        MouseEvent mouseEvent = new MouseEvent(
            MouseEvent.MOUSE_ENTERED,
            0, 0, 0, 0, MouseButton.PRIMARY, 1,
            true, true, true, true, true, true, true, true, true, true, null
        );

        boutonSrc.fireEvent(mouseEvent);
    }

    /**
     * Méthode de chargement de la grille enregistrée.
     */
    /*
    private void chargeGrille(){
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                Ile ile = grille.getIleGrilleJoueur(i,j);
                if(ile.getValPontDir("N")==1){

                }
                if(ile.getValPontDir("N")==2){

                }
            }
        }
    }

     */

    /**
     * Méthode de suppression des ponts possibles.
     */
    private void deleteBridges(){
        List<RectPontPossible> nodesToRemove = new ArrayList<>();
        for (Node node: grillePane.getChildren()) {
            if(node instanceof RectPontPossible && !((RectPontPossible) node).getFill().equals(Color.TRANSPARENT)){
                nodesToRemove.add((RectPontPossible)node);
            } else if (node instanceof Button && !node.getStyle().contains("-fx-background-color: lightgrey;")) {
                node.setStyle("-fx-background-color: transparent");
            }
        }

        for (RectPontPossible node : nodesToRemove) {
            node.removeFromGridPane(grillePane);
        }
    }

    /**
     * Méthode d'initialisation de la grille et d'appel des autres méthodes d'initialisation
     */
    @FXML
    public void initialize() {
        initButtons();
        initChrono();
        niveau_aide = 1;

        this.grille = new GrilleJeu("./src/main/java/com/example/demojeumenu/niveaux/facile/Facile-2.txt");
        System.out.print(this.grille.getNbColonne() + " - " + this.grille.getNbLigne());

        int fontSize;
        if (this.grille.getNbColonne() < 10 && this.grille.getNbLigne() < 10) {
            fontSize = 15;
            this.pixelSize = 50;
        } else {
            fontSize = 7;
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
                    Button button = createButton(this.grille.getIleGrilleJoueur(i, j), fontSize);
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
