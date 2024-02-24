/**
 * Cette classe représente un pont
 * @author Coupé Xavier
 * @version 0.1
 */

public class Pont{
    boolean estHypothese; 


    private IleJoueur src;
    private IleJoueur dst;
    
    /**
     * Constructeur de Pont
     * @param src_ l'ile source du pont
     * @param dst_ l'ile destination du pont
     * @param h true si c'est un pont hypothese, false sinon
     */
    Pont(IleJoueur src_, IleJoueur dst_, boolean h){
        src = src_;
        dst = dst_;
        estHypothese = h;
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

}
