package com.example.demojeumenu.game.Aide;

public interface Aide {

    abstract public Technique detecte();
    abstract public AideStat suivant();
    abstract public AideStat reinit();
    abstract public String getStat();
}
