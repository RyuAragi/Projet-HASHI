/** Classe aide qui lance la détection de technique et l'aide à la résolution
 * Toutes les méthodes de cette classe sont statiques
* @author COUPE Xavier FOUQUERAY Victor
* @version 0.0
*/
import java.util.*;


public class Aide{


    /**
     * Méthode qui permet de détecter si une technique de départ est applicable
     * @param grille
     * @return vrai si une technique est applicable, faux sinon
     */
    static boolean techniqueDeDepart(GrilleJeu grille){
        List<Ile> listeIlesGrilleJoueur  = new ArrayList<>();
        List<Ile> listeIlesGrilleResolu = new ArrayList<>();

        int i,j;

        //Récupère toutes les îles non nuls de la grille de jeu
        for(i = 0; i < grille.getNbLigne(); i++){
            for(j = 0; j < grille.getNbColonne(); j++){
                if (grille.getIleGrilleJoueur(i, j) != null){
                    listeIlesGrilleJoueur.add(grille.getIleGrilleJoueur(i, j));

                    /*Pas necessaire de comparer dans les deux matrice ()
                    * L'emplacement des noeuds est la meme sur les deux matrices
                    */
                    listeIlesGrilleResolu.add(grille.getIleGrilleSolution(i, j));
                }
            }
        }




        if (Aide.techniqueHuitAuMilieu(listeIlesGrilleJoueur) != null){
            System.out.println("Huit au milieu detecte");
            return true;
            
        }
        
        if(Aide.techniqueSixSurLeCote(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Six sur le cote detecte");
            return true;
            
        }

        if (Aide.techniqueQuatreDansUnCoin(listeIlesGrilleJoueur, listeIlesGrilleResolu, grille) != null){
            System.out.println("Quatre dans un angle detecte");
            return true;
        }
        return false;
        
    }


    /**
     * Méthode permettant de detecter un 8 entouré de 4 îles
     * @param list la liste des iles du joueur
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueHuitAuMilieu(List<Ile> list){
        for (Ile n : list){
            if ((n.getValIle() == 8) && (n.getSommeVoisins() < 8)){
                return n;
            }
        }
        
        return null;
    }

    /**
     * Méthode permettant de detecter un 7 entouré de 4 îles
     * @param list la liste des iles du joueur
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueSeptAuMilieu(List<Ile> list){
        for (Ile n :list){
            if((n.getValIle() == 7) && (n.getSommeVoisins() < 7)){
                return n;
            }
        }
        return null;
    }


    
    /**
     * Méthode permettant de détecter un 6 entouré de 3 îles
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueSixSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 6)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<6)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de détecter un 5 entouré de 3 îles
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille du jeu_
     * @return
     */
    private static Ile techniqueCinqSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 5)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<5)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de détecter un 4 entourer de 3 îles
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille du jeu_
     * @return
     */
    private static Ile techniqueQuatreSurLeCote(List<Ile> listJoueur, List<Ile> listResolu, GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 3) && (listJoueur.get(i).getSommeVoisins()<4)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de détecter un 4 entourer de 2 îles
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueQuatreDansUnCoin(List<Ile> listJoueur, List<Ile> listResolu,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 4)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<4)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter un 3 entre 2 îles
     * @param listJoueur la liste des iles du joueur
     * @param listResolu la liste des iles de la grille résolu
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueTroisDansUnCoin(List<Ile> listJoueur, List<Ile> listResolu,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if ((listResolu.get(i).getValIle() == 3)&&(grille.getNbVoisinReel((IleJoueur)listResolu.get(i)) == 2) && (listJoueur.get(i).getSommeVoisins()<3)){
                return listResolu.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter une île ne possédant qu'un seul voisin
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueIleUnSeulVoisin(List<Ile> listJoueur,GrilleJeu grille){
        for(int i = 0 ; i< listJoueur.size(); i++){
            if(grille.getNbVoisinReel((IleJoueur)listJoueur.get(i)) == 1){
                return listJoueur.get(i);
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter un 6 possédant 4 voisin dont l'un d'entre eux est un 1
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueSixMilieuSpecial(List<Ile> listJoueur, GrilleJeu grille){
        IleJoueur j;
        for (Ile n : listJoueur){
            if ((n.getValIle() == 6) && (n.getSommeVoisins() < 6)){

                j=grille.getVoisinDir((IleJoueur)n,"E");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"O");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"N");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"S");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter un 2 possédant plus de un voisin dont l'un d'entre eux est un 2
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueIsolation2(List<Ile> listJoueur, GrilleJeu grille){
        IleJoueur j;
        for (Ile n : listJoueur){
            if ((n.getValIle() == 2) && (n.getSommeVoisins() < 2) ){

                j=grille.getVoisinDir((IleJoueur)n,"E");
                if(j!=null){
                    if (j.getValIle() == 2) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"O");
                if(j!=null){
                    if (j.getValIle() == 2) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"N");
                if(j!=null){
                    if (j.getValIle() == 2) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"S");
                if(j!=null){
                    if (j.getValIle() == 2) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter un 1 possédant plus de un voisin dont l'un d'entre eux est un 1
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueIsolation1(List<Ile> listJoueur, GrilleJeu grille){
        IleJoueur j;
        for (Ile n : listJoueur){
            if ((n.getValIle() == 1) && (n.getSommeVoisins() < 1) ){

                j=grille.getVoisinDir((IleJoueur)n,"E");
                if(j!=null){
                    if (j.getValIle() == 1 ){
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"O");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"N");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
                j=grille.getVoisinDir((IleJoueur)n,"S");
                if(j!=null){
                    if (j.getValIle() == 1) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Méthode permettant de detecter un 2 possédant plus de un voisin dont deux d'entre eux sont des 1
     * @param listJoueur la liste des iles du joueur
     * @param grille la grille résolu
     * @return l'ile ou la technique est applicable ou null
     */
    private static Ile techniqueIsolementIle2Avec2Voisins1(List<Ile> listJoueur, GrilleJeu grille){
        List<IleJoueur> listVoisin = new ArrayList<IleJoueur>();
        int cpt = 0;
        for (Ile n: listJoueur){
            listVoisin = grille.getListVoisinReel((IleJoueur)n);

            for(IleJoueur i : listVoisin){
                if(i.getValIle() == 1){
                    cpt++;
                    if (cpt == 2){
                        return n;
                    }
                }
            }
            cpt = 0;
        }

        return null;
    }


}