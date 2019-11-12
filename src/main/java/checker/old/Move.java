package checker.old;

public class Move {
    private int value;
    private Piece move_piece;
    private int new_x;
    private int new_y;
    private int[][] board;
    private int player;


    public Move(Piece piece, int x, int y, int player){
        this.move_piece = piece;
        this.new_x = x;
        this.new_y = y;
        this.player = player;
    }

    public void makeMove(){
        move_piece.setX(new_x);
        move_piece.setY(new_y);
    }

    public int getValue(){
        return value;
    }

    public void calcValue(){
        
    }



}
