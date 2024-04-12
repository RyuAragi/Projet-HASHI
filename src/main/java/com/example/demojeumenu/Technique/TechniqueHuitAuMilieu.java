package com.example.demojeumenu.Technique;

import java.util.List;

import com.example.demojeumenu.controler.MenuTechniqueDeb1;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.GrilleJeu;
import javafx.stage.Stage;

public class TechniqueHuitAuMilieu extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueHuitAuMilieu(){
        courante = null;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueHuitAuMilieu();
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
            if ((n.getValIle() == 8) && (n.getSommeVoisins() < 8)){
                res.setIle(n);
                return res;
            }
        }
        res.setIle(null);
        return res;
    }


    public String getNomTechnique(){
        return super.getNomTechnique() + " « Huit au milieu » ";
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
