/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/

package com.example.demojeumenu.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Voir si le code peut être factorisé
 * 
 * 
 * 
 * 
 */

public class Aide implements Serializable {


    /**
     * Méthode qui permet de détecter si une technique de départ est applicable
     * @param grille
     * @return vrai si une technique est applicable, faux sinon
     */
    static boolean techniqueDeDepart(GrilleJeu grille){
        List<Ile> listeIlesGrilleJoueur  = new ArrayList<>();
        List<Ile> listeIlesGrilleResolu = new ArrayList<>();

        int i,j;
        //int niveauAide;

        //Récupère toutes les îles non nuls de la grille de jeu
        for(i = 0; i < grille.getNbLigne(); i++){
            for(j = 0; j < grille.getNbColonne(); j++){
                if (grille.getIleGrilleJoueur(i, j) != null){
                    listeIlesGrilleJoueur.add(grille.getIleGrilleJoueur(i, j));

                    /*Pas necessaire de comparer dans les deux matrice ()
                    * L'emplacement des noeuds est la meme sur les deux matrices
                    */
                    listeIlesGrilleResolu.add(grille.getIleGrilleSolution(i, j));
                }
            }
        }

        //niveauAide = grille.getPrecisionAide();



        if (Aide.techniqueHuitAuMilieu(listeIlesGrilleJoueur) != null){
            System.out.println("Huit au milieu detecte");
            return true;
            
        }
        
        if(Aide.techniqueSixSurLeCote(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Six sur le cote detecte");
            return true;
            
        }

        if (Aide.techniqueQuatreDansUnCoin(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Quatre dans un angle detecte");
            return true;
        }
        return false;
        
    }

    /**
     * Méthode qui détecte si une technique d'une ile au milieu est applicable
     * @param list la liste des iles du joueur
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueAuMilieu(List<Ile> list){
        int v1 = 8;
        int v2 = 7;
        for(Ile n : list){
            if(((n.getValIle() == v1) && (n.getSommeVoisins() < v1) ) ||((n.getValIle() == v2)&&(n.getSommeVoisins()==v2))){
                return n;
            }
        }
        return null;
    }

    /**
     * 
     * @param list la liste des iles du joueur
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueHuitAuMilieu(List<Ile> list){
        for (Ile n : list){
            if ((n.getValIle() == 8) && (n.getSommeVoisins() < 8)){
                return n;
            }
        }
        
        return null;
    }

    /**
     * 
     * @param list la liste des iles du joueur
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueSeptAuMilieu(List<Ile> list){
        for (Ile n :list){
            if((n.getValIle() == 7) && (n.getSommeVoisins() < 7)){
                return n;
            }
        }
        return null;
    }


    
    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueSixSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 6)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<6)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille du jeu_
     * @return
     */
    private static Ile techniqueCinqSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 5)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<5)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille du jeu_
     * @return
     */
    private static Ile techniqueQuatreSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<4)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueQuatreDansUnCoin(List<Ile> listJoueur, List<Ile> listResolu,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<4)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueTroisDansUnCoin(List<Ile> listJoueur, List<Ile> listResolu,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 3)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<3)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueIleUnSeulVoisin(List<Ile> listJoueur,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 1){
                return listJoueur.get(i);
            }
        }
        return null;
    }

}