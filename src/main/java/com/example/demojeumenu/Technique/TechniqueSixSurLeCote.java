package com.example.demojeumenu.Technique;

import java.util.List;

import com.example.demojeumenu.controler.MenuTechniqueDeb1;
import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;
import javafx.stage.Stage;

public class TechniqueSixSurLeCote extends Technique {
    static private TechniqueInter technique = null;


    private TechniqueSixSurLeCote(){
        courante = null;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueSixSurLeCote();
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
            if ((listJoueur.get(i).getValIle() == 6)&&(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i))== 3) && (listJoueur.get(i).getSommeVoisins()<6)){
                res.setIle( listJoueur.get(i));
                return res;
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + " « Six sur un côté » ";
    }

    @Override
    public String getFichierFXML(){
        return "MenuTechniqueDeb1.fxml";
    }

    @Override
    public void setStage(Stage st){
        MenuTechniqueDeb1.setStage(st);
    }
}
