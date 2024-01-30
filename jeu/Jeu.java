/** Classe jeu utilisé pour symboliser une grille de hashi*/
//* @author COUPE Xavier FOUQUERAY Victor*/
//* @version 0.0 */


public class Jeu{
    /**La matrice sur laquelle nous sommes actuellement entrain de corriger */
    Noeud[][] mat ;
    /**La matrice deja finis donc celle que l'on doit avoir au final */
    Noeud[][] mat_res ;
    /**La matrice que l'on sauvegardera au moment ou l'on fait une erreur */
    Noeud[][] mat_err ;
    /**Le nombre de lignes que la grille possède */
    int taille_li ;
    /** Le nombre de colonnes que la grille possède */
    int taille_col ;
    /**Constructeur @param o */
    Jeu(){

    }
    /**
     * Fonction permettant dans un premier temps de detecter dans quel sens se situe le noeud n2 par rapport au n1 et de vérifier si le pont poser est bon ou non
     * @param n1 le premier noeud
     * @param n2 le deuxieme noeud
     * @return true ou false en fonction de si le pont est valide ou pas
     */
    boolean verifPont(Noeud n1, Noeud n2){
        if (n1.x < n2.x){
            if(n1.ponts.get("E")<= mat_res[n1.x][n1.y].ponts.get("E") ) return true;
        }if(n1.x > n2.x){
            if(n1.ponts.get("O")<= mat_res[n1.x][n1.y].ponts.get("O") ) return true;
        }if(n1.y > n2.y){
            if(n1.ponts.get("S")<= mat_res[n1.x][n1.y].ponts.get("S") ) return true;
        }if(n1.y < n2.y){
            if(n1.ponts.get("N")<= mat_res[n1.x][n1.y].ponts.get("N") ) return true;
        }
        mat_err = mat;
        return false;
    }
    /**
     * Fonction permettant de poser un pont entre 2 noeuds en incremetant dans la bonne direction chacun des noeuds puis en utilisant %3, comme ça dès qu'on a dépasser deux on retourne a 0
     * @param n1 correspond au noeud 1
     * @param n2 correspond au noeud 2
     */
    void poserPont(Noeud n1, Noeud n2){
        if (n1.x < n2.x){
            n1.ponts.replace("E",n1.ponts.get("E")%3);
            n2.ponts.replace("O",n2.ponts.get("O")%3);
        }if(n1.x > n2.x){
            n1.ponts.replace("O",n1.ponts.get("O")%3);
            n2.ponts.replace("E",n2.ponts.get("E")%3);
        }if(n1.y > n2.y){
            n1.ponts.replace("S",n1.ponts.get("S")%3);
            n2.ponts.replace("N",n2.ponts.get("N")%3);
        }if(n1.y < n2.y){
            n1.ponts.replace("N",n1.ponts.get("N")%3);
            n2.ponts.replace("S",n2.ponts.get("S")%3);
        }
    }
}