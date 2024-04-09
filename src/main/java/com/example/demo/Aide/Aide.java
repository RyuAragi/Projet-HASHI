package com.example.demo.Aide;

public interface Aide {

    abstract public Technique detecte();
    abstract public AideStat suivant();
    abstract public AideStat reinit();

    abstract public Technique getTechnique();
    abstract public Int getPrecision();
}
