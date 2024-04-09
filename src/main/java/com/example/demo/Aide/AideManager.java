/** Classe AideManager qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/

package com.example.demo.Aide;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.Technique.*;
import com.example.demo.game.*;

public class AideManager implements Aide{
    private List<TechniqueInter> listTechniqueDetecte ;

    private TechniqueInter techniqueCourante;
    private Integer precision;
    static private AideManager instance = null;

    private AideManager(){
        listTechniqueDetecte = new ArrayList<>();
        precision = 0;

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
        TechniqueInter tmp;
        for (TechniqueInter t : listTechniqueDetecte){

            if ((tmp = t.detecte(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille))!=null){
                if (tmp == t){
                    precision += 1;
                    if (precision > 3) precision = 3;
                }else{
                    precision = 0;
                }
                
                techniqueCourante = t;
                return techniqueCourante;
            }

        }
        return null;

    }


    /**
     * @return renvoie les informations nécessaires à l'affichage de l'aide
     */
    @Override
    public Integer getPrecision(){
        return precision;
    }

    public TechniqueInter getTechnique(){
        return techniqueCourante;
    }
}