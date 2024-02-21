/** Classe jeu utilisé pour symboliser une grille de hashi*/
//* @author COUPE Xavier FOUQUERAY Victor*/
//* @version 0.0 */

import java.io.*;

public class Jeu{
    /**La matrice sur laquelle nous sommes actuellement entrain de corriger */
    Noeud[][] mat ;
    /**La matrice deja finis donc celle que l'on doit avoir au final */
    Noeud[][] mat_res ;
    /**La matrice que l'on sauvegardera au moment ou l'on fait une erreur */
    Noeud[][] mat_err ;
    /**Le nombre de lignes que la grille possède */
    int taille_li ;
    /** Le nombre de colonnes que la grille possède */
    int taille_col ;
    /** Le score du joueur */
    double score ;
    /** le nombre de pont total de posés */
    double nbPont;
    /**le nombre total d'aide utilisé */
    double nbAide;
    /**Le temps initial au moment ou le jeu a commencé */
    long temps_init;
    /**Le temps final a la fin du jeu */
    long temps_f;
    /**Le temps total pour finir le puzzle */
    long temps;

    /*La precision de l'aide*/
    int precisionAide;


    /**Constructeur 
     * @param path le chemin menant au niveau qui doit etre cree 
     */
    Jeu(String path){
        charge(path);
    }
    
