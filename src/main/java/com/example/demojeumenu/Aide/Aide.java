package com.example.demojeumenu.Aide;

import com.example.demojeumenu.Technique.*;
import com.example.demojeumenu.game.GrilleJeu;

/**
 * Interface pour les aides
 */

public interface Aide {

    /**
     * Detecte si une technique est applicable
     * @param grille la grille de jeu
     * @return la technique si elle est trouvée, null sinon
     */
    TechniqueInter detecte(GrilleJeu grille);

    /**
     * Renvoie le nom de l'aide
     * @return le nom de l'aide
     */
    TechniqueInter getTechnique();

    /**
     * Renvoie la précision de l'aide
     * @return la précision de l'aide
     */
    Integer getPrecision();
}
