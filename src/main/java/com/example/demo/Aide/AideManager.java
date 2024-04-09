/** Classe AideManager qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/

package com.example.demo.Aide;

import java.util.*;

import com.example.demo.Technique.TechniqueInter;
import com.example.demo.Technique.*;
import com.example.demo.game.GrilleJeu;
import com.example.demo.game.Ile;

public class AideManager implements Aide{
    private List<TechniqueInter> listTechniqueDetecte ;

    private TechniqueInter techniqueCourante;
    static private AideManager instance = null

    private AideManager(){
        aide = AidePrecision1.getInstance();
        listTechniqueDetecte = new ArrayList<>();

        listTechniqueDetecte.add(TechniqueHuitAuMilieu.getInstance());
        listTechniqueDetecte.add(TechniqueQuatreSurLeCote.getInstance());
        listTechniqueDetecte.add(TechniqueSixSurLeCote.getInstance());
        listTechniqueDetecte.add(TechniqueIleUnSeulVoisin.getInstance());
        listTechniqueDetecte.add(TechniqueTroisDansUnCoin.getInstance());
        listTechniqueDetecte.add(TechniqueCinqSurLeCote.getInstance());
        listTechniqueDetecte.add(TechniqueSeptAuMilieu.getInstance());
        listTechniqueDetecte.add(TechniqueTroisDansUnCoin.getInstance());
        listTechniqueDetecte.add(TechniqueQuatreSurLeCote.getInstance());
        listTechniqueDetecte.add(TechniqueIsolation1.getInstance());
        listTechniqueDetecte.add(TechniqueIsolationDeux.getInstance());
        listTechniqueDetecte.add(TechniqueIsolementIle2Avec2Voisins1.getInstance());
        listTechniqueDetecte.add(TechniqueIsolementIle3Avec1Voisin2Et1Voisin1.getInstance());

    }
    
    /**
     * Renvoie l'instance de l'aide manager si elle existe ou la crée
     * @return L'instance de l'aide manager
     */
    synchronized static public AideManager getInstance(){
        if(instance == null){
            instance = new AideManager();
        }
        return instance;
    }
    

    /**
     * Méthode qui permet de détecter si une technique de départ est applicable
     * @param grille
     * @return la technique applicable ,null si il en existe pas
     */
    @Override
    public TechniqueInter detecte(GrilleJeu grille){
        List<Ile> listeIlesGrilleJoueur  = new ArrayList<>();
        List<Ile> listeIlesGrilleResolu = new ArrayList<>();

        int i,j;

        //Récupère toutes les îles non nuls de la grille de jeu
        for(i = 0; i < grille.getNbLigne(); i++){
            for(j = 0; j < grille.getNbColonne(); j++){
                if (grille.getIleGrilleJoueur(i, j) != null){
                    listeIlesGrilleJoueur.add(grille.getIleGrilleJoueur(i, j));
                    listeIlesGrilleResolu.add(grille.getIleGrilleSolution(i, j));
                }
            }
        }

        for (TechniqueInter t : listTechniqueDetecte){
            t.detecte(listeIlesGrilleJoueur, listeIlesGrilleResolu,grille);

            if(t.getIle()!=null){
                techniqueCourante = t;
                return techniqueCourante;
            }
        }
        return null;

    }

    /**
     * 
     * @return renvoie le niveau suivant de precision de l'aide 
     */
    @Override
    public AideStat suivant(){
        aide = aide.suivant();
        return aide;
    }

    /**
     * 
     * @return renvoie le niveau 1 de precision de l'aide 
     */
    @Override
    public AideStat reinit(){
        aide = aide.reinit();
        return aide;
    }

    /**
     * @return renvoie les informations nécessaires à l'affichage de l'aide
     */
    @Override
    public String getStat(){
        return aide.getStat();
    }

    public TechniqueInter getTechnique(){
        return techniqueCourante;
    }

}