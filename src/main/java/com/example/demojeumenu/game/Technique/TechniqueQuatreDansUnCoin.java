package com.example.demojeumenu.game.Technique;

import java.util.List;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;

public class TechniqueQuatreDansUnCoin extends Technique {
    static private TechniqueInter technique = null;

    private TechniqueQuatreDansUnCoin(String descri){
        courante = null;

        description = descri;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueQuatreDansUnCoin("Cette technique vise une ile qui a comme valeur quatre et qui a deux voisins. Il faut relier deux ponts avec chaque voisins de l'ile");
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
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<4)){
                res.setIle((IleJoueur)listResolu.get(i));
                return res;
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + "quatre dans un coin est applicable ici ";
    }
}
