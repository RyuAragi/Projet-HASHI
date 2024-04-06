package com.example.demojeumenu.game.Aide;

abstract public interface AideStat{
    abstract public void setTechnique(Technique);
    abstract public AideStat suivant();
    abstract public AideStat reinit();

    abstract public String getStat();
}
