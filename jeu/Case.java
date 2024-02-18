public class Case {

    private int x;
    private int y;

    /**
     * Constructeur d'une Case
     * @param cX La coordonnée x
     * @param cY La coordonnée y
     */
    Case(int cX,int cY ){
        x = cX;
        y = cY;
    }


    int getX(){return x;}
    int getY(){return y;}
}
