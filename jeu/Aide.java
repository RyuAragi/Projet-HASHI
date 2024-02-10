import java.util.*;

/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/


public class Aide{


    /**
     * Méthode qui permet de détecter si une technique est applicable
     * @param grille
     * @return vrai si une technique est applicable, faux sinon
     */
    static boolean techniqueDeDepart(Jeu grille){
        List<Noeud> listeNoeudsGrilleJoueur  = new ArrayList<Noeud>();
        List<Noeud> listeNoeudsGrilleResolu = new ArrayList<Noeud>();

        int i,j;
        //int niveauAide;

        for(i = 0; i < grille.getTailleLigne(); i++){
            for(j = 0; j < grille.getTailleColonne(); j++){
                if (grille.mat[i][j] != null){
                    listeNoeudsGrilleJoueur.add(grille.mat[i][j]);

                    /*Pas necessaire de comparer dans les deux matrice ()
                    * L'emplacement des noeuds est la meme sur les deux matrices
                    */
                    listeNoeudsGrilleResolu.add(grille.mat_res[i][j]);
                }
            }
        }

        //niveauAide = grille.getPrecisionAide();

        if (Aide.techniqueHuitAuMilieu(listeNoeudsGrilleJoueur)){
            return true;
            
        }
        
        if(Aide.techniqueSixSurLeCote(listeNoeudsGrilleJoueur, listeNoeudsGrilleResolu, grille)){
            return true;
            
        }

        if (Aide.techniqueQuatreSurLeCote(listeNoeudsGrilleJoueur, listeNoeudsGrilleResolu, grille)){
            return true;
        }
        return false;
        
    }

    private static boolean techniqueHuitAuMilieu(List<Noeud> list){
        for (Noeud n : list){
            if ((n.getEtiquette() == 8) && (n.getSommeVoisins() < 8)){
                System.out.println(n.getEtiquette());
                return true;
            }
        }
        
        return false;
    }

    private static boolean techniqueSixSurLeCote(List<Noeud> listJoueur, List<Noeud> listResolu, Jeu grille){
        
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getEtiquette() == 6)&&(grille.getNbVoisinReel(listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<6)){
                return true;
            }
        }
        return false;
    }

    private static boolean techniqueQuatreSurLeCote(List<Noeud> listJoueur, List<Noeud> listResolu,Jeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getEtiquette() == 4)&&(grille.getNbVoisinReel(listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<4)){
                return true;
            }
        }
        return false;
    }
}