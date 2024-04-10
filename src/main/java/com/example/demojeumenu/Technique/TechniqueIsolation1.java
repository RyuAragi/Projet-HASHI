package com.example.demojeumenu.Technique;

import java.util.List;

import com.example.demojeumenu.controler.MenuTechniqueIso1;
import com.example.demojeumenu.game.GrilleJeu;
import com.example.demojeumenu.game.Ile;
import com.example.demojeumenu.game.IleJoueur;
import javafx.stage.Stage;

public class TechniqueIsolation1 extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIsolation1(){
        courante = null;
    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIsolation1();
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
        IleJoueur j;
        for (Ile n : listJoueur){
            if ((n.getValIle() == 1) &&(grille.getNbVoisinReel((IleJoueur) n)>1)&& (n.getSommeVoisins() < 1) ){

                j=grille.getVoisinDir((IleJoueur)n,"E");
                if(j!=null){
                    if (j.getValIle() == 1 ){
                        res.setIle(n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"O");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle(n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"N");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle(n);
                        return res;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"S");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        res.setIle(n);
                        return res;
                    }
                }
            }
        }
        res.setIle(null);
        return res;
    }

    public String getNomTechnique(){
        return super.getNomTechnique() + " « Ile 1 ayant au moins une ile voisine de même valeur » ";
    }


    @Override
    public String getFichierFXML(){
        return "MenuTechniqueIso1.fxml";
    }

    @Override
    public void setStage(Stage st){
        MenuTechniqueIso1.setStage(st);
    }
}
