package checker.old;

import java.util.Comparator;

// public class Piece implements Comparable {
public class Piece {
    private int x;
    private int y;
    private boolean king;
    private int owner;

//    public int h;
//    public int alpha;
//    public int beta;
//    public int board_sum;

    public Piece(int x, int y, int owner){
        this.x=x;
        this.y=y;
        this.king = false;
        this.owner = owner;
    }

    public Piece(){
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
    }

    public int getOwner(){
        return owner;
    }

    public void setOwner(){
        this.owner = owner;
    }


//    public int getH(){
//        return h;
//    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

//    @Override
//    public int compareTo(Object o) {
//        int ch = ((Piece) o).h;
//        return this.h-ch;
//    }

//    public static Comparator<Piece> pointComparator = new Comparator<Piece>() {
//        @Override
//        public int compare(Piece o1, Piece o2) {
//            int board_sum1 = o1.board_sum;
//            int board_sum2 = o2.board_sum;
//            // Descending order
//            return board_sum2-board_sum1;
//        }
//    };
}
