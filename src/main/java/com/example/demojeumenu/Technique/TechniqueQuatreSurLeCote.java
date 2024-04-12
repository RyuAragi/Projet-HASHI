package com.example.demojeumenu.Technique;


import com.example.demojeumenu.controler.MenuTechniqueBas3;
import com.example.demojeumenu.game.IleJoueur;
import com.example.demojeumenu.game.Ile;

import java.util.List;

import com.example.demojeumenu.game.GrilleJeu;
import javafx.stage.Stage;

public class TechniqueQuatreSurLeCote extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueQuatreSurLeCote(){
        courante = null;

    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueQuatreSurLeCote();
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
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<4)){
                res.setIle(listResolu.get(i));
                return res;
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + " « Quatre sur un côté » ";
    }

    @Override
    public String getFichierFXML(){
        return "MenuTechniqueBas3.fxml";
    }

    @Override
    public void setStage(Stage st){
        MenuTechniqueBas3.setStage(st);
    }
   
}
