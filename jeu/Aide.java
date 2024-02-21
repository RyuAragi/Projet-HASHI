/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/
import java.util.*;

public class Aide{


    /**
     * Méthode qui permet de détecter si une technique de départ est applicable
     * @param grille
     * @return vrai si une technique est applicable, faux sinon
     */
    static boolean techniqueDeDepart(GrilleJeu grille){
        List<Ile> listeNoeudsGrilleJoueur  = new ArrayList<>();
        List<Ile> listeNoeudsGrilleResolu = new ArrayList<>();

        int i,j;
        //int niveauAide;

        //Récupère toutes les îles non nuls de la grille de jeu
        for(i = 0; i < grille.getNbLigne(); i++){
            for(j = 0; j < grille.getNbColonne(); j++){
                if (grille.getIleGrilleJoueur(i, j) != null){
                    listeNoeudsGrilleJoueur.add(grille.getIleGrilleJoueur(i, j));

                    /*Pas necessaire de comparer dans les deux matrice ()
                    * L'emplacement des noeuds est la meme sur les deux matrices
                    */
                    listeNoeudsGrilleResolu.add(grille.getIleGrilleSolution(i, j));
                }
            }
        }

        //niveauAide = grille.getPrecisionAide();



        if (Aide.techniqueHuitAuMilieu(listeNoeudsGrilleJoueur)){
            System.out.println("Huit au milieu detecte");
            return true;
            
        }
        
        if(Aide.techniqueSixSurLeCote(listeNoeudsGrilleJoueur, listeNoeudsGrilleResolu, grille)){
            System.out.println("Six sur le cote detecte");
            return true;
            
        }

        if (Aide.techniqueQuatreSurLeCote(listeNoeudsGrilleJoueur, listeNoeudsGrilleResolu, grille)){
            System.out.println("Quatre dans un angle detecte");
            return true;
        }
        return false;
        
    }

    /**
     * 
     * @param list la liste des iles du joueur
     * @return vrai si la technique est détectée, faux sinon
     */
    private static boolean techniqueHuitAuMilieu(List<Ile> list){
        for (Ile n : list){
            if ((n.getValIle() == 8) && (n.getSommeVoisins() < 8)){
                return true;
            }
        }
        
        return false;
    }

    /**
     * 
     * @param listJoueur la liste de noeud du joueur
     * @param listResolu la liste de noeud de la grille résolu
     * @param grille la grille résolu
     * @return vrai si la technique est détectée, faux sinon
     */
    private static boolean techniqueSixSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 6)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<6)){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param listJoueur la liste de noeud du joueur
     * @param listResolu la liste de noeud de la grille résolu
     * @param grille la grille résolu
     * @return vrai si la technique est détectée, faux sinon
     */
    private static boolean techniqueQuatreSurLeCote(List<Ile> listJoueur, List<Ile> listResolu,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<4)){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param listJoueur la liste de noeud du joueur
     * @param grille la grille résolu
     * @return vrai si la technique est détectée, faux sinon
     */
    private static boolean techniqueIleIsolee(List<Ile> listJoueur,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 1){
                return true;
            }
        }
        return false;
    }
}