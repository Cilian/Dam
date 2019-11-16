package checker;

import checker.old.Checker;

public class CheckersMove {
    public String moves;
    public int val;

    public CheckersMove(String moves, int val, int alpha, int beta){
        this.moves = moves;
        this.val = val;
    }

    public CheckersMove(){
    }
}
