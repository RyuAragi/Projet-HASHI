/**
 * Cette classe représente une grille de jeu de hashi
 * @author Coupé Xavier, Georget Rémy
 * @version 0.1
 */

package com.example.demo.game;

import com.example.demo.GrilleControler;
import com.example.demo.Sauvegarde;
import com.example.demo.undoRedo.UndoRedo;
import javafx.animation.Timeline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GrilleJeu implements Serializable{
    //Représente la grille sur laquelle joue le joueur
    private Case[][] joueur;

    //Représente la solution de la grille
    private Case[][] solution;


    //Représente un boolean qui vaut vrai si une erreur a été commise dans la grille
    private Boolean erreur;
    //Représente l'indice de la première erreur rencontrée
    private int premiereErreur;
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

    // Objet undo redo permettant d'effectuer les actions undo/redo
    UndoRedo undoRedo;

    /** Mettre une seule liste on ne peut pas savoir l'avant dernier pont posé avec cette méthode */
    private List<Pont> listPontPose;

    /**
     * Constructeur de GrilleJeu
     * @param reader le chemin vers le fichier de la grille
     */
    public GrilleJeu(InputStreamReader reader){
        charge(reader);

        nbPontTotal = 0;
        listPontPose= new ArrayList<>();
        undoRedo = new UndoRedo();
        erreur = false;
    }

    /**
     * Charge un fichier passé en parametre et insère les données dans les grilles correspondantes
     * @param reader Le chemin du fichier a charger
     */
    private void charge(InputStreamReader reader){
        try{
            BufferedReader bufferedReader = new BufferedReader(reader);

            String ligne; // Sert a stocker chaque ligne du fichier 'path'
            int valeurIle;
            int i = 0;

            while((ligne = bufferedReader.readLine())!= null){
                String[] data ;
                data = ligne.split(" ");

                if (i == 0){
                    //Récupère la taille de la matrice
                    nbLigne = Integer.parseInt(data[0]);
                    nbColonne = Integer.parseInt(data[1]);

                    //Initialisation des grille
                    joueur = new Case[nbLigne][nbColonne];
                    solution = new Case[nbLigne][nbColonne];
                }else{
                    //Récupère chaque ile de la matrice

                    //Calcul de la valeur de l'ile
                    valeurIle = Integer.parseInt(data[2])+Integer.parseInt(data[3])+Integer.parseInt(data[4])+Integer.parseInt(data[5]);

                    /*
                     * Le nombre de voisin de la grille du joueur est initialisé à 0
                     */
                    setGrilleJoueur(Integer.valueOf(data[0]), Integer.valueOf(data[1]), new IleJoueur(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurIle));

                    /*
                     * On remplit la solution
                     */
                    setGrilleSolution(Integer.valueOf(data[0]), Integer.valueOf(data[1]), new IleSolution(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurIle,Integer.valueOf(data[2]),Integer.valueOf(data[3]),Integer.valueOf(data[4]),Integer.valueOf(data[5])));
                }
                i+=1;
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Renvoie le nombre de ligne de la grille de jeu
     * @return le nombre de ligne
     */
    public int getNbLigne(){
        return nbLigne;
    }

    /**
     * Renvoie le nombre de colonne de la grille de jeu
     * @return le nombre de colonne
     */
    public int getNbColonne(){
        return nbColonne;
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
     * Retourne la valeur aux indices indiqués
     * @param x la coordonnée x de la grille solution
     * @param y la coordonnée y de la grille solution
     * @return la valeur correspondante
     */
    public Ile getIleGrilleJoueur(int x, int y){
        return (Ile)joueur[x][y];
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

    /**
     * Retourne la valeur aux indices indiqués
     * @param x la coordonnée x de la grille solution
     * @param y la coordonnée y de la grille solution
     * @return la valeur correspondante
     */
    public Ile getIleGrilleSolution(int x, int y){
        return (Ile)solution[x][y];
    }

    /**
     * permet d'enregistrer dans la liste de pont chaque fois qu'un pont est posé
     * @param p le pont a enregistrer
     */
    private void enregistrePont(Pont p){
        listPontPose.add(p);
    }

    /**
     * permet de mettre à jour la liste de pont chaque fois qu'un pont est retiré
     * @param p le pont a supprimer
     */
    public void supprimePontUndoRedo(Pont p){
        IleJoueur src = p.getSrc();
        IleJoueur dest = p.getDst();
        String directionSrc = src.getPontDirection(p);
        String directionDest = dest.getPontDirection(p);

        if (listPontPose.isEmpty()==false){
            listPontPose.remove(p);
            src.supprimePont(directionSrc, p);
            dest.supprimePont(directionDest, p);
        }

    }

    /**
     * permet de smettre à jour la liste de pont chaque fois qu'un pont est retiré
     * @param p le pont a supprimer
     */
    public void supprimePont(Pont p){

        if (listPontPose.isEmpty()==false){
            listPontPose.remove(p);
        }

    }


    /**
     * Méthode qui remplace tous les ponts hypothèses en pont non hypothèse
     */
    public void valideHypothese(){
        for(Pont p: listPontPose){
            if(p.estHypothese()){
                p.setHypothese(false);
            }
        }
    }

    /**
     * Méthode qui détruit tous les ponts hypothèses
     */
    public void quitteHypothèse(){
        for(Pont p: listPontPose){
            if(p.estHypothese()){
                listPontPose.remove(p);
            }
        }

    }

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
     * @param i1 la première île
     * @param i2 la seconde île
     * @return true ou false en fonction de si le pont est valide ou pas
     */
    private Boolean verifPont(Ile i1, Ile i2){
        //Il faut s'assurer que i1 et i2 soit bien deux réels voisins
        if (!erreur){
            if (i1.getX() < i2.getX()){
                if(i1.getValPontDir("E")<= getIleGrilleSolution(i1.getX(),i1.getY()).getValPontDir("E"))  return false;
            }if(i1.getX() > i2.getX()){
                if(i1.getValPontDir("O") <= getIleGrilleSolution(i1.getX(), i1.getY()).getValPontDir("O")) return false;
            }if(i1.getY() > i2.getY()){
                if(i1.getValPontDir("N") <= getIleGrilleSolution(i1.getX(),i1.getY()).getValPontDir("N")) return false;
            }else{
                if(i1.getValPontDir("S") <= getIleGrilleSolution(i1.getX(), i1.getY()).getValPontDir("S")) return false;
            }
            erreur = true;
        }
        return true;
    }

    /**
     * Méthode permettant de poser un pont entre 2 iles
     * @param i1 correspond a l'ile 1
     * @param i2 correspond a l'ile 2
     * @param estHypothese vrai si l'ile doit etre hypothese , false sinon
     */
    public void poserPont(Ile i1, Ile i2, Boolean estHypothese){
        int valXI1 = i1.getX();
        int valYI1 = i1.getY();

        int valXI2 = i2.getX();
        int valYI2 = i2.getY();


        if(valXI1 == valXI2 || valYI1 == valYI2){
            if(valYI1 > valYI2){
                ajoutePont("O",(IleJoueur)i1,"E",(IleJoueur)i2, estHypothese);
            }else if(valYI1 < valYI2){
                ajoutePont("E",(IleJoueur)i1 , "O", (IleJoueur)i2, estHypothese);
            }else if(valXI1 > valXI2){
                ajoutePont("N", (IleJoueur)i1, "S",(IleJoueur)i2, estHypothese);
            }else{
                ajoutePont("S",(IleJoueur)i1, "N", (IleJoueur)i2, estHypothese);
            }
            nbPontTotal += 1;

            if(verifMatrice()){
                Timeline timeline = GrilleControler.getChrono();
                timeline.stop();
            }

        }
    }

    /**
     * Renvoie le dernier pont ajouter
     * @return le dernier pont ajouter
     */
    public Pont getDernierPontAjouter(){
        if (!listPontPose.isEmpty()){
            return listPontPose.get(listPontPose.size()-1);
        }
        return null;
    }
    /**
     * Ajoute un pont dans la direction souhaitée
     * @param dir1 La direction souhaitée du joueur1
     * @param dir2 La direction souhaitée du joueur2
     * @param j1 l'ile du joueur 1
     * @param j2 l'ile du joueur 2
     * @return renvoie le pont créé
     */
    public Pont ajoutePont(String dir1, IleJoueur j1, String dir2, IleJoueur j2,Boolean estHypothese){
        if(j1.getValPontDir(dir1) == j1.getMaxPont() || j2.getValPontDir(dir2) == j2.getMaxPont()){
            for(Pont p: j1.getListePonts(dir1) ){
                if (p.estHypothese() == estHypothese){
                    j1.supprimePont(dir1,p);
                    j1.reinitPont(dir1);
                }
            }
            for(Pont p: j2.getListePonts(dir2) ){
                if (p.estHypothese() == estHypothese){
                    j2.reinitPont(dir2);
                }
            }
        }else{
            Pont p = new Pont(j1,j2, estHypothese);
            j1.ajoutePontList(dir1,p);
            j2.ajoutePontList(dir2, p);
            enregistrePont(p);

            //A chaque pont posé entre deux îles, on le vérifie
            if (!erreur && verifPont(j1, j2)){
                premiereErreur = listPontPose.size()-1;
            }else if(erreur){
                if (premiereErreur > listPontPose.size()) erreur = false;
            }
            return p;

        }
        return null;
    }

    /**
     * Renvoie l'indice de la première erreur
     * @return l'indice de la première erreur
     */
    public int getPremiereErreur(){
        return premiereErreur;
    }
    /**
     * Vérifie la grille et modifie la liste de Pont en conséquence
     * @return vrai si la grille est correct, faux sinon
     */
    public void corrigeList(){
        int taille = listPontPose.size();
        while(taille > premiereErreur){
            undoRedo.actionUndo(this);
            taille = listPontPose.size();
        }
        undoRedo.RazRedo();

    }

    /**
     * Vérifie la grille et la modifie en conséquence
     * @return vrai si la grille est correct, faux sinon
     */
    public boolean verifMatrice(){
        for (int i = 0; i< nbLigne; i++){
            for(int j = 0; j < nbColonne; j++){
                if(getIleGrilleJoueur(i,j)!=null && !getIleGrilleJoueur(i,j).ileComplete()){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Calcul le nombre de voisin 'physique' de j
     * @param joueur l'ile à tester
     * @return le nombre de voisin de j
     */
    public int getNbVoisinReel(IleJoueur joueur){
        int cpt = 0;

        int x = joueur.getX();
        int y = joueur.getY();

        int i = x;
        int j = y;


        //Test de présence de voisin au nord
        boucle_nord:
        for(i = x,j=y; j >= 0; j-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                cpt++;
                break boucle_nord;
            }
        }

        //Test de présence de voisin au sud
        boucle_sud:
        for(i = x,j=y; j < nbColonne; j++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                cpt++;
                break boucle_sud;
            }
        }

        //Test de présence de voisin au est
        boucle_est:
        for(i = x,j=y; i < nbLigne; i++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                cpt++;
                break boucle_est;
            }
        }

        //Test de présence de voisin au ouest
        boucle_ouest:
        for(i = x,j=y; i >= 0; i-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                cpt++;
                break boucle_ouest;
            }
        }

        return cpt;
    }

    /**
     * Test si pour une ile donnée et une direction donnée, un voisin existe
     * @param ile l'ile à tester
     * @param dir la direction à tester
     * @return l'ile si elle existe, null sinon
     */
    public IleJoueur getVoisinDir(IleJoueur ile, String dir){
        int i = -1;
        int j = -1;

        int x = ile.getX();
        int y = ile.getY();

        if (dir.compareTo("N") == 0){
            for(i = ile.getX(),j=ile.getY(); j >= 0; j-- ){
                if (getIleGrilleJoueur(i, j) != null && (i!=ile.getX() || j!= ile.getY())){
                    return (IleJoueur)getIleGrilleJoueur(i, j);
                }
            }
        }else if(dir.compareTo("S") == 0){
            for(i = x,j=y; j < nbColonne; j++ ){
                if (getIleGrilleJoueur(i, j) != null && (i!=x || j!= y)){
                    return (IleJoueur) getIleGrilleJoueur(i, j);
                }
            }
        }else if(dir.compareTo("E") == 0){
            for(i = x,j=y; i < nbLigne; i++ ){
                if (getIleGrilleJoueur(i, j) != null && (i!=x || j!= y)){
                    return (IleJoueur) getIleGrilleJoueur(i, j);
                }
            }
        }else{
            for(i = x,j=y; i >= 0; i-- ){
                if (getIleGrilleJoueur(i, j) != null && (i!=x || j!= y)){
                    return (IleJoueur) getIleGrilleJoueur(i, j);
                }
            }
        }
        //Ne peut pas arriver
        return null;
    }

    /**
     * renvoie la liste des voisins 'physique' de j
     * @param joueur l'ile à tester
     * @return  la list des voisins de j
     */
    public List<IleJoueur> getListVoisinReel(IleJoueur joueur){
        List<IleJoueur> listIle = new ArrayList<IleJoueur>();

        int x = joueur.getX();
        int y = joueur.getY();

        int i = x;
        int j = y;


        //Test de présence de voisin au nord
        boucle_nord:
        for(i = x,j=y; j >= 0; j-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                listIle.add((IleJoueur)getIleGrilleJoueur(i, j));
                break boucle_nord;
            }
        }

        //Test de présence de voisin au sud
        boucle_sud:
        for(i = x,j=y; j < nbColonne; j++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                listIle.add((IleJoueur)getIleGrilleJoueur(i, j));
                break boucle_sud;
            }
        }

        //Test de présence de voisin au est
        boucle_est:
        for(i = x,j=y; i < nbLigne; i++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                listIle.add((IleJoueur)getIleGrilleJoueur(i, j));
                break boucle_est;
            }
        }

        //Test de présence de voisin au ouest
        boucle_ouest:
        for(i = x,j=y; i >= 0; i-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                listIle.add((IleJoueur)getIleGrilleJoueur(i, j));
                break boucle_ouest;
            }
        }

        return listIle;
    }

    /**
     * Vérifie dans quelle direction un pont peut être posé
     * @param joueur l'ile qu'il faut tester
     * @return la liste des directions disponible
     */
    public List<String> pontPossible(IleJoueur joueur){
        List<String> res = new ArrayList<>();

        int x = joueur.getX();
        int y = joueur.getY();

        int i = x;
        int j = y;
        Ile voisinPontPotentiel = null;

        //Recherche de voisin au nord
        boucle_nord:
        for(i = x,j=y; j >= 0; j-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                voisinPontPotentiel = getIleGrilleSolution(i, j);
                break boucle_nord;
            }
        }
        /**
         * Si un voisin a été trouvé, on vérifie si il n'existe pas de pont entre les deux îles
         */
        if (voisinPontPotentiel != null){
            if (pontPossibleEntre(joueur, (IleJoueur)voisinPontPotentiel)){
                res.add("N");
            }
            voisinPontPotentiel = null;
        }

        //Test de présence de voisin au sud
        boucle_sud:
        for(i = x,j=y; j < nbColonne; j++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                voisinPontPotentiel = getIleGrilleSolution(i, j);
                break boucle_sud;
            }
        }

        /**
         * Si un voisin a été trouvé, on vérifie si il n'existe pas de pont entre les deux îles
         */
        if (voisinPontPotentiel != null){
            if (pontPossibleEntre(joueur, (IleJoueur)voisinPontPotentiel)){
                res.add("S");
            }
            voisinPontPotentiel = null;
        }

        //Test de présence de voisin au est
        boucle_est:
        for(i = x,j=y; i < nbLigne; i++ ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                voisinPontPotentiel = getIleGrilleSolution(i, j);
                break boucle_est;
            }
        }

        /**
         * Si un voisin a été trouvé, on vérifie si il n'existe pas de pont entre les deux îles
         */
        if (voisinPontPotentiel != null){
            if (pontPossibleEntre(joueur, (IleJoueur)voisinPontPotentiel)){
                res.add("E");
            }
            voisinPontPotentiel = null;
        }


        //Test de présence de voisin au ouest
        boucle_ouest:
        for(i = x,j=y; i >= 0; i-- ){
            if (getIleGrilleSolution(i, j) != null && (i!=x || j!= y)){
                voisinPontPotentiel = getIleGrilleSolution(i, j);
                break boucle_ouest;
            }
        }
        /**
         * Si un voisin a été trouvé, on vérifie si il n'existe pas de pont entre les deux îles
         */
        if (voisinPontPotentiel != null){
            if (pontPossibleEntre(joueur, (IleJoueur)voisinPontPotentiel)){
                res.add("O");
            }
            voisinPontPotentiel = null;
        }
        return res;

    }

    /**
     * Vérifie si un pont est posable entre deux iles
     * @param i1 la première ile a tester
     * @param i2 la seconde ile a tester
     * @return True si c'est possible, false sinon
     */
    public boolean pontPossibleEntre(IleJoueur i1, IleJoueur i2){
        int minX;
        int maxX;
        int minY;
        int maxY;

        if(i1==null || i2==null){
            return false;
        }

        if (estVerticale(i1, i2)){
            System.out.println("Verticale");
            if (i2.getX() < i1.getX()){
                minX = i2.getX();
                maxX = i1.getX();
            }else{
                minX = i1.getX();
                maxX = i2.getX();
            }
            for (Pont p : listPontPose){
                if( p.estHorizontal()){
                    if ((p.getMinY() < i1.getY()) &&( p.getMaxY() > i1.getY()) && (minX < p.getSrc().getX()) && (maxX > p.getSrc().getX())){
                        return false;
                    }
                }
            }
            return true;
        }
        else{
            System.out.println("Horizontale");
            if(i2.getY() < i1.getY()){
                minY = i2.getY();
                maxY = i1.getY();
            }else{
                minY = i1.getY();
                maxY = i2.getY();
            }

            for(Pont p: listPontPose){
                if(p.estVertical()){
                    if ((minY < p.getSrc().getY()) && (maxY > p.getSrc().getY()) && (p.getMinX() < i1.getX()) && (p.getMaxX() > i1.getX())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


    protected void afficher_mat_out() {
        for(int i = 0; i < nbLigne; i++) {
            for(int j = 0; j < nbColonne; j++) {
                if(getIleGrilleSolution(i,j) == null) {
                    System.out.print("*");
                }
                else {
                    System.out.print(getIleGrilleSolution(i,j).getValIle());
                }
            }
            System.out.print("\n");
        }
    }


    /**
     * Renvoie vrai si deux iles sont à la verticale ou pas
     * @param src l'ile source
     * @param dst l'ile destination
     * @return True ou False
     */
    public boolean estVerticale(Ile src, Ile dst){
        return src.getY() == dst.getY();
    }

    /**
     * Renvoie vrai si deux iles sont à l'horizontal ou pas
     * @param src l'ile source
     * @param dst l'ile
     * @return True ou False
     */
    public boolean estHorizontal(Ile src, Ile dst){
        return dst.getX() == src.getX();
    }

    /**
     * Methode permettant de créer une sauvegarde de la grille en cours
     * @param path_niveau le path doit être de la forme : /niveau/{Difficulte}/{Difficulte}-{Numero_Niveau}.txt
     */
    public void creer_sauvegarde(String path_niveau) {
        try {
            Sauvegarde save = new Sauvegarde();
            //String nom_joueur = GlobalVariables.getUserInput();
            String nom_joueur = "Nathan";
            String[] result = path_niveau.split("/");

            System.out.println(save.getPath() + result[1] + "/" + nom_joueur + "/" + result[2].substring(0, result[2].length()-4)+ ".ser");

            File fichier_save = new File(save.getPath() + result[1] + "/" + nom_joueur + "/" + result[2].substring(0, result[2].length()-4)+ ".ser");

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier_save));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode permettant de créer une sauvegarde de la grille en cours
     * @param nom_fichier le path doit être de la forme : {Numero_Niveau}.ser
     */
    public GrilleJeu charger_sauvegarde(String nom_fichier) {
        try {
            Sauvegarde save = new Sauvegarde();
            //String nom_joueur = GlobalVariables.getUserInput();
            String nom_joueur = "Nathan";
            String[] result = nom_fichier.split("-");


            File fichier = new File(save.getPath() + "/" + result[0] + "/" + nom_joueur + "/" + nom_fichier);

            // ouverture d'un flux sur un fichier
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            GrilleJeu courant = (GrilleJeu) ois.readObject();

            ois.close();

            return courant;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
 /*
     public static void main(String[] args) {
         GrilleJeu testJeu = new GrilleJeu("../niveaux/facile/Facile-5.txt");
         testJeu.afficher_mat_out();
 
         testJeu.poserPont(testJeu.getIleGrilleJoueur(0,0), testJeu.getIleGrilleJoueur(0,2));
 
         System.out.println(testJeu.getIleGrilleJoueur(0,0).getValPontDir(new String("E")));
         //Aide.techniqueDeDepart(testJeu);
     }*/
}