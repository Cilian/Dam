package checker.old;

import java.util.ArrayList;
import java.util.List;

public class Checker {

    /* Definition of board integers
     * 0 = Empty     1 = red piece       2 = black piece
     * 3 = red King  4 = black king
     * */
    int Totalpieces = 12;
    Boolean blackPlayer;
    List moves = new ArrayList();

    static int [][] board =
            {       {0,2,0,2,0,2,0,2}, // 0
                    {2,0,2,0,2,0,2,0}, // 1
                    {0,2,0,2,0,2,0,2}, // 2
                    {0,0,0,0,0,0,0,0}, // 3
                    {0,0,0,0,0,0,0,0}, // 4
                    {1,0,1,0,1,0,1,0}, // 5
                    {0,1,0,1,0,1,0,1}, // 6
                    {1,0,1,0,1,0,1,0}  // 7
            };  //   0 1 2 3 4 5 6 7



    public static List validMoves (ArrayList<Piece> Pieces, boolean blackPlayer, int TotalPieces) {
        ArrayList<Integer> moves = new ArrayList();
        for (int i = 0; i < TotalPieces; i++) {
            if(Pieces.get(i).getY() - 1 >= 0 && Pieces.get(i).getY() + 1 < 8 && Pieces.get(i).getX() + 1 < 8 && Pieces.get(i).getX() - 1 >= 0){
                //if pieces in front of player 1 are empty
                if (blackPlayer) {
                    //out of range
                    if (board[Pieces.get(i).getX() - 1][Pieces.get(i).getY() + 1] == 0) {
                        moves.add(Pieces.get(i).getX() - 1);
                        moves.add( Pieces.get(i).getY() + 1);
                    } if (board[Pieces.get(i).getX() - 1][Pieces.get(i).getY() - 1] == 0) {
                        moves.add(Pieces.get(i).getX() - 1);
                        moves.add(Pieces.get(i).getY() - 1);
                    }
                } else if (!blackPlayer) {
                    if (board[Pieces.get(i).getX() + 1][Pieces.get(i).getY() + 1] == 0) {
                        moves.add(Pieces.get(i).getX() + 1);
                        moves.add(Pieces.get(i).getY() + 1);
                    } else if (board[Pieces.get(i).getX() + 1][Pieces.get(i).getY() - 1] == 0) {
                        moves.add(Pieces.get(i).getX() + 1);
                        moves.add(Pieces.get(i).getY() - 1);
                    }
                }
            }
        }
        return moves;
    }


    public static void main(String[] args) {
        ArrayList<Piece> pieces = new ArrayList<>();
        //row 1  player 1
        pieces.add(new Piece(7,0,1));
        pieces.add(new Piece(7,2,1));
        pieces.add(new Piece(7,4,1));
        pieces.add(new Piece(7,6,1));
        //row 2  player 1
        pieces.add(new Piece(6,1,1));
        pieces.add(new Piece(6,3,1));
        pieces.add(new Piece(6,5,1));
        pieces.add(new Piece(6,7,1));
        //row 3  player 1
        pieces.add(new Piece(5,0,1));
        pieces.add(new Piece(5,2,1));
        pieces.add(new Piece(5,4,1));
        pieces.add(new Piece(5,6,1));
        //row 1  player 2
        pieces.add(new Piece(0,1,2));
        pieces.add(new Piece(0,3,2));
        pieces.add(new Piece(0,5,2));
        pieces.add(new Piece(0,7,2));
        //row 2  player 2
        pieces.add(new Piece(1,0,2));
        pieces.add(new Piece(1,2,2));
        pieces.add(new Piece(1,4,2));
        pieces.add(new Piece(1,6,2));
        //row 3  player 2
        pieces.add(new Piece(2,1,2));
        pieces.add(new Piece(2,3,2));
        pieces.add(new Piece(2,5,2));
        pieces.add(new Piece(2,7,2));

        System.out.println(validMoves(pieces,true,12));
    }

}
