package com.example.demojeumenu.game.Technique;

import java.util.List;

import com.example.demojeumenu.game.IleJoueur;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.GrilleJeu;

public class TechniqueSeptAuMilieu extends Technique {
    static private TechniqueInter technique = null;


    private TechniqueSeptAuMilieu(String descri){
        courante = null;

        description = descri;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueSeptAuMilieu("Cette technique vise une ile qui a comme valeur, 7. Avec cette configuration il faut relié tous les voisins de l'ile avec un pont au minimum ");
        }
        return technique;
    }

    /**
     * Regarde si la technique est applicable
     * @param listJoueur la liste d'ile du joueur
     * @param listResolu la liste d'ile résolu
     * @param grill la grille de jeu
     * @return La technique si elle trouvée, null sinon
     */
    @Override
    public TechniqueInter detecte(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grill){
        TechniqueInter res;
        res = getInstance();
        for (Ile n : listJoueur){
            if ((n.getValIle() == 7) && (n.getSommeVoisins() < 7)){
                res.setIle((IleJoueur)n);
                return res;
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + "sept au milieu est applicable ici ";
    }
}
