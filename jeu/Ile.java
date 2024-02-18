import java.util.*;

public class Ile extends Case{
    
    //La valeur de l'île (le nombre de pont qu'il faut relier)
    private int valIle;

    //Enregistre le nombre de pont en fonction de la direction (N => Nord, S => Sud, E => Est, O => Ouest)
    private HashMap<String,Integer> nbPontDir;
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
    Ile(int cX, int cY, int valIle_ , int nbPontsN_, int nbPontsS_,int nbPontsE_, int nbPontsO_){
        super(cX,cY);

        valIle = valIle_;
        nbPontDir = new HashMap<>();
        nbPontDir.put("N", nbPontsN_);
        nbPontDir.put("S", nbPontsS_);
        nbPontDir.put("E", nbPontsE_);
        nbPontDir.put("O", nbPontsO_);
    }

    int getEtiquette(){return valIle;}

    /*
     * Méthode qui compte la somme des voisins 
     * @return la somme des voisins d'un noeud
     */
    int getSommeVoisins(){
        int cpt = 0;

        for(Map.Entry<String, Integer> ile : nbPontDir.entrySet()){
            cpt += ile.getValue();
        }
        return cpt;
    }

    int getValIle(){return valIle;}

    int getValPontDir(String dir){return nbPontDir.get(dir);}

    void incrementValPontDir(String dir){
        nbPontDir.replace(dir, (nbPontDir.get(dir)+1)%3);
    }

}
