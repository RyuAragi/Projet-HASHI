package com.example.demojeumenu.game;

import com.example.demojeumenu.GrilleControler;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

/**
 * Classe implémentant la création et les actions d'un RectPontPossible qui est inséré dans le GridPane de l'interface graphique.
 */
public class RectPontPossible extends Rectangle {

    /**
     * [Boolean] Booléen utilisé lors du chargement de la grille empêche l'insertion de pont dans le back si =True.
     */
    private boolean chargement;

    /**
     * [Boolean] Indicateur de présence de la souris sur le rectangle
     */
    private boolean mouseOn;

    /**
     * [LignePont] Référence vers la 1e ligne symbolisant un pont simple
     */
    protected LignePont line1;

    /**
     * [LignePont] Référence vers la deuxième ligne qui, accompagnée de la première, forme un pont double.
     */
    protected LignePont line2;

    /**
     * [String] Chaine de caractère représentant la direction du pont parmi N, S, E et O.
     */
    private final String dir;

    /**
     * [Ile] Ile source du pont
     */
    protected final Ile ileSrc;

    /**
     * [Ile] Ile destination du pont
     */
    protected final Ile ileDest;

    /**
     * [Bouton] Bouton de l'ile source dans le grillePane
     */
    protected final Button boutonSrc;

    /**
     * [Bouton] Bouton de l'ile destination dans le grillePane
     */
    protected final Button boutonDest;

    /**
     * [GrilleJeu] Référence vers la grille du backend.
     */
    protected final GrilleJeu grille;

    /**
     * [GridPane] Référence vers la grillePane c'est-à-dire la grille du frontend.
     */
    protected final GridPane grillePane;

    /**
     * [Boolean] Booléen vérifiant s'il s'agit d'un pont hypothèse ou pas.
     */
    private boolean hypothese;


    /**
     * Méthode d'instanciation d'un RectPontPossible.
     * @param grille [GrilleJeu] La grille du backend.
     * @param grillePane [GridPane] La grille affichée à l'utilisateur (au frontend).
     * @param width [Integer] Longueur du rectangle.
     * @param height [Integer] Largeur du rectangle.
     * @param boutonSrc [Button] Bouton de l'ile source inclu dans grillePane.
     * @param boutonDest [Button] Bouton de l'ile destination inclu dans grillePane.
     * @param ileSrc [Ile] Ile source du RectPontPossible.
     * @param ileDest [Ile] Ile destination du RectPontPossible.
     * @param dir [String] Direction du pont.
     * @param hypothese [Boolean] Indicateur vérifiant s'il s'agit d'un pont hypothèse ou pas.
     */
    public RectPontPossible(GrilleJeu grille, GridPane grillePane, int width, int height, Button boutonSrc, Button boutonDest, Ile ileSrc, Ile ileDest, String dir, boolean hypothese){
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
        this.hypothese = hypothese;
        this.chargement = false;

        this.setFill(Color.valueOf("#F7ECB8"));
        this.toFront();

        this.setOnMouseEntered(getMouseOn(boutonDest));
        this.setOnMouseClicked(getClickedAction());
        this.setOnMouseExited(getMouseOut());
    }


    /**
     * Méthode de récupération du bouton de l'ile source.
     * @return [Button] Bouton de l'ile source.
     */
    public Button getBoutonSrc() {
        return boutonSrc;
    }

    /**
     * Méthode de récupération du bouton de l'ile destination.
     * @return [Button] Bouton de l'ile destination.
     */
    public Button getBoutonDest() {
        return boutonDest;
    }

    /**
     * Méthode de récupération de l'ile source.
     * @return [Ile] Ile source du rectangle.
     */
    public Ile getIleSrc() {
        return ileSrc;
    }

    /**
     * Méthode de récupération de l'ile destination.
     * @return [Ile] Ile destination du rectangle.
     */
    public Ile getIleDest() {
        return ileDest;
    }

    /**
     * Méthode de récupération de l'instance de la ligne 1.
     * @return [LignePont] Instance de la ligne 1.
     */
    public LignePont getLine1() {
        return line1;
    }

    /**
     * Méthode de récupération de l'instance de la ligne 2.
     * @return [LignePont] Instance de la ligne 2.
     */
    public LignePont getLine2() {
        return line2;
    }

    /**
     * Méthode de récupération de la direction du rectPontPossible en fonction de l'ile source.
     * @return [String] Direction du pont (N,S,E,O)
     */
    public String getDir() {
        return dir;
    }

    /**
     * Méthode permettant de modifier la valeur de l'hypothèse d'un pont.
     * @param hypo [Boolean] Nouvel indicateur d'hypothèse.
     */
     public void setHypothese(Boolean hypo){
        this.hypothese = hypo;
     }

    /**
     * Méthode de récupération de l'indicateur d'hypothèse d'un pont.
     * @return [Boolean] Indicateur d'hypothèse.
     */
    public boolean estHypothese() {
        return hypothese;
    }

    /**
     * Méthode vérifiant si un pont est double (=true) ou simple (=false).
     * @return Indicateur du niveau de pont.
     */
    public boolean estDoublePont(){
        return (line1!=null && line2!=null);
    }

