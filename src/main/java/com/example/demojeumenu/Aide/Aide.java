package com.example.demojeumenu.Aide;

import com.example.demojeumenu.Technique.*;
import com.example.demojeumenu.game.GrilleJeu;

public interface Aide {
    abstract public TechniqueInter detecte(GrilleJeu grille);
    abstract public TechniqueInter getTechnique();
    abstract public Integer getPrecision();
}
