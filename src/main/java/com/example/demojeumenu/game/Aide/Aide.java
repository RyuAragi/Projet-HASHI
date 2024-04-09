package com.example.demojeumenu.game.Aide;

public interface Aide {

    abstract public TechniqueInter detecte(GrilleJeu grille);

    abstract public TechniqueInter getTechnique();
    abstract public Integer getPrecision();
}
