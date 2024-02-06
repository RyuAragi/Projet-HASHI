/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/

public class Aide{


    static boolean techniqueDeDepart(Jeu grille){

        return true;
    }

    static boolean techniqueHuitAuMilieu(Jeu grille){
        int i,j;
        for(i = 0; i <grille.getTailleLigne();i++){
            for(j = 0; j < grille.getTailleColonne();j++){
                if (grille.mat[i][j] != null && grille.mat[i][j].getEtiquette()==8){
                        return true;
                }
            }
        }
        return false;
    }


}