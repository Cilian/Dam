import java.util.Comparator;

// public class Piece implements Comparable {
public class Piece {
    private int x;
    private int y;

//    public int h;
//    public int alpha;
//    public int beta;
//    public int board_sum;

    public Piece(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Piece(){

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
