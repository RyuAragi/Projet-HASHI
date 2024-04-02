package com.example.demojeumenu.game;

/**
 * Cette classe représente une ile abstraite
 * @author Coupé Xavier
 * @version 0.1
 */

public class Case {

    private int x;
    private int y;

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