package com.example.demojeumenu.game.Technique;

import java.util.List;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;

public class TechniqueIsolation1 extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIsolation1(String descri){
        courante = null;

        description = descri;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIsolation1("Cette technique vise une ile qui a comme valeur un avec plus d'un voisin, et il faut qu'un de ses voisins soit de valeur un. Dans cette configuration il ne faut pas relier l'ile et son voisins ensemble");
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
    public TechniqueInter detecte(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        TechniqueInter res;
        res = getInstance();
        IleJoueur j;
        for (Ile n : listJoueur){
            if ((n.getValIle() == 1) && (n.getSommeVoisins() < 1) ){

                j=grille.getVoisinDir((IleJoueur)n,"E");
                if(j!=null){
                    if (j.getValIle() == 1 ){
                        res.setIle((IleJoueur)n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"O");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle((IleJoueur)n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"N");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle((IleJoueur)n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"S");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle((IleJoueur)n);
                        return res;
                    }
                }
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + "ile avec comme valeur un ayant plus d'un voisin dont un de même valeur que l'ile est applicable ici ";
    }
}
