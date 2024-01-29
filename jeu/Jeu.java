public class Jeu{
    Noeud[][] mat ;
    Noeud[][] mat_res ;
    Noeud[][] mat_err ;
    int taille_li ;
    int taille_col ;

    Jeu(){

    }
    boolean verifPont(Noeud n1, Noeud n2){
        if (n1.x < n2.x){
            if(n1.ponts.get("E")<= mat_res[n1.x][n1.y].ponts.get("E") ) return true;
        }if(n1.x > n2.x){
            if(n1.ponts.get("O")<= mat_res[n1.x][n1.y].ponts.get("O") ) return true;
        }if(n1.y > n2.y){
            if(n1.ponts.get("S")<= mat_res[n1.x][n1.y].ponts.get("S") ) return true;
        }if(n1.y < n2.y){
            if(n1.ponts.get("N")<= mat_res[n1.x][n1.y].ponts.get("N") ) return true;
        }
        mat_err = mat;
        return false;
    }
}