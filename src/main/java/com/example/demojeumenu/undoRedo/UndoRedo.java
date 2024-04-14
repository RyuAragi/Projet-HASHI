/**
 * @author GEORGET Rémy 
 * @version 1.0
 */

package com.example.demojeumenu.undoRedo;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Pont;

import java.io.Serializable;
import java.util.Stack;

 /** 
  * La classe UndoRedo symbolise le fait de pouvoir revenir en arriere ou reprendre un coup déja posé. 
  * Le undo sera symbolisé 
  * Elle connait 1 pile : 
  * - Une pile Redo qui symbolise tout les coups permettant de reprendre un coup déja fait (dans le cas ou on a utilise le undo)
  * Elle sait : 
  * - Empiler sur Redo
  * - Depiler sur Redo 
  * - RAZ (Remettre a zero) la pile redo 
  * - Faire une action undo : 
  *   * Chercher le dernier coup joué (dernier posé pont) dans la liste de ponts -> Dans la classe GrilleJeu
  *   * Enlever le coup dans la grille de jeu
  *   * Empiler dans la pile Redo
  * - Faire une action redo : 
  *   * Depiler le coup de redo
  *   * Ajouter le pont dans la matrice 
  */
 public class UndoRedo implements Serializable {
    private final Stack<Pont> redo;
    
    /**
     * Constructeur de la classe
     */
    public UndoRedo() {
        this.redo = new Stack<Pont>();
    }

    /**
     * Methode permettant d'empiler sur Redo
     */
    void pushRedo(Pont p) {
        this.redo.push(p);
    }
 
    /** 
     * Methode permettant de dépiler sur Undo
     *
     * @return Pont 
     */
    Pont popRedo() {
        return this.redo.pop();
    }
 
    /** 
     * Methode permettant de RAZ la pile Redo
     */
    public void RazRedo() {
        while(!(this.redo.empty())) this.redo.pop();
    }

    /**
     * Methode permettant de faire une action undo -> Revenir en arriere 
     * @param jeu
     */
    public Pont actionUndo(GrilleJeu jeu) {
       Pont p = jeu.getDernierPontAjouter();
       this.pushRedo(p);
       return p;
    }

    /**
     * Methode permettant de faire une action redo -> Revenir a l'état avant le retour en arrière
     * @param jeu
     */
    public Pont actionRedo(GrilleJeu jeu){
        if(!redo.isEmpty()) {
            Pont p = this.popRedo();
            return p;
        }
        System.out.println("Plus de ponts !");
        return null;
    }

    /**
     * Test de Undo/Redo => A refaire avec de vrais tests maven
     * @param args
     *//*
    public static void main(String[] args) {
        GrilleJeu grille = new GrilleJeu("../niveaux/facile/Facile-1.txt");
        
        // Affichage de la matrice pour le test
        grille.afficher_mat_out();

        // Pose du pont et affichage du nombre de voisins pour savoir si ils sont connectés
        grille.poserPont(grille.getIleGrilleJoueur(0, 1), grille.getIleGrilleJoueur(0, 3));
        System.out.println("----- Pose du Pont -----");
        System.out.println("Nombre de pont a l'est a l'ile 0 1 : " + grille.getIleGrilleJoueur(0, 1).getValPontDir("E"));
        System.out.println("Nombre de pont a l'ouest a l'ile 0 3 : " + grille.getIleGrilleJoueur(0, 3).getValPontDir("O") + "\n");

        // Action de undo et affichage des coordonées X et Y des deux iles
        grille.undoRedo.actionUndo(grille);
        System.out.println("----- Action du Undo -----");
        System.out.println("Ile SRC : " + grille.undoRedo.redo.peek().getSrc().getX() + " " + grille.undoRedo.redo.peek().getSrc().getY());
        System.out.println("Ile DEST : " + grille.undoRedo.redo.peek().getDst().getX() + " " + grille.undoRedo.redo.peek().getDst().getY() + "\n");

        // Action de redo et affichage du nombre de voisins pour savoir si ils sont connectés
        grille.undoRedo.actionRedo(grille);
        System.out.println("----- Action du Redo -----");
        System.out.println("Nombre de pont a l'est a l'ile 0 1 : " + grille.getIleGrilleJoueur(0, 1).getValPontDir("E"));
        System.out.println("Nombre de pont a l'ouest a l'ile 0 3 : " + grille.getIleGrilleJoueur(0, 3).getValPontDir("O"));
    }*/
 }