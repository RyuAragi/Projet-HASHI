/**
 * Cette classe représente une ile abstraite
 * @author Coupé Xavier
 * @version 0.1
 */

package com.example.demojeumenu.game;

import java.util.ArrayList;

public abstract class Ile extends Case {

    //La valeur de l'île (le nombre de pont qu'il faut relier)
    private final int valIle;

    /**
     *
     * @param cX La coordonnée X
     * @param cY La coordonnée Y
     * @param valIle_ La valeur de l'île
     */
    Ile(int cX, int cY, int valIle_ ){
        super(cX,cY);

        valIle = valIle_;
    }

    public int getValIle(){
        return valIle;
    }

    /*
     * Méthode qui compte la somme des voisins
     * @return la somme des voisins d'un noeud
     */
    public abstract int getSommeVoisins();

    /**
     * Méthode qui renvoie le nb de pont en fonction d'une direction
     * @param dir la direction désirée
     * @return Le nombre de pont associé
     */
    public abstract int getValPontDir(String dir);

    public Ile getIleNord(GrilleJeu grille){
        int i=super.getX()-1;
        if(i<0) return null;

        while(i >= 0){
            if(grille.getIleGrilleJoueur(i, super.getY())!=null){
                return grille.getIleGrilleJoueur(i, super.getY());
            }
            i--;
        }
        return null;
    }

    public Ile getIleSud(GrilleJeu grille){
        int i=super.getX()+1;
        if(i==grille.getNbLigne()) return null;

        while(i < grille.getNbLigne()){
            if(grille.getIleGrilleJoueur(i, super.getY())!=null){
                return grille.getIleGrilleJoueur(i, super.getY());
            }
            i++;
        }
        return null;
    }

    public Ile getIleOuest(GrilleJeu grille){
        int i=super.getY()-1;
        if(i<0) return null;

        while(i >= 0){
            if(grille.getIleGrilleJoueur(super.getX(), i)!=null){
                return grille.getIleGrilleJoueur(super.getX(), i);
            }
            i--;
        }
        return null;
    }

    public Ile getIleEst(GrilleJeu grille){
        int i=super.getY()+1;
        if(i==grille.getNbColonne()) return null;

        while(i < grille.getNbColonne()){
            if(grille.getIleGrilleJoueur(super.getX(), i)!=null){
                return grille.getIleGrilleJoueur(super.getX(), i);
            }
            i++;
        }
        return null;
    }

    public boolean ileComplete(){
        return getValIle() == getSommeVoisins();
    }
}