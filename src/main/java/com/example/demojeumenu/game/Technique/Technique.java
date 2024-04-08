/** Classe technique qui détecte les techniques utilisable
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier
* @version 0.0
*/

package com.example.demojeumenu.game.Technique;

import com.example.demojeumenu.game.IleJoueur;

abstract public class Technique implements TechniqueInter{

    protected IleJoueur courante ;
    protected String description;



    /**
     * Renvoie l'ile visée par la technique
     * @return une ile
     */
    @Override
    public IleJoueur getIle(){
        return courante;
    }

    /**
     * Renvoie la description de la technique
     * @return la description
     */
    @Override
    public String getDescription(){
        return description;
    }  

    /**
     * modifie l'ile visée par la technique
     * @param i l'ile 
     */
    @Override
    public void setIle(IleJoueur i){
        courante = i;
    }

    @Override 
    public String getNomTechnique(){
        return "Une technique ";
    }

    
}