package com.example.demojeumenu;

import com.example.demojeumenu.Technique.TechniqueInter;
import com.example.demojeumenu.controler.GlobalVariables;
import com.example.demojeumenu.controler.PopupWindowController;
import com.example.demojeumenu.game.*;
import com.example.demojeumenu.undoRedo.UndoRedo;
import com.example.demojeumenu.utils.BaseController;
import com.example.demojeumenu.Aide.AideManager;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.*;

@Controller
public class GrilleControler extends BaseController {

    /**
     * [String] Nom (+chemin) du fichier chargé de la grille.
     */
    public static String loadedFile;

    /**
     * [Integer] niveau de zoom de la grille
     */
    private int zoom_level;

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
    public static boolean enModeHypothese;

    /**
     * [Timeline] Timer du jeu
     */
    private static Timeline timeline;

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



    private static Duration duration;


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
        duration = Duration.ZERO;
        chrono.setText("00:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            duration = duration.add(Duration.seconds(1));
            // Appeler la méthode pour incrémenter le chrono chaque seconde
            incrementeChrono();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Pour répéter indéfiniment
    }

    /**
     * Méthode de démarrage du chrono
     */
    public static void startChrono() {
        timeline.play();
    }

    /**
     * Méthode d'arrêt du chrono.
     */
    public static void stopChrono() {
        timeline.stop();
    }

