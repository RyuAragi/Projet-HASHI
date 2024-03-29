/**
 * Cette classe représente une ile dans la grille solution
 * @author Coupé Xavier
 * @version 0.1
 */

package com.example.demojeumenu;

import java.util.HashMap;
import java.util.Map;

public class IleSolution extends Ile{

    //Enregistre le nombre de pont en fonction de la direction (N => Nord, S => Sud, E => Est, O => Ouest)
    private HashMap<String,Integer> pontRelie;

    IleSolution(int cX, int cY, int valIle_ , int nbPontsN_, int nbPontsS_,int nbPontsE_, int nbPontsO_){
        super(cX,cY,valIle_);

        pontRelie = new HashMap<>();
        pontRelie.put("N", nbPontsN_);
        pontRelie.put("S", nbPontsS_);
        pontRelie.put("E", nbPontsE_);
        pontRelie.put("O", nbPontsO_);
    }

    /*
     * Méthode qui compte la somme des voisins 
     * @return la somme des voisins d'un noeud
     */
    int getSommeVoisins(){
        int cpt = 0;

        for(Map.Entry<String, Integer> ile : pontRelie.entrySet()){
            cpt += ile.getValue();
        }
        return cpt;
    }

    /**
     * Méthode qui renvoie le nb de pont en fonction d'une direction
     * @param dir la direction désirée
     * @return Le nombre de pont associé
     */
    int getValPontDir(String dir){
        return pontRelie.get(dir);
    }

}
