/**
 * Cette classe représente une ile dans la grille du joueur
 * @author Coupé Xavier, Georget Rémy 
 * @version 0.1
 */

package com.example.demojeumenu.game;

import java.io.Serializable;
import java.util.*;

public class IleJoueur extends Ile implements Serializable {

    //Représente le nombre max de pont pouvant être connecté sur un côté de l'ile
    final int NB_PONT_MAX = 2;
    //Stock les ponts posés en fonction de la direction
    private final HashMap<String, List<Pont>> pontRelie;


    public IleJoueur(int cX, int cY, int valIle_){
        super(cX, cY, valIle_);
        pontRelie = new HashMap<>();
        pontRelie.put("N", new ArrayList<Pont>());
        pontRelie.put("S", new ArrayList<Pont>());
        pontRelie.put("E", new ArrayList<Pont>());
        pontRelie.put("O", new ArrayList<Pont>());
    }

    /**
     * Méthode qui compte la somme des voisins
     * @return la somme des voisins d'un noeud
     */
    public int getSommeVoisins(){
        int cpt = 0;
        for(Map.Entry<String, List<Pont>> ile : pontRelie.entrySet()){
            cpt += ile.getValue().size();
        }
        return cpt;
    }

    /**
     * Méthode qui renvoie le nb de pont en fonction d'une direction
     * @param dir la direction désirée
     * @return Le nombre de pont associé
     */
    public int getValPontDir(String dir){
        return pontRelie.get(dir).size();
    }

    public List<Pont> getPontDir(String dir){
        return pontRelie.get(dir);
    }

    /**
     * Renvoie le nombre maximal de pont accepté
     * @return le nombre de pont maximal pour une direction
     */
    public int getMaxPont(){
        return NB_PONT_MAX;
    }

    /**
     * Récupère la liste de pont pour une direction donnée
     * @param dir une direction
     * @return la liste des ponts associée
     */
    public List<Pont> getListePonts(String dir){
        return pontRelie.get(dir);
    }

    /**
     * Supprime le pont passe en parametre pour une direction donnée
     * @param dir la direction
     * @param p le pont a supprimé
     */
    public void supprimePont(String dir, Pont p){
        pontRelie.get(dir).remove(p);
    }

    public void reinitPont(String dir){
        pontRelie.remove(dir);
        pontRelie.put(dir, new ArrayList<Pont>());
    }


    /**
     * Ajoute un pont pour une direction donnée
     * @param dir la direction a donnée
     * @param p le pont a ajouté
     */
    public void ajoutePontList(String dir, Pont p){
        pontRelie.get(dir).add(p);
    }
    /**
     * Ajoute un pont dans la direction souhaitée
     * @param dir La direction souhaitée
     */
    void ajoutePontHypothese(String dir, Ile j){
        if(pontRelie.get(dir).size() == 3){
            for(Pont p: pontRelie.get(dir) ){
                if (p.estHypothese()){
                    pontRelie.get(dir).remove(p);
                }
            }
        }
        else{
            pontRelie.get(dir).add(new Pont(this,(IleJoueur) j, true));
        }
    }

    /**
     * Methode permettant d'obtenir la direction qui contient le pont passé en paramètre.
     *
     * @param p
     * @return La direction
     * @throws NoSuchElementException
     */
    public String getPontDirection(Pont p) throws NoSuchElementException{
        for(Map.Entry<String, List<Pont>> elem : this.pontRelie.entrySet()) {
            if(elem.getValue().contains(p)) {
                return elem.getKey();
            }
        }
        //throw new NoSuchElementException();
        return null;
    }
}