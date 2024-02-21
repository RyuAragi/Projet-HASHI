/**
 * Cette classe représente un pont
 * @author Coupé Xavier
 * @version 0.1
 */

public class Pont{
    boolean estHypothese; 


    IleJoueur src;
    IleJoueur dst;
    
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

    public boolean estHypothese(){
        return estHypothese;
    }
}
