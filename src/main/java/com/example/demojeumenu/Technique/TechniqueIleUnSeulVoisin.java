package com.example.demojeumenu.Technique;

import java.util.List;

import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;

public class TechniqueIleUnSeulVoisin extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIleUnSeulVoisin(){
        courante = null;

    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIleUnSeulVoisin();
        }
        return technique;
    }

    /**
     * Regarde si la technique est applicable
     * @param listJoueur la liste d'ile du joueur
     * @param listResolu la liste d'ile résolu
     * @param grille la grille de jeu
     * @return La technique si elle trouvée, null sinon
     */
    @Override
    public TechniqueInter detecte(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        TechniqueInter res;
        res = getInstance();
        for(int i = 0 ; i< listJoueur.size(); i++){
            if(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 1){
                res.setIle((IleJoueur)listJoueur.get(i));
                return res;
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + "ile avec un seul voisin est applicable ici";
    }


    @Override
    public String getFichierFXML(){
        return "MenuTechniqueDeb2.fxml";
    }
    
}
