import java.util.HashMap;
import java.util.*;
/**Classe noeud utilisé pour symboliser les iles du jeu Hashi, elle est capable de se créer */
//* @author COUPE Xavier FOUQUERAY Victor*/
//*@version 0.0 */


public class Noeud {
    /** coordonnée x */
    int x;
    /** coordonnée y */
    int y;
    /** étiquette qui correspond au nombre de ponts reliés necessaire */
    int etiquette;
    /**indique la correspondance du nombre de pont en fonction de la direction (N pour nord, S pour sud, E pour est et O pour ouest) */
    HashMap<String,Integer> ponts;
    /**Constructeur @param c_x La coordonnée x du noeud @param c_y la coordonnée y du noeud @param nb_pmax l'étiquette (le nombre de pont a posé) du noeud*/
    Noeud(int c_x,int c_y , int nb_pmax , int nbVoisinsN, int nbVoisinsS,int nbVoisinsE, int nbVoisinsO){
        x=c_x;
        y=c_y;
        etiquette = nb_pmax;
        ponts = new HashMap<String,Integer>();
        ponts.put("N",nbVoisinsN);
        ponts.put("S",nbVoisinsS);
        ponts.put("E",nbVoisinsE);
        ponts.put("O",nbVoisinsO);
    }

    /* Méthode qui permet de récupérer la coordonnee x du noeud
     * @return la coordonnee x
    */
    int getX(){return x;}

    /* Méthode qui permet de récupérer la coordonnee y du noeud
     * @return la coordonnee y
    */
    int getY(){return y;}

    /* Méthode qui permet de récupérer l'etiquette du noeud
     * @return la valeur du noeud
    */
    int getEtiquette(){return etiquette;}


    /*
     * Cette méthode ne sert à rien / il faut la retirer
     */
    /*
     * Méthode qui compte le nombre de voisins d'un noeud qui possède un pont au moins
     * @return le nombre de voisin d'un noeud
     */
    int getNbVoisins(){
        int cpt = 0;

        for(Map.Entry<String,Integer> entree : ponts.entrySet()){
            if (!entree.getValue().equals(0)){
                cpt++;
            }
        }
        return cpt;
    }

    /*
     * Méthode qui compte la somme des voisins 
     * @return la somme des voisins d'un noeud
     */
    int getSommeVoisins(){
        int cpt = 0;

        for(Map.Entry<String, Integer> entree : ponts.entrySet()){
            cpt += (int)entree.getValue();
        }
        return cpt;
    }
}