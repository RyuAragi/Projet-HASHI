/**
 * Cette classe représente une ile abstraite
 * @author Coupé Xavier
 * @version 0.1
 */

public abstract class Ile extends Case{
    
    //La valeur de l'île (le nombre de pont qu'il faut relier)
    private int valIle;

    /**
     * 
     * @param cX La coordonnée X
     * @param cY La coordonnée Y
     * @param valIle La valeur de l'île 
     * @param nbPontsN Le nombre de Pont au Nord
     * @param nbPontsS Le nombre de Pont au Sud
     * @param nbPontsE Le nombre de Pont a l'Est 
     * @param nbPontsO Le nombre de Pont a l'Ouest 
     */
    Ile(int cX, int cY, int valIle_ ){
        super(cX,cY);

        valIle = valIle_;
    }

    int getValIle(){
        return valIle;
    }

    /*
     * Méthode qui compte la somme des voisins 
     * @return la somme des voisins d'un noeud
     */
    abstract int getSommeVoisins();

    /**
     * Méthode qui renvoie le nb de pont en fonction d'une direction
     * @param dir la direction désirée
     * @return Le nombre de pont associé
     */
    abstract int getValPontDir(String dir);

}
