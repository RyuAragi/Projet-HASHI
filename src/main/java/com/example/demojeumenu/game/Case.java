package com.example.demojeumenu.game;

import java.io.Serializable;

/**
 * Cette classe représente une ile abstraite
 * @author Coupé Xavier
 * @version 0.1
 */

public class Case implements Serializable {

    private final int x;
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


    public int getX(){return x;}
    public int getY(){return y;}
}