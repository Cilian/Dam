import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class tic_tac_toe {
    public int maxply = 10;

    public Piece minmax(Piece st, int[][] board, boolean max, int ply, int player){
        int[][] method_board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, method_board[i], 0, board[i].length);
        }
        if (ply == maxply){
            System.out.println("hello from leaf");
            return st;
        }
        if (ply != 1){
            method_board[st.x][st.y] = player;
        }
        // when max is true, it is then a maximizer
        if (max){
            if(gameover(method_board)){
                st.alpha = 10000;
                return st;
            }
            List childs = new ArrayList();
            List availableMoves = available_moves(method_board, 1);
            int n = ply + 1;
            if(availableMoves.size() == 1)
                return st;
            for (Object element: availableMoves) {
                if(!(st.alpha < st.beta))
                    break;
                Piece move = (Piece)element;
                move.alpha = st.alpha;
                move.beta = st.beta;
                Piece child = minmax(move,method_board,false,n,1);
                childs.add(child);
                if (child.alpha > st.alpha)
                    st.alpha = child.alpha;
            }
//            if(availableMoves.size() == 0)
//                return st;
            System.out.println("max");
            childs.sort(Collections.reverseOrder());
            return (Piece) childs.get(0);
        }
        // when max is false, it is then a minimizer
        else {
            if(gameover(method_board)){
                st.alpha = 10000;
                return st;
            }
            List childs = new ArrayList();
            List availableMoves = available_moves(method_board, 2);
            int n = ply + 1;
            if(availableMoves.size() == 1)
                return st;
            for (Object element: availableMoves) {
                if(!(st.alpha < st.beta))
                    break;
                Piece move = (Piece)element;
                move.alpha = st.alpha;
                move.beta = st.beta;
                Piece child = minmax(move,method_board,true,n,2);
                childs.add(child);
                if (child.beta < st.beta)
                    st.beta = child.beta;
            }
            System.out.println("min");
            System.out.println(childs);
            childs.sort(Collections.reverseOrder());
            return (Piece) childs.get(childs.size()-1);
        }




    }


    public void make_move(int[][] board, Piece st, int player){
       board[st.x][st.y] = player;
    }

    public List available_moves(int[][] board, int player){
        List moves = new ArrayList();
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == 0){
                    Piece move = new Piece(i,j);
                    move.h = h_value(move);
                    moves.add(move);
                }

            }
        }
        System.out.println("board");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("moves");
        System.out.println(moves);
        return moves;
    }

    public static boolean gameover(int[][] board){
        // top row is the same
        if(board[0][0] == board[0][1] && board[0][0] == board[0][2]){
            if(board[0][0] != 0){
                return true;
            }
        }
        // middle row is the same
        if(board[1][0] == board[1][1] && board[1][0] == board[1][2]){
            if(board[1][0] != 0){
                return true;
            }
        }
        // bottom row is the same
        if(board[2][0] == board[2][1] && board[2][0] == board[2][2]){
            if(board[2][0] != 0){
                return true;
            }
        }
        // left column same
        if(board[0][0] == board[1][0] && board[0][0] == board[2][0]){
            if(board[0][0] != 0){
                return true;
            }
        }
        // middle column same
        if(board[0][1] == board[1][1] && board[0][1] == board[2][1]){
            if(board[0][1] != 0){
                return true;
            }
        }
        // right column same
        if(board[0][2] == board[1][2] && board[0][2] == board[2][2]){
            if(board[0][2] != 0){
                return true;
            }
        }
        // slash \ is same
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            if(board[0][0] != 0){
                return true;
            }
        }

        // slash / is same
        if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
            if(board[0][2] != 0){
                return true;
            }
        }
        return false;
    }

    public int board_sum(int[][] board, int player){
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == player){

                }
            }
        }
        return 0;
    }

    public int h_value(Piece p){
        if ((p.x == 0 && p.y == 0) || (p.x == 0 && p.y == 2) || (p.x == 2 && p.y == 0) || (p.x == 2 && p.y == 2)){
            return 3;
        }
        else if ((p.x == 0 && p.y == 1) || (p.x == 1 && p.y == 0) || (p.x == 1 && p.y == 2) || (p.x == 2 && p.y == 1)){
            return 2;
        }
        else return 4;
    }
}



