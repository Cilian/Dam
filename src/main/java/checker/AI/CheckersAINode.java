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
	// public ArrayList<CheckersMove> moveList;
	// public boolean isLeaf;
	public int currHeight;
	public CheckersOp board;
	public String bestMovePrevIteration;
	public String firstSearchMv;
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
	public int minmax(CheckersOp board, boolean max, int currHeight, String prevItMv , int alpha, int beta ){
		boolean isLeaf = false;
		ArrayList<CheckersMove> moveList = null;
		CheckersMove mv = new CheckersMove();

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
				CheckersOp tempb = new CheckersOp(board);
				// call moveGenerate
				if(prevItMv != null){
					firstSearchMv = prevItMv;
				}
				else{
					moveList = moveGen(max, tempb);
					// might want to remove the firstSearchMv and then add it at the start of the list such that we start our search from there
					firstSearchMv = moveList.get(0).moves;
				}
				// make move on tempb
				// mv.moves = move;

				int v = minmax(tempb, false, currHeight + 1, null, alpha, beta);
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
				CheckersOp tempb = new CheckersOp(board);
				if(prevItMv != null){
					firstSearchMv = prevItMv;
				}
				else{
					moveList = moveGen(max, tempb);
					// might want to remove the firstSearchMv and then add it at the start of the list such that we start our search from there
					firstSearchMv = moveList.get(0).moves;
				}
				// make move on tempb
				// mv.moves = move;
				int v =  minmax(tempb, true, currHeight + 1, null, alpha, beta);
				if (v < beta){
					beta = v;
					mv.val = v;
					bestNextMove = mv.moves;
				}
			}
			return beta;
	}

	public void buildTree(int currheight){

		// run minmax iteratively
		// firts run with max depth of 1 than with 2 ..
		// have it in a while loop maybe where we can break, if we are reaching the time limit (15 sec)
		// can be something like this...
		// int i = 0;
		// bestMovePrevIteration = null; // might want to set this at the top
		// alpha = Integer.Min_value
		// beta = Integer.Max_value
		// while(TimeIsLeft){
		// minmax(board, max, i, bestMovePrevIteration, alpha, beta);
		// bestMovePrevIteration = bestNextMove;}
		// remember to break loop before we run out of time


	}

	public ArrayList<CheckersMove> moveGen(boolean max, CheckersOp board){
		ArrayList<CheckersMove> Moves = new ArrayList<CheckersMove>();
		// who the player is can be based on the max bool
		// Generate some moves based on the player and the board
		return Moves;
		//Moves are in StartYStartX,NextYNextX format
		// example of this is move from StartY = 2 and StartX = 2 to NextY = 3 and NextX = 3 is written 22,33
		// if there are jumps then the next position is just put after fx. 22,44,66
	}

}