    /**
     * Méthode de récupération du temps du chrono
     */
    public static String getChronoTime() {
        long seconds = (long) duration.toSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Méthode création d'un bouton ile qui sera inséré dans la grille visuelle.
     * @param ile Ile dans la grille du back-end.
     * @param fontSize Taille de la police.
     * @return [Button] Bouton qui est créé.
     */
    private Button createButton(Ile ile, int fontSize) {
        Button boutonIle = new Button(ile.getValIle() + "");
        boutonIle.getStyleClass().add("boutonIle");
        boutonIle.getStyleClass().add("-fx-font-size: "+ fontSize +"px;");
        boutonIle.setPrefSize(this.pixelSize, this.pixelSize);

        return boutonIle;
    }

    private void verification_grille(){
        ArrayList<List<Pont>> pontsInccorects = grille.getPontsIncorrects();
        vbox_aide_info.setVisible(true);
        vbox_aide_info.setDisable(false);
        if(pontsInccorects.isEmpty()){
            textInfo.setText("Aucune erreur trouvé ! Félicitation vous tenez le bon bout !");
            ok_button.setVisible(true);
            ok_button.setDisable(false);
            see_tech.setVisible(false);
            next_clue.setVisible(false);
        }
        else{
            for (List<Pont> lp: pontsInccorects) {
                for (Node node : grillePane.getChildren()) {
                    if (node instanceof RectPontPossible rect && rect.ileSrc == lp.get(0).getSrc() && rect.ileDest == lp.get(0).getDst()) {
                        rect.line1.toRed();
                        if (rect.line2 != null) {
                            rect.line2.toRed();
                        }
                    }
                }
            }
            textInfo.setText(pontsInccorects.size()+" pont erronés ! Souhaitez-vous les supprimer ?");
            ok_button.setVisible(false);
            ok_button.setDisable(true);
            see_tech.setVisible(true);
            see_tech.setDisable(false);
            next_clue.setVisible(true);
            next_clue.setDisable(false);

            see_tech.setText("Non");
            next_clue.setText("Oui");

            EventHandler<? super MouseEvent> see_tech_act = see_tech.getOnMouseClicked();
            see_tech.setOnMouseClicked(event -> {
                vbox_aide_info.setVisible(false);
                vbox_aide_info.setDisable(true);

                for (Node node: grillePane.getChildren()) {
                    if(node instanceof LignePont){
                        ((LignePont)node).toBlack();
                    }
                }
                see_tech.setOnMouseClicked(see_tech_act);
            });

            EventHandler<? super MouseEvent> next_clue_act = next_clue.getOnMouseClicked();
            next_clue.setOnMouseClicked(event -> {
                vbox_aide_info.setVisible(false);
                vbox_aide_info.setDisable(true);

                for (List<Pont> lp :pontsInccorects) {
                    IleJoueur ileSrc = lp.get(0).getSrc();
                    IleJoueur ileDest = lp.get(0).getDst();

                    ileSrc.reinitPont(ileSrc.getPontDirection(lp.get(0)));
                    ileDest.reinitPont(ileDest.getPontDirection(lp.get(0)));

                    grille.supprimePont(lp);
                }


                ArrayList<RectPontPossible> rectToRemove = new ArrayList<>();
                for (Node node:grillePane.getChildren()) {
                    if(node instanceof LignePont && ((LignePont)node).getStroke()==Color.RED){
                        rectToRemove.add(((LignePont)node).pontPossible);
                    }
                }
                for (RectPontPossible rect:rectToRemove) {
                    rect.removeFromGridPane(grillePane);
                }

                next_clue.setOnMouseClicked(next_clue_act);
            });

        }
    }

    private void undoMethod(){
        UndoRedo undoRedo = grille.getUndoRedo();
        Pont pont = undoRedo.actionUndo(grille);
        if(pont!=null) {
            IleJoueur src = pont.getSrc();
            IleJoueur dest = pont.getDst();
            RectPontPossible rect = getPontParIles(src, dest);
            if(src.getValIle() != 1 && dest.getValIle() != 1) {
                if (rect.estDoublePont()) {
                    rect.simulerClick();
                    RectPontPossible rect1 = new RectPontPossible(grille, grillePane, (int) rect.getWidth(), (int) rect.getHeight(), rect.boutonSrc, rect.boutonDest, rect.ileSrc, rect.ileDest, rect.dir, rect.hypothese);
                    rect1.addToGridPane();
                    rect1.simulerClick();
                } else {
                    rect.simulerClick();
                    rect.simulerClick();
                }
            }

            else {
                if (rect == null) {
                    RectPontPossible rect1 = new RectPontPossible(grille, grillePane, (int) rect.getWidth(), (int) rect.getHeight(), rect.boutonSrc, rect.boutonDest, rect.ileSrc, rect.ileDest, rect.dir, rect.hypothese);
                    rect1.addToGridPane();
                    rect1.simulerClick();
                }
                else {
                    rect.simulerClick();
                }
            }
        }
    }

    public void redoMethod() {
        UndoRedo undoRedo = grille.getUndoRedo();
        Pont pont = undoRedo.actionRedo(grille);
        if(pont!=null) {
            IleJoueur src = pont.getSrc();
            IleJoueur dest = pont.getDst();

            RectPontPossible rect = getPontParIles(src, dest);
            if (rect != null) {
                rect.simulerClick();
            }
            else{
                RectPontPossible rect1 = null;
                if(src.getIleNord(grille) == dest){
                    rect1 = creerPontPossibleNord(src, findButtonByCoord(src.getX(),src.getY()));
                }
                else if(src.getIleSud(grille) == dest){
                    rect1 = creerPontPossibleSud(src, findButtonByCoord(src.getX(),src.getY()));
                }
                else if(src.getIleOuest(grille) == dest){
                    rect1 = creerPontPossibleOuest(src, findButtonByCoord(src.getX(),src.getY()));
                }
                else if(src.getIleEst(grille) == dest){
                    rect1 = creerPontPossibleEst(src, findButtonByCoord(src.getX(),src.getY()));
                }
                if(rect1!=null) {
                    rect1.simulerClick();
                }
            }
        }
    }



    public RectPontPossible getPontParIles(Ile ileSrc, Ile ileDest){
        for (Node node: grillePane.getChildren()) {
            if(node instanceof RectPontPossible rect && ((rect.ileSrc==ileSrc && rect.ileDest==ileDest) || (rect.ileSrc==ileDest && ileDest==ileSrc))){
                return rect;
            }
        }
        return null;
    }


    /**
     * Méthode de validation d'hypothèses.
     */
    private void validation_hypotheses() {
        for (Node node: grillePane.getChildren()) {
            if(node instanceof RectPontPossible && ((RectPontPossible)node).hypothese){
                ((RectPontPossible)node).line1.toBlack();
                if(((RectPontPossible)node).line2!=null){
                    ((RectPontPossible)node).line2.toBlack();
                }
                ((RectPontPossible)node).hypothese = false;
            }
        }
        grille.valideHypothese();
        hypotheseMethod();
    }

    /**
     * Méthode de suppression des hypothèses.
     */
    private void suppression_hypotheses() {
        ArrayList<RectPontPossible> nodesToRemove = new ArrayList<>();
        for (Node node: grillePane.getChildren()) {
            if(node instanceof RectPontPossible && ((RectPontPossible)node).hypothese){
                nodesToRemove.add((RectPontPossible)node);
            }
        }
        grille.quitteHypothese();
        for (RectPontPossible rect: nodesToRemove) {
            if(!rect.ileSrc.ileComplete()){
                rect.boutonSrc.setStyle("-fx-background-color: transparent;");
            }
            if(!rect.ileDest.ileComplete()){
                rect.boutonDest.setStyle("-fx-background-color: transparent;");
            }
            rect.removeFromGridPane(grillePane);
        }
        hypotheseMethod();
    }

    /**
     * Méthode d'affichage de l'aide au joueur.
     */
    private void helpMethod() {
        Rectangle zoneSolution;
        AideManager aideManager = AideManager.getInstance();
        aideManager.detecte(grille);

        int niveau_aide = aideManager.getPrecision();
        System.out.println("niveau_aide : " + niveau_aide);
        TechniqueInter technique = aideManager.getTechnique();

        if(technique!=null) {
            System.out.println(technique);
            Ile ileSolution = technique.getIle();

            vbox_aide_info.setVisible(true);
            vbox_aide_info.setDisable(false);
            vbox_aide_info.toFront();

            if (niveau_aide == 1 || niveau_aide == 2) {
                int mid_X = grille.getNbLigne() / 2;
                int mid_Y = grille.getNbColonne() / 2;
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
                grillePane.add(zoneSolution, (int) zoneSolution.getY(), (int) zoneSolution.getX(), grille.getNbColonne() - mid_Y, grille.getNbLigne() - mid_X);


                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2), zoneSolution);
                fadeTransition1.setFromValue(1.0);
                fadeTransition1.setToValue(0.0);
                fadeTransition1.setOnFinished(event -> {
                    grillePane.getChildren().remove(zoneSolution); // Supprimer le rectangle après la disparition
                });

                next_clue.setVisible(true);
                next_clue.setDisable(false);
                if (niveau_aide == 1) {
                    textInfo.setText("Une technique peut être appliquée dans cette zone !");
                    textInfo.setWrapText(true);
                    see_tech.setVisible(false);
                    see_tech.setDisable(true);
                }
                if (niveau_aide == 2) {
                    textInfo.setText(technique.getNomTechnique() + " est applicable dans cette zone !");
                    see_tech.setVisible(true);
                    see_tech.setDisable(false);
                }
                ok_button.setOnMouseClicked(getOkButtonAction(aideManager, fadeTransition1));
                next_clue.setOnMouseClicked(getNextClueAction(zoneSolution));
                see_tech.setOnMouseClicked(getTechniqueAction(technique));
            } else if (niveau_aide == 3) {
                textInfo.setText(technique.getNomTechnique() + " est applicable dans cette ile !");
                next_clue.setDisable(true);
                next_clue.setVisible(false);

                Circle ileCercle = new Circle();
                ileCercle.setRadius((double) this.pixelSize / 2);
                ileCercle.setStrokeWidth(4.0);
                ileCercle.setStroke(Color.valueOf("#BC1C58"));
                ileCercle.setFill(Color.TRANSPARENT);
                grillePane.add(ileCercle, ileSolution.getY(), ileSolution.getX(), 1, 1);

                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), ileCercle);
                fadeTransition2.setFromValue(1.0);
                fadeTransition2.setToValue(0.0);
                fadeTransition2.setOnFinished(event -> grillePane.getChildren().remove(ileCercle));
                ok_button.setOnMouseClicked(getOkButtonAction(aideManager, fadeTransition2));
                see_tech.setOnMouseClicked(getTechniqueAction(technique));
            }
        }
        else{
            textInfo.setText("Aucune technique n'a été detecté dans cette configuration !");
            next_clue.setDisable(true);
            next_clue.setVisible(false);
            see_tech.setDisable(true);
            see_tech.setVisible(false);
            ok_button.setOnMouseClicked(event -> vbox_aide_info.setVisible(false));
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

            helpMethod();
        };
    }

    /**
     * Méthode retournant l'action du bouton permettant de ne plus afficher l'aide.
     * @param aide Aide fournie par l'aide manageur
     * @param fadeTransition Effet de disparition du rectangle de la zone-solution ou de l'ile-solution.
     * @return [EventHandler<Event>] Action du bouton.
     */
    private EventHandler<Event> getOkButtonAction(AideManager aide, FadeTransition fadeTransition){
        return event -> {
            aide.decrementePrecision();
            fadeTransition.play();
            vbox_aide_info.setVisible(false);
        };
    }

    /**
     * Méthode retournant l'action du bouton permettant l'affichage de la technique à utiliser.
     * @return [EventHandler<Event>] Action du bouton.
     */
    private EventHandler<Event> getTechniqueAction(TechniqueInter technique){
        return event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/"+technique.getFichierFXML()));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            loader.getController();

            // Create the scene for the external frame
            Scene scenePopup = new Scene(root);
            scenePopup.setFill(Color.TRANSPARENT);
            scenePopup.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
            scenePopup.getRoot().setEffect(new DropShadow());

            // Create a new window for the external frame
            Stage popupWindow = new Stage();
            popupWindow.setResizable(false);
            popupWindow.setWidth(scene.getWidth());
            popupWindow.setHeight(scene.getHeight());
            popupWindow.setAlwaysOnTop(true);
            popupWindow.initStyle(StageStyle.TRANSPARENT);
            popupWindow.initOwner(scene.getWindow());
            popupWindow.initModality(Modality.APPLICATION_MODAL);
            popupWindow.setUserData(false);

            PopupWindowController.setStage(popupWindow);

            popupWindow.setOnHidden(event1 -> scene.getRoot().setEffect(null));

            popupWindow.setScene(scenePopup);
            technique.setStage(popupWindow);

            // Apply the darkening effect to the main scene
            ColorAdjust darkColorAdjust = new ColorAdjust();
            darkColorAdjust.setBrightness(-0.5);
            scene.getRoot().setEffect(darkColorAdjust);

            // Show the popup window
            popupWindow.showAndWait();
        };
    }

    /**
     * Méthode d'activation et désactivation du mode hypothèse.
     */
    @FXML
    private void hypotheseMethod() {
        if (enModeHypothese) {
            enModeHypothese = false;
            grillePane.getParent().setStyle("-fx-background-image: url('images/background.png')");

            valid_hypo.setDisable(true);
            valid_hypo.setVisible(false);
            supp_hypo.setDisable(true);
            supp_hypo.setVisible(false);

            hypothese.setVisible(true);
            hypothese.setDisable(false);
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
            enModeHypothese = true;
            grillePane.getParent().setStyle("-fx-background-image: url('images/background_blur.png')");

            valid_hypo.setDisable(false);
            valid_hypo.setVisible(true);
            supp_hypo.setDisable(false);
            supp_hypo.setVisible(true);

            hypothese.setVisible(false);
            hypothese.setDisable(true);
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
        valid_hypo.setDisable(true);
        valid_hypo.setVisible(false);

        supp_hypo.setDisable(true);
        supp_hypo.setVisible(false);

        vbox_aide_info.setDisable(true);
        vbox_aide_info.setVisible(false);

        zoom.setOnAction(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), grillePane);
            if(zoom_level<=5) {
                zoom_level++;
                st.setByX(0.1);
                st.setByY(0.1);
                st.play();
            }
        });

        dezoom.setOnAction(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), grillePane);
            if(zoom_level>=-5) {
                zoom_level--;
                st.setByX(-0.1);
                st.setByY(-0.1);
                st.play();
            }
        });

        help.setOnAction(event -> helpMethod());

        quit.setOnAction(event -> {
            GlobalVariables.setInGame(false);
            grille.creer_sauvegarde("/niveau/"+loadedFile);
            FXMLUtils.goBack(scene);
        });

        restart.setOnAction(event -> {
            textInfo.setText("Etes-vous sûr de vouloir réinitiliser la grille ?");
            vbox_aide_info.setVisible(true);
            vbox_aide_info.setDisable(false);
            vbox_aide_info.toFront();
            ok_button.setVisible(false);
            ok_button.setDisable(true);

            see_tech.setText("Annuler");
            see_tech.setVisible(true);
            see_tech.setDisable(false);
            next_clue.setText("Confirmer");
            next_clue.setVisible(true);
            next_clue.setDisable(false);

            EventHandler<? super MouseEvent> next_clue_act = next_clue.getOnMouseClicked();
            EventHandler<? super MouseEvent> see_tech_act = see_tech.getOnMouseClicked();

            next_clue.setOnMouseClicked(event1 -> {
                FXMLUtils.goBack(scene);
                FXMLUtils.loadFXML("/GrilleDisplay.fxml", scene, loadedFile, false);
            });

            see_tech.setOnMouseClicked(event1 -> {
                this.next_clue.setOnMouseClicked(next_clue_act);
                this.see_tech.setOnMouseClicked(see_tech_act);
                vbox_aide_info.setDisable(true);
                vbox_aide_info.setVisible(false);
            });
        });


        hypothese.setOnMouseClicked(event -> hypotheseMethod());

        valid_hypo.setOnMouseClicked(event -> validation_hypotheses());

        supp_hypo.setOnMouseClicked(event -> suppression_hypotheses());

        check.setOnMouseClicked(event -> verification_grille());

        undo.setOnMouseClicked(event -> undoMethod());

        redo.setOnMouseClicked(event -> redoMethod());
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
        //verifFinGrille();
    }

    /**
     * Méthode de création d'un rectangle de pontPossible au nord du bouton courant
     * @param ileSrc Ile courante
     * @param boutonSrc Bouton courant
     */
    private RectPontPossible creerPontPossibleNord(Ile ileSrc, Button boutonSrc) {
        Ile ileNord = ileSrc.getIleNord(grille);

        if (ileNord != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileNord) && !ileNord.ileComplete() && !ileSrc.ileComplete() && ileSrc.getValPontDir("N") == 0) {
            Button buttonDestNord = findButtonByCoord(ileNord.getY(), ileNord.getX());

            if (buttonDestNord != null) {
                buttonDestNord.setStyle("-fx-background-color: #F7ECB8;");
                int height = ileSrc.getX() - ileNord.getX() - 1;
                RectPontPossible pontNord = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonSrc, buttonDestNord, ileSrc, ileNord, "N", enModeHypothese );
                pontNord.addToGridPane();
                return pontNord;
            }
        }
        return null;
    }

    /**
     * Méthode de création d'un rectangle de pontPossible au sud du bouton courant
     * @param ileSrc Ile courante
     * @param boutonSrc Bouton courant
     */
    private RectPontPossible creerPontPossibleSud(Ile ileSrc, Button boutonSrc) {
        Ile ileSud = ileSrc.getIleSud(grille);

        if (ileSud != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileSud) && !ileSud.ileComplete() && !ileSrc.ileComplete() && ileSrc.getValPontDir("S") == 0) {
            Button buttonDestSud = findButtonByCoord(ileSud.getY(), ileSud.getX());

            if (buttonDestSud != null) {
                buttonDestSud.setStyle("-fx-background-color: #F7ECB8;");
                int height = ileSud.getX() - ileSrc.getX() - 1;
                RectPontPossible pontSud = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonSrc, buttonDestSud, ileSrc, ileSud, "S" , enModeHypothese);
                pontSud.addToGridPane();
                return pontSud;
            }
        }
        return null;
    }

    /**
     * Méthode de création d'un rectangle de pontPossible à l'Ouest du bouton courant
     * @param ileSrc Ile courante
     * @param boutonSrc Bouton courant
     */
    private RectPontPossible creerPontPossibleOuest(Ile ileSrc, Button boutonSrc) {
        Ile ileOuest = ileSrc.getIleOuest(grille);

        if (ileOuest != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileOuest) && !ileOuest.ileComplete() && !ileSrc.ileComplete() && ileSrc.getValPontDir("O") == 0) {
            Button buttonDestOuest = findButtonByCoord(ileOuest.getY(), ileOuest.getX());

            if (buttonDestOuest != null) {
                buttonDestOuest.setStyle("-fx-background-color: #F7ECB8;");
                int width = ileSrc.getY() - ileOuest.getY() - 1;
                RectPontPossible pontOuest = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonSrc, buttonDestOuest, ileSrc, ileOuest, "O", enModeHypothese);
                pontOuest.addToGridPane();

                return pontOuest;
            }
        }
        return null;
    }

    /**
     * Méthode de création d'un rectangle de pontPossible à l'est du bouton courant
     * @param ileSrc Ile courante
     * @param boutonSrc Bouton courant
     */
    private RectPontPossible creerPontPossibleEst(Ile ileSrc, Button boutonSrc) {
        Ile ileEst = ileSrc.getIleEst(grille);

        if (ileEst != null && grille.pontPossibleEntre((IleJoueur) ileSrc, (IleJoueur) ileEst) && !ileEst.ileComplete() && !ileSrc.ileComplete() && ileSrc.getValPontDir("E") == 0) {
            Button buttonDestEst = findButtonByCoord(ileEst.getY(), ileEst.getX());

            if (buttonDestEst != null) {
                buttonDestEst.setStyle("-fx-background-color: #F7ECB8;");
                int width = ileEst.getY() - ileSrc.getY() - 1;
                RectPontPossible pontEst = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonSrc, buttonDestEst, ileSrc, ileEst, "E", enModeHypothese);
                pontEst.addToGridPane();

                return pontEst;
            }
        }
        return null;
    }


    /**
     * Méthode de chargement graphique de la grille.
     */
    private void chargeGrille(){
        System.out.println("Chargement au front");
        List<Ile> ilesDejaVerifiees = new ArrayList<>();
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                Ile ile = grille.getIleGrilleJoueur(i,j);
                if(ile!=null && !ilesDejaVerifiees.contains(ile)) {
                    System.out.println("Ile pas nulle");
                    Button boutonIle = findButtonByCoord(j, i);
                    int valN = ile.getValPontDir("N");
                    int valS = ile.getValPontDir("S");
                    int valO = ile.getValPontDir("O");
                    int valE = ile.getValPontDir("E");
                    System.out.println("valDir : " + valN + " - "+ valS +" - "+ valO + " - "+valE );

                    Ile ileNord, ileSud, ileOuest, ileEst;

                    if (valN > 0 && (ileNord = ile.getIleNord(grille))!=null && !ilesDejaVerifiees.contains(ileNord)) {
                        System.out.println("ile au nord");

                        Button buttonDestNord = findButtonByCoord(ileNord.getY(), ileNord.getX());
                        int height = ile.getX() - ileNord.getX() - 1;
                        RectPontPossible rect = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonIle, buttonDestNord, ile, ileNord, "N", enModeHypothese );
                        rect.addToGridPane();

                        rect.activeChargement();

                        rect.simulerClick();
                        if (valN == 2) {
                            System.out.println("Deuxième click Nord");
                            rect.simulerClick();
                        }
                        rect.desactiveChargement();
                    }
                    if (valS > 0 && (ileSud = ile.getIleSud(grille))!=null && !ilesDejaVerifiees.contains(ileSud)) {
                        System.out.println("ile au sud");

                        Button buttonDestSud = findButtonByCoord(ileSud.getY(), ileSud.getX());
                        int height = ileSud.getX() - ile.getX() - 1;
                        RectPontPossible rect = new RectPontPossible(grille, grillePane,this.pixelSize / 2 , this.pixelSize * height, boutonIle, buttonDestSud, ile, ileSud, "S" , enModeHypothese);
                        rect.addToGridPane();

                        rect.activeChargement();

                        rect.simulerClick();
                        if (valS == 2) {
                            System.out.println("Deuxième click Sud");
                            rect.simulerClick();
                        }
                        rect.desactiveChargement();
                    }
                    if (valO > 0 && (ileOuest = ile.getIleOuest(grille))!=null && !ilesDejaVerifiees.contains(ileOuest)) {
                        System.out.println("ile à l'ouest");

                        Button buttonDestOuest = findButtonByCoord(ileOuest.getY(), ileOuest.getX());

                        int width = ile.getY() - ileOuest.getY() - 1;
                        RectPontPossible rect = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonIle, buttonDestOuest, ile, ileOuest, "O", enModeHypothese);
                        rect.addToGridPane();

                        rect.activeChargement();

                        rect.simulerClick();
                        if (valO == 2) {
                            System.out.println("Deuxième click Ouest");
                            rect.simulerClick();
                        }
                        rect.desactiveChargement();
                    }
                    if (valE > 0 && (ileEst = ile.getIleEst(grille))!=null && !ilesDejaVerifiees.contains(ileEst)) {
                        System.out.println("ile à l'est");

                        Button buttonDestEst = findButtonByCoord(ileEst.getY(), ileEst.getX());

                        int width = ileEst.getY() - ile.getY() - 1;
                        RectPontPossible rect = new RectPontPossible(grille, grillePane, this.pixelSize*width,this.pixelSize / 2 , boutonIle, buttonDestEst, ile, ileEst, "E", enModeHypothese);
                        rect.addToGridPane();

                        rect.activeChargement();

                        rect.simulerClick();
                        if (valE == 2) {
                            System.out.println("Deuxième click Est");
                            rect.simulerClick();
                        }
                        rect.desactiveChargement();
                    }
                    ilesDejaVerifiees.add(ile);
                }
            }
        }
    }


    /**
     * Méthode de suppression des ponts possibles.
     */
    private void deleteBridges(){
        List<RectPontPossible> nodesToRemove = new ArrayList<>();
        for (Node node: grillePane.getChildren()) {
            if(node instanceof RectPontPossible && ((RectPontPossible)node).line1==null ){
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
     * Méthode d'initialisation des données de la grille
     * @param levelFileName Nom du fichier du niveau
     * @param chargement booleen utilisé lorsque une grille sera chargée ou pas.
     */
    public void initData(String levelFileName, boolean chargement) {
        GlobalVariables.setInGame(true);
        System.out.println("LevelFileNameCorrected: " + levelFileName);
        loadedFile = levelFileName;

        // Get the resource as a stream
        InputStream resourceStream = getClass().getResourceAsStream("/niveaux/" + loadedFile);

        if (resourceStream == null) {
            System.err.println("Resource not found: " + loadedFile);
            // Handle the error, for example by throwing an exception or returning null
        } else {
            InputStreamReader reader = new InputStreamReader(resourceStream);
            // Pass the InputStream to GrilleJeu

            enModeHypothese=false;

            this.zoom_level = 0;
            initButtons();
            initChrono();

            this.grille = new GrilleJeu(reader);
            initializeGrille();

            if(chargement){
                System.out.println("Chargement de la grille depuis un fichier de sauvegarde");
                this.grille = grille.charger_sauvegarde(loadedFile.substring(0, loadedFile.length()-4)+".ser");
                chargeGrille();
            }
            System.out.println("Grille: " + this.grille);

            startChrono();
        }
    }

    /**
     * Méthode d'initialisation de la grille.
     */
    public void initializeGrille() {
        System.out.print("Taille grille : " + this.grille.getNbColonne() + " - " + this.grille.getNbLigne());
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
                    int J = j;
                    int I = i;
                    button.setOnMouseEntered(event -> {
                        createBridges(button, I, J);
                        System.out.println("Sur ile!");
                    });

                    button.setOnMouseExited(event -> {
                        Timeline timelineDel = new Timeline(new KeyFrame(Duration.seconds(0.05), eventDel -> deleteBridges()));
                        timelineDel.play();
                    });

                    grillePane.getChildren().add(button);
                    GridPane.setColumnIndex(button, j);
                    GridPane.setRowIndex(button, i);
                }
            }
        }
        grillePane.toFront();
    }
}
