package com.example.demojeumenu.Aide;

import com.example.demojeumenu.Technique.*;
import com.example.demojeumenu.game.GrilleJeu;

public interface Aide {
    TechniqueInter detecte(GrilleJeu grille);
    TechniqueInter getTechnique();
    Integer getPrecision();
}