    /**
     * Méthode d'affectation de la ligne 2 à une instance passé en paramètre.
     * @param ligne [LignePont] Instance de LignePont
     */
    public void setLine2(LignePont ligne){
        this.line2 = ligne;
    }

    /**
     * Méthode de récupération de la grille du backend.
     * @return [GrilleJeu] Grille du backend.
     */
    public GrilleJeu getGrille() {
        return grille;
    }

    /**
     * Méthode de récupération de la grille du frontend.
     * @return [GridPane] Grille du frontend.
     */
    public GridPane getGrillePane() {
        return grillePane;
    }

    /**
     * Méthode de désactivation du chargement. Autorise la pose de pont sur la grille du backend.
     */
    public void desactiveChargement(){
        if(this.chargement){
            this.chargement = false;
        }
    }

    /**
     * Méthode d'activation du chargement. Interdit la pose de pont sur la grille du backend.
     */
    public void activeChargement(){
        if(!this.chargement){
            this.chargement = true;
        }
    }

    /**
     * Méthode de suppression du rectangle rectPontPossible de la grille affichée.
     * @param grillePane [GridPane] La grille affichée au joueur
     */
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

    /**
     * Méthode d'ajout du rectangle à l'affichage dans la grille GrillePane
     */
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

    /**
     * Méthode permettant d'obtenir l'action lorsque la souris entre sur le rectangle RectPontPossible
     * @return [EventHandler<MouseEvent>] Evenement d'entrée.
     */
    private EventHandler<MouseEvent> getMouseOn(Button buttonDest){
        return event -> {
            this.mouseOn = true;
            this.setFill(Color.valueOf("#F7ECB8"));
            if (!ileDest.ileComplete()) {
                buttonDest.setStyle("-fx-background-color: transparent;");
            }
        };
    }


    /**
     * Méthode permettant d'obtenir l'action lorsque la souris clique sur le rectangle RectPontPossible
     * @return [EventHandler<MouseEvent>] Evenement de clique.
     */
    private EventHandler<MouseEvent> getClickedAction(){
        return event -> {
            int valPontDir = ileSrc.getValPontDir(dir);
            System.out.println(valPontDir);
            if(valPontDir==0 || line1==null){
                boutonSrc.setStyle("-fx-background-color: transparent");
                boutonDest.setStyle("-fx-background-color: transparent");

                this.setFill(Color.TRANSPARENT);
                if(dir.equals("N") || dir.equals("S")) {
                    line1 = new LignePont(this, (int) (this.getX() + this.getWidth() / 2), (int) this.getY(), (int) (this.getX() + this.getWidth() / 2), (int) (this.getY() + this.getHeight()));
                }
                else{
                    line1 = new LignePont(this, (int) this.getX(), (int) (this.getY() + this.getHeight()/2), (int) (this.getX() + this.getWidth()), (int) (this.getY()+ this.getHeight()/2));
                }
                line1.addToGridPane();
                if(!this.chargement) grille.poserPont(ileSrc, ileDest, hypothese);

                if (ileSrc.ileComplete()) {
                    boutonSrc.setStyle("-fx-background-color: lightgrey;");
                }
                if (ileDest.ileComplete()) {
                    boutonDest.setStyle("-fx-background-color: lightgrey;");
                }
            }
            else if((valPontDir==1 || line2==null) && this.hypothese==GrilleControler.enModeHypothese){
                if ((!ileSrc.ileComplete() && !ileDest.ileComplete()) || this.chargement) {
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
                    if(!this.chargement) grille.poserPont(ileSrc, ileDest, hypothese);

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
                    if(!this.chargement) {
                        grille.poserPont(ileSrc, ileDest, hypothese);
                        grille.poserPont(ileSrc, ileDest, hypothese);
                    }
                }
            }
            else if(valPontDir==2 && this.hypothese==GrilleControler.enModeHypothese){
                boutonDest.setStyle("-fx-background-color: transparent");
                boutonSrc.setStyle("-fx-background-color: transparent");
                removeFromGridPane(grillePane);
                if(!this.chargement) grille.poserPont(ileSrc, ileDest, hypothese);
            }
        };
    }

    /**
     * Méthode permettant d'obtenir l'action lorsque la souris sort du rectangle RectPontPossible
     * @return [EventHandler<MouseEvent>] Evenement de sortie.
     */
    private EventHandler<MouseEvent> getMouseOut(){
        return event -> {
                this.mouseOn = false;
                this.setFill(Color.TRANSPARENT);
                if (line1==null) {
                    removeFromGridPane(grillePane);
                    boutonDest.setStyle("-fx-background-color: transparent;");
                }
        };
    }

    /**
     * Méthode de simulation de clique sur le rectangle pour poser un pont. Cette méthode est utilisée lors du chargement d'un grille précédemment sauvegardée.
     */
    public void simulerClick(){
        System.out.println("Coords : "+this.getX()+ " - "+this.getY());
        MouseEvent mouseEvent = new MouseEvent(
                MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null
        );

        // Déclencher l'événement sur le rectangle
        this.fireEvent(mouseEvent);
    }
}
