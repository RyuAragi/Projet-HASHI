/**
 * @author GEORGET Rémy 
 * @version 1.0
 */

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
 public class UndoRedo {
     private Stack<Pont> Redo;
     
     /**
      * Constructeur de la classe
      */
     UndoRedo() {   
         this.Redo = new Stack<Pont>();
     }
 
      /**
      * Methode permettant d'empiler sur Redo
      */
     void pushRedo(Pont p) {
         this.Redo.push(p);
     }
 
     /** 
      * Methode permettant de dépiler sur Undo
      * 
      * @param p Pont a ajouter
      * @return Pont 
      */
     Pont popRedo(Pont p) {
         return this.Redo.pop();
     }
 
     /** 
      * Methode permettant de RAZ la pile Redo
      */
     void RazRedo() {
         while(!(this.Redo.empty())) this.Redo.pop();
     }
 
     /**
      * Methode permettant de faire une action undo -> Revenir en arriere 
      * @param jeu
      */
     void actionUndo(GrilleJeu jeu) {
         
     }
 
 }