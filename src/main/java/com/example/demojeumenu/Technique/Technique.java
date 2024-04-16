/** Classe technique qui détecte les techniques utilisable
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier
* @version 0.0
*/

package com.example.demojeumenu.Technique;

import com.example.demojeumenu.game.Ile;
/**
 * Classe abstraite qui représente une technique
 */
abstract public class Technique implements TechniqueInter{
    /**
     * L'ile visée par la technique
     */
    protected Ile courante ;


    /**
     * Renvoie l'ile visée par la technique
     * @return une ile
     */
    @Override
    public Ile getIle(){
        return courante;
    }


    /**
     * modifie l'ile visée par la technique
     * @param i l'ile 
     */
    @Override
    public void setIle(Ile i){
        courante = i;
    }

    @Override 
    public String getNomTechnique(){
        return "La technique";
    }
    
}