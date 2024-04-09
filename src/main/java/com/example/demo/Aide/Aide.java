package com.example.demo.Aide;

import com.example.demo.Technique.*;
import com.example.demo.game.GrilleJeu;

public interface Aide {
    abstract public TechniqueInter detecte(GrilleJeu grille);
    abstract public TechniqueInter getTechnique();
    abstract public Integer getPrecision();
}
