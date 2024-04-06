package com.example.demojeumenu.game.Technique;

import java.util.ArrayList;
import java.util.List;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;

public class TechniqueIsolementIle3Avec1Voisin2Et1Voisin1 extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIsolementIle3Avec1Voisin2Et1Voisin1(String descri){
        courante = null;

        description = descri;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIsolementIle3Avec1Voisin2Et1Voisin1("Cette technique vise une ile qui a comme valeur un avec plus d'un voisin, et il faut qu'un de ses voisins soit de valeur un et un autre de valeur deux. Dans cette configuration il ne faut pas relier l'ile et ses voisins ensemble");
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
        List<IleJoueur> listVoisin = new ArrayList<IleJoueur>();
        Boolean deux = false;
        Boolean un = false;

        for (Ile n: listJoueur){
            listVoisin = grille.getListVoisinReel((IleJoueur)n);

            for(IleJoueur i : listVoisin){
                if(i.getValIle() == 1){
                    un = true;
                    
                }else if(i.getValIle() == 2){
                    deux = true;
                }
                if(un == true && deux == true){
                    res.setIle((IleJoueur)n);
                    return res;
                }
            }
            un = false;
            deux = false;
        }
        res.setIle(null);
        return res;
    }
}
