package com.example.demojeumenu.game.Aide;

public class AidePrecision3 implements AideStat {
    Technique tDetecte;

    static private AideStat as  = null ;

    private AidePrecision3(){
        tDetecte = null;
    }

    static public AideStat getInstance(){
        if (as == null){
            as = new AidePrecision3();
        }
        return as;
    }


    /**
     * remplace la technique enregistrée par celle envoyée en paramètre
     * @param t la technique a mettre
     */
    @Override
    public void setTechnique(Technique t){
        tDetecte = t;
    }


    /**
     * 
     * @return renvoie le niveau suivant de precision de l'aide 
     */
    @Override
    public AideStat suivant(){
        return AidePrecision3.getInstance();
    }

    /**
     * 
     * @return renvoie le niveau 1 de precision de l'aide 
     */
    @Override
    public AideStat reinit(){
        return AidePrecision1.getInstance();
    }

    /**
     * @return renvoie les informations nécessaires à l'affichage de l'aide
     */
    @Override
    public String getStat(){
        return "";
    }
}
