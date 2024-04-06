package com.example.demojeumenu.game.Technique;

import java.util.List;
import com.example.demojeumenu.game.IleJoueur;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.GrilleJeu;
/**
 * Interface représent les messages communs que toutes les techniques peuvent répondre
 */
public interface TechniqueInter {
    /**
     * Regarde si la technique est applicable
     * @param listJoueur la liste d'ile du joueur
     * @param listResolu la liste d'ile résolu
     * @param grill la grille de jeu
     * @return La technique si elle trouvée, null sinon
     */
    public abstract TechniqueInter detecte(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grill);

    /**
     * Renvoie l'ile visée par la technique
     * @return une ile
     */
    public abstract IleJoueur getIle();

    /**
     * Renvoie la description de la technique
     * @return la description
     */
    public abstract String getDescription();  

    

    /**
     * modifie l'ile visée par la technique
     * @param i l'ile 
     */
    public abstract void setIle(IleJoueur i);

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    static public TechniqueInter getInstance(){
        return null;
    }
}
