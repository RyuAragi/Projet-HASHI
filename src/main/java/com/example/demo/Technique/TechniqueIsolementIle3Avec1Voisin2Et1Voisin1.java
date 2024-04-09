package com.example.demo.Technique;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.game.GrilleJeu;
import com.example.demo.game.Ile;
import com.example.demo.game.IleJoueur;

public class TechniqueIsolementIle3Avec1Voisin2Et1Voisin1 extends Technique{
    static private TechniqueInter technique = null;

    private TechniqueIsolementIle3Avec1Voisin2Et1Voisin1(){
        courante = null;

    }

    /**
     * renvoie l'instance de la technique
     * @return l'instance unique de la technique
     */
    synchronized static public TechniqueInter getInstance(){
        if (technique == null){
            technique = new TechniqueIsolementIle3Avec1Voisin2Et1Voisin1();
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

    public String getNomTechnique(){
        return super.getNomTechnique() + "ile avec comme valeur trois ayant plus d'un voisin dont un de valeur un et un de valeur deux est applicable ici ";
    }

    @Override
    public String getFichierFXML(){
        return "MenuTechniqueIso2.fxml";
    }
}
