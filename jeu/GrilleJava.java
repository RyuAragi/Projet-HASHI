/**
 * Cette classe représente une grille de jeu de hashi
 * @author Coupé Xavier
 * @version 0.1
 */

import java.io.*;


public class GrilleJava {
    //Représente la grille sur laquelle joue le joueur 
    private Case[][] joueur;
    //Représente la grille sur laquelle joue le joueur en mode hypothèse
    private Case[][] joueur_hypo;
    //Représente la grille avant la première erreur détectée
    private Case[][] erreur;
    //Représente la solution de la grille 
    private Case[][] solution;
    
    //Le nombre de ligne de la grille
    private int nbLigne;
    //Le nombre de colonne de la grille
    private int nbColonne;

    //Le score du joueur
    private double score ;
    //Le nombre de pont total de posés
    private double nbPontTotal;
    //Le nombre total d'aide utilisé
    private double nbAide;


    /**
     * Constructeur de GrilleJava
     * @param path le chemin vers le fichier de la grille 
     */
    GrilleJava(String path){
        charge(path);

        nbPontTotal = 0;
    }

    /**
     * Charge un fichier passé en parametre et insère les données dans les grilles correspondantes
     * @param path Le chemin du fichier a charger
     */
    private void charge(String path){
        try{
            FileReader file = new FileReader(new File(path));
            BufferedReader reader = new BufferedReader(file);

            String ligne; // Sert a stocker chaque ligne du fichier 'path'
            int valeurIle;
            int i = 0;
            
            while((ligne = reader.readLine())!= null){
                String[] data ; 
                data = ligne.split(" ");
                
                if (i == 0){
                    //Récupère la taille de la matrice
                    nbLigne = Integer.valueOf(data[0]);
                    nbColonne = Integer.valueOf(data[1]);

                    //Initialisation des grille 
                    joueur = new Case[nbLigne][nbColonne];
                    joueur_hypo = new Case[nbLigne][nbColonne];
                    erreur = null;
                    solution = new Case[nbLigne][nbColonne];
                }else{
                    //Récupère chaque ile de la matrice
                    
                    //Calcul de la valeur de l'ile
                    valeurIle = Integer.valueOf(data[2])+Integer.valueOf(data[3])+Integer.valueOf(data[4])+Integer.valueOf(data[5]);

                    /*
                     * Le nombre de voisin de la grille du joueur est initialisé à 0
                     */
                    setGrilleJoueur(Integer.valueOf(data[0]), Integer.valueOf(data[1]), new Ile(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurIle,0,0,0,0));

                    /*
                     * On remplit la solution
                     */
                    setGrilleSolution(Integer.valueOf(data[0]), Integer.valueOf(data[1]), new Ile(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurIle,Integer.valueOf(data[2]),Integer.valueOf(data[3]),Integer.valueOf(data[4]),Integer.valueOf(data[5])));
                }
                i+=1;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * 
     * @param x la coordonnée x de la grille du joueur
     * @param y la coordonnée y de la grille du joueur
     * @param val La valeur a affecté
     */
    public void setGrilleJoueur(int x,int y, Ile val){
        joueur[x][y] = val;
    }

    /**
     * 
     * @param x la coordonnée x de la grille solution
     * @param y la coordonnée y de la grille solution
     * @param val La valeur a affecté
     */
    public void setGrilleSolution(int x,int y, Case val){
        solution[x][y] = val;
    }

    public Ile getIleGrilleSolution(int x, int y){
        return (Ile)solution[x][y];
    }


    /*
     * --------------------------------
     * A TESTER // A TESTER // A TESTER
     * --------------------------------
     */
    private void copieGrille(Case[][] src, Case[][] dst){
        int i = 0;
        int j = 0;
        for (i = 0; i< nbLigne; i++){
            for(j = 0; j < nbColonne; j++){
                dst[i][j] = src[i][j];
            }
        }
    }
    /**
     * Méthode qui vérifie si deux îles sont correctements reliées
     * @param n1 la première île
     * @param n2 la seconde île
     * @return true ou false en fonction de si le pont est valide ou pas
     */
    private void verifPont(Ile i1, Ile i2){
        //Il faut s'assurer que i1 et i2 soit bien deux réels voisins
        if (erreur == null){

            if (i1.getX() < i2.getX()){
                if(i1.getValPontDir("E")<= getIleGrilleSolution(i1.getX(),i1.getY()).getValPontDir("E"))  return;
            }if(i1.getX() > i2.getX()){
                if(i1.getValPontDir("O") <= getIleGrilleSolution(i1.getX(), i1.getY()).getValPontDir("O")) return;
            }if(i1.getY() > i2.getY()){
                if(i1.getValPontDir("N") <= getIleGrilleSolution(i1.getX(),i1.getY()).getValPontDir("N")) return;
            }else{
                if(i1.getValPontDir("S") <= getIleGrilleSolution(i1.getX(), i1.getY()).getValPontDir("S")) return;

            }
            erreur = new Case[nbLigne][nbColonne];
            copieGrille(joueur, erreur);
        }
        return;
    }

    /**
     * Méthode permettant de poser un pont entre 2 iles
     * @param i1 correspond a l'ile 1
     * @param i2 correspond a l'ile 2
     */
    void poserPont(Ile i1, Ile i2){
        int valXI1 = i1.getX();
        int valYI1 = i1.getY();

        int valXI2 = i2.getX();
        int valYI2 = i2.getY();

        if(valXI1 < valXI2){
            i1.incrementValPontDir("E");
            i2.incrementValPontDir("O");
        }else if(valXI1 > valXI2){
            i1.incrementValPontDir("O");
            i2.incrementValPontDir("E");
        }else if(valYI1 > valYI2){
            i1.incrementValPontDir("S");
            i2.incrementValPontDir("N");
        }else{
            i1.incrementValPontDir("N");
            i2.incrementValPontDir("S");
        }
        nbPontTotal += 1;

        //A chaque pont posé entre deux îles, on le vérifie
        verifPont(i1, i2);
    }


    /**
     * Vérifie la grille et la modifie en conséquence
     * @return vrai si la grille est correct, faux sinon
     */
    boolean verifMatrice(){
        if (erreur != null){
            copieGrille(joueur, erreur);
            erreur =null;

            return false;
        }

        return true;
    }

    
}
