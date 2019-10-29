import java.util.Comparator;

public class point implements Comparable {
    public int x;
    public int y;
    public int h;
    public int alpha;
    public int beta;
    public int board_sum;
    public point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public point(){

    }
    public int getH(){
        return h;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int compareTo(Object o) {
        int ch = ((point) o).h;
        return this.h-ch;
    }

    public static Comparator<point> pointComparator = new Comparator<point>() {
        @Override
        public int compare(point o1, point o2) {
            int board_sum1 = o1.board_sum;
            int board_sum2 = o2.board_sum;
            // Descending order
            return board_sum2-board_sum1;
        }
    };
}
