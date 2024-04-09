package com.example.demo.Technique;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.game.GrilleJeu;
import com.example.demo.game.Ile;
import com.example.demo.game.IleJoueur;

public class TechniqueIsolementIle2Avec2Voisins1 extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIsolementIle2Avec2Voisins1(){
        courante = null;

    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIsolementIle2Avec2Voisins1();
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
        int cpt = 0;
        for (Ile n: listJoueur){
            listVoisin = grille.getListVoisinReel((IleJoueur)n);

            for(IleJoueur i : listVoisin){
                if(i.getValIle() == 1){
                    cpt++;
                    if (cpt == 2){
                        res.setIle((IleJoueur)n);
                        return res;
                    }
                }
            }
            cpt = 0;
        }

        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + "ile avec comme valeur deux ayant plus d'un voisin dont deux de valeur un est applicable ici ";
    }

    @Override
    public String getFichierFXML(){
        return "MenuTechniqueIso2.fxml";
    }
}
