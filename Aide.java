
/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0
*/

import java.util.*;


public class Aide{

    private static Aide instance = null;


    private IleJoueur cibleTechnique;
    private int niveauxPrecision;

    private Aide(){
        niveauxPrecision = 0;
    }

    public synchronized Aide getInstance(){
        if (instance == null){
            instance = new Aide();
        }
        return instance;
    }
    

    public int getNiveauxPrecision(){
        return niveauxPrecision;
    }

    /**
     * Méthode qui permet de détecter si une technique de départ est applicable
     * @param grille
     * @return vrai si une technique est applicable, faux sinon
     */
    static boolean testTechnique(GrilleJeu grille){
        List<Ile> listeIlesGrilleJoueur  = new ArrayList<>();
        List<Ile> listeIlesGrilleResolu = new ArrayList<>();

        int i,j;

        //Récupère toutes les îles non nuls de la grille de jeu
        for(i = 0; i < grille.getNbLigne(); i++){
            for(j = 0; j < grille.getNbColonne(); j++){
                if (grille.getIleGrilleJoueur(i, j) != null){
                    listeIlesGrilleJoueur.add(grille.getIleGrilleJoueur(i, j));

                    /*Pas necessaire de comparer dans les deux matrice ()
                    * L'emplacement des noeuds est la meme sur les deux matrices
                    */
                    listeIlesGrilleResolu.add(grille.getIleGrilleSolution(i, j));
                }
            }
        }


        if (Technique.techniqueHuitAuMilieu(listeIlesGrilleJoueur) != null){
            System.out.println("Huit au milieu detecte");
            //return true;
            
        }
        
        if(Technique.techniqueSixSurLeCote(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Six sur le cote detecte");
            //return true;
            
        }

        if (Technique.techniqueQuatreDansUnCoin(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Quatre dans un angle detecte");
            //return true;
        }

        if (Technique.techniqueCinqSurLeCote(listeIlesGrilleJoueur,listeIlesGrilleResolu,grille)!= null){
            System.out.println("techniqueCinqSurLeCote");
        }

        if (Technique.techniqueQuatreSurLeCote(listeIlesGrilleJoueur,listeIlesGrilleResolu,grille) != null){
            System.out.println("techniqueQuatreSurLeCote");
        }

        if (Technique.techniqueTroisDansUnCoin(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("techniqueTroisDansUnCoin");
        }

        if(Technique.techniqueIleUnSeulVoisin(listeIlesGrilleJoueur,grille)!= null){
            System.out.println("techniqueIleUnSeulVoisin");
        }
    
        if(Technique.techniqueIsolation2(listeIlesGrilleJoueur,grille)!=null){
            System.out.println("techniqueIsolation2");
        }

        if(Technique.techniqueIsolation1(listeIlesGrilleJoueur, grille)!= null){
            System.out.println("techniqueIsolation1");
        }

        if(Technique.techniqueIsolementIle2Avec2Voisins1(listeIlesGrilleJoueur, grille)!= null){
            System.out.println("techniqueIsolementIle2Avec2Voisins1");
        }

        if(Technique.techniqueIsolementIle3Avec1Voisin2Et1Voisin1(listeIlesGrilleJoueur, grille)!= null){
            System.out.println("techniqueIsolementIle3Avec1Voisin2Et1Voisin1");
        }
        return false;
        
    }
}