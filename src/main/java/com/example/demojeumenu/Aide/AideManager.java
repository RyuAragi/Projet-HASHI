/** Classe AideManager qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0 
*/

package com.example.demojeumenu.Aide;

import java.util.*;

import com.example.demojeumenu.Technique.*;
import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;

public class AideManager implements Aide{
    private final List<TechniqueInter> listTechniqueDetecte ;
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
        Ile ile = null;
        if(techniqueCourante!=null){
            ile = techniqueCourante.getIle();
        }
        for (TechniqueInter t : listTechniqueDetecte){
            if ((tmp = t.detecte(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille))!=null && tmp.getIle()!=null){
                if ((tmp == techniqueCourante) && ile!=null && (tmp.getIle() == ile)){
                    precision += 1;
                    if (precision > 3) precision = 3;
                }else{
                    precision = 1;
                    techniqueCourante = tmp;
                }
                return techniqueCourante;
            }

        }
        precision = 0;
        return null;
    }


    /**
     * @return renvoie les informations nécessaires à l'affichage de l'aide
     */
    @Override
    public Integer getPrecision(){
        return precision;
    }

    public void decrementePrecision(){
        this.precision--;
    }

    public TechniqueInter getTechnique(){
        return techniqueCourante;
    }
}