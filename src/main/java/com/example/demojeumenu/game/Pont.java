/**
 * Cette classe représente un pont
 * @author Coupé Xavier
 * @version 0.1
 */

package com.example.demojeumenu.game;

import java.io.Serializable;

public class Pont implements Serializable {
    private boolean estHypothese;

    private enum VertiHori{
        VERTICALE,HORIZONTAL
    }

    private final VertiHori estVertiHori;

    private final IleJoueur src;
    private final IleJoueur dst;

    /**
     * Constructeur de Pont
     * @param src_ l'ile source du pont
     * @param dst_ l'ile destination du pont
     * @param h true si c'est un pont hypothese, false sinon
     */
    public Pont(IleJoueur src_, IleJoueur dst_, boolean h){
        src = src_;
        dst = dst_;
        estHypothese = h;

        if (src.getX() == dst.getX()){
            estVertiHori = VertiHori.HORIZONTAL;
        }else{
            estVertiHori = VertiHori.VERTICALE;
        }
    }

    /**
     * Renvoie vrai si la pont est vertical
     * @return vrai ou faux
     */
    public boolean estVertical(){
        return estVertiHori == VertiHori.VERTICALE;
    }

    /**
     * Renvoie vrai si la pont est horizontal
     * @return vrai ou faux
     */
    public boolean estHorizontal(){
        return estVertiHori == VertiHori.HORIZONTAL;
    }

    /**
     * Renvoie vrai si le pont est un pont hypothese
     * @return True, ou False
     */
    public boolean estHypothese(){
        return estHypothese;
    }

    /**
     * Renvoie l'ile destination
     * @return l'ile destination
     */
    public IleJoueur getDst(){
        return dst;
    }

    /**
     * Renvoie l'ile source
     * @return l'ile source
     */
    public IleJoueur getSrc(){
        return src;
    }

    /**
     * Renvoie la coordonnée y la plus petite entre les iles d'un pont
     * @return la coordonnée y
     */
    public int getMinY(){
        return src.getY() < dst.getY() ? src.getY() : dst.getY();
    }


    /**
     * Renvoie la coordonnée x la plus petite entre les iles d'un pont
     * @return la coordonnée x
     */
    public int getMinX(){
        return src.getX() < dst.getX() ? src.getX() : dst.getX();
    }


    /**
     * Renvoie la coordonnée y la plus petite entre les iles d'un pont
     * @return la coordonnée y
     */
    public int getMaxY(){
        return src.getY() > dst.getY() ? src.getY() : dst.getY();
    }


    /**
     * Renvoie la coordonnée x la plus petite entre les iles d'un pont
     * @return la coordonnée x
     */
    public int getMaxX(){
        return src.getX() > dst.getX() ? src.getX() : dst.getX();
    }


    /**
     * Set la valeur estHypothese du pont
     * @param b true ou false
     */
    public void setHypothese(Boolean b){
        estHypothese = b;
    }

}