    /**
     * Méthode qui charge un fichier dans un tableau de noeuds
     * @param path le chemin menant au fichier 
     */
    private void charge(String path){
    		try{
            File file = new File(path);
            FileReader fr;
            BufferedReader read;
            fr = new FileReader(file);
            read = new BufferedReader(fr);
            

            String ligne;

            int i = 0;
            int valeurNoeud;
            while((ligne = read.readLine())!= null){
                String[] data;
                data = ligne.split(" ");
                if (i == 0){
                	taille_li = Integer.valueOf(data[0]);
                	taille_col = Integer.valueOf(data[1]);
                	
                	
                    mat_res = new Noeud[taille_li][taille_col];
                    
                    //Initialisation de la matrice erreur
                    mat_err = new Noeud[taille_li][taille_col];
                    mat = new Noeud[taille_li][taille_col];
                }else{
                    valeurNoeud = Integer.valueOf(data[2])+Integer.valueOf(data[3])+Integer.valueOf(data[4])+ Integer.valueOf(data[5]);
                    //Grille du joueur initialise avec 0 voisins
                    mat[Integer.valueOf(data[0])][Integer.valueOf(data[1])] = new Noeud(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurNoeud,0,0,0,0);

                    //enregistre pour chaque noeud le resultat final
                    mat_res[Integer.valueOf(data[0])][Integer.valueOf(data[1])] = new Noeud(Integer.valueOf(data[0]),Integer.valueOf(data[1]),valeurNoeud,Integer.valueOf(data[2]),Integer.valueOf(data[3]),Integer.valueOf(data[4]), Integer.valueOf(data[5]));
                }
                i +=1;
            }
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    
    }
     
     
    /**
     * Méthode permettant dans un premier temps de detecter dans quel sens se situe le noeud n2 par rapport au n1 et de vérifier si le pont poser est bon ou non
     * @param n1 le premier noeud
     * @param n2 le deuxieme noeud
     * @return true ou false en fonction de si le pont est valide ou pas
     */
    boolean verifPont(Noeud n1, Noeud n2){
        if (n1.x < n2.x){
            if(n1.ponts.get("E")<= mat_res[n1.x][n1.y].ponts.get("E") ) return true;
        }if(n1.x > n2.x){
            if(n1.ponts.get("O")<= mat_res[n1.x][n1.y].ponts.get("O") ) return true;
        }if(n1.y > n2.y){
            if(n1.ponts.get("N")<= mat_res[n1.x][n1.y].ponts.get("N") ) return true;
        }else{
            if(n1.ponts.get("S")<= mat_res[n1.x][n1.y].ponts.get("S") ) return true;
        }
        mat_err = mat;
        return false;
    }

    
    /**
     * Méthode permettant de poser un pont entre 2 noeuds en incremetant dans la bonne direction chacun des noeuds puis en utilisant %3, comme ça dès qu'on a dépasser deux on retourne a 0
     * @param n1 correspond au noeud 1
     * @param n2 correspond au noeud 2
     */
    void poserPont(Noeud n1, Noeud n2){
        if (n1.x < n2.x){
            n1.ponts.replace("E",n1.ponts.get("E")%3);
            n2.ponts.replace("O",n2.ponts.get("O")%3);
        }if(n1.x > n2.x){
            n1.ponts.replace("O",n1.ponts.get("O")%3);
            n2.ponts.replace("E",n2.ponts.get("E")%3);
        }if(n1.y > n2.y){
            n1.ponts.replace("S",n1.ponts.get("S")%3);
            n2.ponts.replace("N",n2.ponts.get("N")%3);
        }if(n1.y < n2.y){
            n1.ponts.replace("N",n1.ponts.get("N")%3);
            n2.ponts.replace("S",n2.ponts.get("S")%3);
        }
        nbPont += 1;
    }
    /**Méthode permettant de verifier si la matrice est bonne en vérifiant que la matrice erreur est rester nulle , si ce n'est pas le cas on recharge au moment de l'erreur*/
    boolean verifMatrice(){
        if (null != mat_err){
            System.out.println("La matrice n'est pas valide, retour au moment de l'erreur");
            mat=mat_err;
            mat_err=null;
            return false;
        }
        return true;
    }
    /**
     * Méthode permettant le calcul du score a la fin de la partie
     * @return le score finale
     */
    double calculScore(){
        //A refaire
        return 500 + Math.abs(Math.pow(nbAide,2)/Math.pow(nbPont,0.5) - temps);
    }

    /**
     * Méthode qui remet à zero le jeu 
     */
    void remiseAZero(){
        mat_err = null;
        int i = 0;
        int j = 0;
        for (i = 0; i < taille_li; i++) {
            for (j = 0; j < taille_col; j++){
                Noeud ile = mat[i][j];
                if (ile.etiquette != 0){
                    ile.ponts.replace("E",0);
                    ile.ponts.replace("N",0);
                    ile.ponts.replace("O",0);
                    ile.ponts.replace("S",0);
                }
            }
        }
        temps_init = System.nanoTime();
        nbPont = 0; 
        nbAide = 0;
    }
    /**Méthode commencer qui permet d'initialiser le temps */
    void commencer(){
        temps_init=System.nanoTime();
        nbPont = 0;
        nbAide = 0;
        
    }
    /**Méthode fin qui permet de mettre fin au jeu */
    void fin(){
        temps_f=System.nanoTime();
        temps=temps_f-temps_init;
        score=calculScore();
    }

    /* Méthode qui permet de récupérer la valeur de nbAide
     * @return la valeur de nbAide
    */
    public double getNbAide(){
        return nbAide;
    }

    /* Méthode qui permet de modifier la valeur de nbAide
     * @param v la valeur a applique a nbAide
    */
    public void setNbAide(int v){
        nbAide = v;
    }

    /*
     * Méthode qui renvoi le nombre de ligne de la grille
     * @return le nombre de ligne de la grille
     */
    public int getTailleLigne(){
        return taille_li;
    }

    /*
     * Méthode qui renvoi le nombre de ligne de la grille
     * @return le nombre de ligne de la grille
     */
    public int getTailleColonne(){
        return taille_col;
    }

    /*
     * Méthode qui renvoi la precision de l'aide
     * @return la precision de l'aide
     */
    public int getPrecisionAide(){
        return precisionAide;
    }

    /*
    * Méthode qui compte le nombre de voisin "physique" d'un noeud
    * @param noeud le noeud a verifier
    * @param matNoeud la matrice a qui appartient le noeud
    *
    */
    public int getNbVoisinReel(Noeud n){
        int cpt = 0;
        int x = n.getX();
        int y = n.getY();

        int i = x;
        int j = y;

        //Test si il y a un voisin au nord
        boucle_nord:
        for(i = x,j=y; j >= 0; j-- ){
            if (mat_res[i][j] != null && (i!=x || j!= y)){
                cpt++;
                break boucle_nord;
            }
        }

        //Test si il y a un voisin au sud
        boucle_sud:
        for(i = x,j=y; j < taille_col; j++ ){
            if (mat_res[i][j] != null && (i!=x || j!= y)){
                cpt++;
                break boucle_sud;
            }
        }

        //Test si il y a un voisin au ouest
        boucle_ouest:
        for(i = x,j=y; i >= 0; i-- ){
            if (mat_res[i][j] != null && (i!=x || j!= y)){
                cpt++;
                break boucle_ouest;
            }
        }

        //Test si il y a un voisin au est
        boucle_est:
        for(i = x,j=y; i < taille_li; i++ ){
            if (mat_res[i][j] != null && (i!=x || j!= y)){
                cpt++;
                break boucle_est;
            }
        }

        return cpt;
    }

    
    private void afficher_mat_out() {
        for(int i = 0; i < this.taille_li; i++) {
            for(int j = 0; j < this.taille_col; j++) {
                if(this.mat_res[i][j] == null) {
                    System.out.print("*");
                }
                else {
                    System.out.print(this.mat_res[i][j].getEtiquette());
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Jeu testJeu = new Jeu("../niveaux/facile/Facile-5.txt");
        testJeu.afficher_mat_out();

        Aide.techniqueDeDepart(testJeu);
    }


}   

