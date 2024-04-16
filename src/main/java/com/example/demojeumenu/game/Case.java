package com.example.demojeumenu.game;

import java.io.Serializable;

/**
 * Cette classe représente une ile abstraite
 * @author Coupé Xavier
 * @version 0.1
 */

public class Case implements Serializable {
    /**
     * Les coordonnées x et y de la case
     */
    private final int x;
    /**
     * Les coordonnées y de la case
     */
    private final int y;

    /**
     * Constructeur d'une Case
     * @param cX La coordonnée x
     * @param cY La coordonnée y
     */
    protected Case(int cX, int cY){
        x = cX;
        y = cY;
    }

    /**
     * Renvoie la coordonnée x
     * @return la coordonnée x
     */
    public int getX(){return x;}
    /**
     * Renvoie la coordonnée y
     * @return la coordonnée y
     */
    public int getY(){return y;}
}