package checker.AI;


import checker.CheckersMove;
import checker.CheckersOp;
import checker.old.Checker;

import java.util.ArrayList;
import java.util.List;

public class CheckersAINode
{

	// public int alpha; //Alpha is the maximum lower bound of possible solutions
	//That is, alpha is the minimum (worst) value that the maximizer (the computer AI) is assured at that node
	// public int beta; //Beta is the minimum upper bound of possible solutions
	// public int maxply = 10;
	public ArrayList<CheckersMove> moveList;
	public boolean isLeaf;
	public int currHeight;
	public CheckersOp board;
	public String bestMovePrevIteration;
	public String bestNextMove;
	public String moves;


	public CheckersAINode(CheckersOp inputBoard, String moveSequence, int inputCurrHeight){
		board = new CheckersOp(inputBoard);

		moves = moveSequence;
		bestNextMove = null;
		// alpha = Integer.MIN_VALUE;
		// beta = Integer.MAX_VALUE;
		currHeight = inputCurrHeight;
//		//Here is where you change the depth!!
//		if(board.rCount<=1||board.bCount<=1) //This is where you change the depth! And it automatically searches one deeper near the end-game
//		{
//			if(currHeight>=7)
//				isLeaf = true;
//		}
//		else if(board.rCount<=3||board.bCount<=3)
//		{
//			if(currHeight>=6)
//				isLeaf = true;
//		}
//		else if(currHeight>=5)
//			isLeaf = true;
//		else
//			isLeaf = false;
	}
	// REMEMBER TO HAVE THE FIRST CALL WITH ALPHA=INTEGER_MIN AND BETA=INTEGER_MAX
	public int minmax(CheckersOp board, boolean max, int currHeight, int alpha, int beta ){

		CheckersMove mv = new CheckersMove();
		CheckersOp tempb = new CheckersOp(board);
		// make move on tempb
		// mv.moves = move;

		if(board.rCount<=1||board.bCount<=1) //This is where you change the depth! And it automatically searches one deeper near the end-game
		{
			if(currHeight>=7)
				isLeaf = true;
		}
		else if(board.rCount<=3||board.bCount<=3)
		{
			if(currHeight>=6)
				isLeaf = true;
		}
		else if(currHeight>=5)
			isLeaf = true;
		else
			isLeaf = false;

		if(isLeaf){
			return board.evaluateBoard();
		}

		if(max){
			while (alpha < beta){

				int v = minmax(tempb, false, currHeight + 1, alpha, beta);
				if (v > alpha){
					alpha = v;
					mv.val = v;
					bestNextMove = mv.moves;
				}
			}
			return alpha;
		}
		else
			while (alpha < beta){

				int v =  minmax(tempb, true, currHeight + 1, alpha, beta);
				if (v < beta){
					beta = v;
					mv.val = v;
					bestNextMove = mv.moves;
				}
			}
			return beta;
	}

	public void buildTree(int currheight){

	}

	public ArrayList<String> moveGen(boolean max, int row, int col, CheckersOp board){
		// row = y
		// col = x
		ArrayList<String> Moves = new ArrayList<String>();
		// Generate some moves based on the col,row position and the board
		return Moves;
		//Moves are in StartYStartX,NextYNextX format
		// example of this is move from StartY = 2 and StartX = 2 to NextY = 3 and NextX = 3 is written 22,33
		// if there are jumps then the next position is just put after fx. 22,44,66
	}

}