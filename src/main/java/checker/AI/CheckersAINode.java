package checker.AI;


import checker.CheckersMove;
import checker.CheckersOp;
import checker.old.Checker;
import checker.old.Piece;

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
	public ArrayList<String> genMoves = new ArrayList<>();
	//public int currHeight;
	public CheckersOp globalBoard;
	public String bestMovePrevIteration;
	public String firstSearchMv;
	public String bestNextMove;



	public CheckersAINode(CheckersOp inputBoard, String moveSequence, int inputCurrHeight){
		globalBoard = new CheckersOp(inputBoard);

		//String moves = moveSequence;
		bestNextMove = null;
		// alpha = Integer.MIN_VALUE;
		// beta = Integer.MAX_VALUE;
		//currHeight = inputCurrHeight;
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
	// The AI makes illegal move avoids jump moves
	// Maybe check all jump moves before, normal moves
	// Maybe change the evaluation method since the evaluation results differ very slightly
	public int minmax(CheckersOp board, boolean max, int currHeight, String prevItMv , int alpha, int beta ){
		//boolean isLeaf = false;
		ArrayList<String> moveList = new ArrayList<>();
		CheckersOp tempb = new CheckersOp(board);

		if(prevItMv != null){
//			firstSearchMv = prevItMv;
			// moveList.add(firstSearchMv);
			// might be able to do this in a better way
			moveList.add("placeholder");
			moveList.addAll(moveGen(max, tempb));
			moveList.remove(prevItMv);
			moveList.add(0,prevItMv);
		}
		else{
			moveList = moveGen(max, tempb);
//			firstSearchMv = moveList.get(0);
		}

		if(currHeight == 13){
			return tempb.evaluateBoard();
		}

		if(max){
			int i=0;
			while ((alpha < beta) && (i < moveList.size())){
				// might want to remove the firstSearchMv and then add it at the start of the list such that we start our search from there
				// make move on tempb
				String[] str = moveList.get(i).split(",");
				for (int j = 0; j < str.length - 1; j++) {
					int from = Integer.parseInt(str[j]);
					int to = Integer.parseInt(str[j+1]);
					// could make it throw an exception if the returned int is 0
					tempb.makeMove(from, to);
				}

				int v = minmax(tempb, false, currHeight + 1, null, alpha, beta);
				if (v > alpha) {
					System.out.println("alpha = " + v);
					alpha = v;
					System.out.println(moveList.get(i));
					// mv.val = v;
					bestNextMove = moveList.get(i);
				}
				i++;
			}
			return alpha;
		}
		else {
			int i = 0;
			while ((alpha < beta) && (i < moveList.size())) {
				// make move on tempb
				// mv.moves = move; might not be the way to go
				String[] str = moveList.get(i).split(",");
				for (int j = 0; j < str.length - 1; j++) {
					int from = Integer.parseInt(str[j]);
					int to = Integer.parseInt(str[j+1]);
					// could make it throw an exception if the returned int is 0
					tempb.makeMove(from, to);
				}
				int v = minmax(tempb, true, currHeight + 1, null, alpha, beta);
				if (v < beta) {
					System.out.println(moveList.get(i));
					beta = v;
					System.out.println("beta = " + v);
					// mv.val = v;
					//bestNextMove = moveList.get(i);
				}
				i++;
			}
			return beta;
		}
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
		minmax(globalBoard, true, 0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);

	}

    // THE MOVE GENERATOR !!!!!!!
	public ArrayList<String> moveGen(boolean max, CheckersOp board){
		ArrayList<String > moves = new ArrayList<String>();
		// who the player is can be based on the max bool
		// Generate some moves based on the player and the board

		if (max) { // if Computer's turn we maximize
			// i = y = row
			// j = x = col
			// Boolean hasj = false;
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (board.getBoard()[i][j] % 2 == 1){
						addValidSquareJumpMovesForRed(i,j,"", board);
					}
				}
			}
			// ONLY ADD THE SQUARE MOVES IF NO JUMP MOVES ARE AVAILABLE!!!
			if(genMoves.size() == 0){
				System.out.println("hej from Square moveGen for red piece");
				for (int i = 0; i < 8; i++){
					for (int j = 0; j < 8; j++){
						if (board.getBoard()[i][j] % 2 == 1){
							addValidSquareMovesForRed(i,j, board);
						}
					}
				}
			}
			moves.addAll(genMoves);
			genMoves.clear();
		}
		else { //if player's turn we minimize
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					int piece = board.getBoard()[i][j];
					if (piece % 2 == 0 && piece != 0){
						addValidSquareJumpMovesForBlack(i,j,"", board);
					}
				}
			}
			if(genMoves.size() == 0){
				for (int i = 0; i < 8; i++){
					for (int j = 0; j < 8; j++){
						int piece = board.getBoard()[i][j];
						if (piece % 2 == 0 && piece != 0){
							addValidSquareMovesForBlack(i,j, board);
						}
					}
				}
			}
			moves.addAll(genMoves);
			genMoves.clear();
		}
		return moves;
		//Moves are in StartYStartX,NextYNextX format
		// example of this is move from StartY = 2 and StartX = 2 to NextY = 3 and NextX = 3 is written 22,33
		// if there are jumps then the next position is just put after fx. 22,44,66
	}

	public void addValidSquareMovesForRed(int row, int col, CheckersOp board)
	{

		//These are helper methods: find the possible moves for any given square
		if(board.getBoard()[row][col]==1)//If we found a normal red piece
		{
			//Later, we may optimize the searching order by putting the jumps first
			if(board.checkValidMove(10*row+col,10*(row+1)+col-1)==1) //Moving down-left
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row+1)+col+1)==1) //moving down-right
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col+1);
				genMoves.add(lastMove);
			}
		}
		else if(board.getBoard()[row][col]==3) //This means we found a red king!
		{
			if(board.checkValidMove(10*row+col,10*(row+1)+col-1)==1) //Moving down-left
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row+1)+col+1)==1) //moving down-right
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col+1);
				genMoves.add(lastMove);
			}

			if(board.checkValidMove(10*row+col,10*(row-1)+col-1)==1) //Moving up-left
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row-1)+col+1)==1) //moving up-right
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col+1);
				genMoves.add(lastMove);

			}
		}
	}

	public void addValidSquareJumpMovesForRed(int row, int col, String temp, CheckersOp tboard)
	{
		int index = 10*row+col;
		temp += index + ",";


		//These are helper methods: find the possible moves for any given square
		if(tboard.getBoard()[row][col]==1)//If we found a normal red piece
		{
			if(temp.length() > 3){ //If temp has more than 1 coordinate
				if(!(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2)){
					temp = temp.substring(0,temp.length()-1);
					genMoves.add(temp);
					return;
				}
			}

			//Later, we may optimize the searching order by putting the jumps first
			if(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) //Jumping down left (this is only possible when moving down-left is impossible)
			{
				// String tempMove = temp  + ","+(10*(row+2)+col-2);
				//String tempIndex = temp +(10*(row+2)+col-2);
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2) //Jumping down-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col+2, temp, tempBoard);
			}
		}
		else if(tboard.getBoard()[row][col]==3) //This means we found a red king!
		{
			if(temp.length() > 3){ //If temp has more than 1 coordinate
				if(!(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2) &&
						!(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2)){
					temp = temp.substring(0,temp.length()-1);
					genMoves.add(temp);
					return;
				}
			}

			if(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) //Jumping down left
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2) //Jumping down-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col+2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) //Jumping up-left
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2) //Jumping up-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col+2, temp, tempBoard);
			}
		}
	}


	public void addValidSquareMovesForBlack(int row, int col, CheckersOp board)
	{

		//These are helper methods: find the possible moves for any given square
		if(board.getBoard()[row][col]==2)//If we found a normal black piece
		{

			if(board.checkValidMove(10*row+col,10*(row-1)+col-1)==1) //Moving up-left
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row-1)+col+1)==1) //moving up-right
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col+1);
				genMoves.add(lastMove);
			}
		}
		else if(board.getBoard()[row][col]==4) //This means we found a red king!
		{
			if(board.checkValidMove(10*row+col,10*(row+1)+col-1)==1) //Moving down-left
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row+1)+col+1)==1) //moving down-right
			{
				String lastMove=(10*row+col) + "," + (10*(row+1)+col+1);
				genMoves.add(lastMove);
			}

			if(board.checkValidMove(10*row+col,10*(row-1)+col-1)==1) //Moving up-left
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col-1);
				genMoves.add(lastMove);
			}
			if(board.checkValidMove(10*row+col,10*(row-1)+col+1)==1) //moving up-right
			{
				String lastMove=(10*row+col) + "," + (10*(row-1)+col+1);
				genMoves.add(lastMove);

			}
		}
	}

	public void addValidSquareJumpMovesForBlack(int row, int col, String temp, CheckersOp tboard)
	{
		int index = 10*row+col;
		temp += index + ",";


		//These are helper methods: find the possible moves for any given square
		if(tboard.getBoard()[row][col]==2)//If we found a normal black piece
		{
			if(temp.length() > 3){ //If temp has more than 1 coordinate
				if(!(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2)){
					temp = temp.substring(0,temp.length()-1);
					genMoves.add(temp);
					return;
				}
			}

			//Later, we may optimize the searching order by putting the jumps first
			if(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) //Jumping down left (this is only possible when moving down-left is impossible)
			{
				// String tempMove = temp  + ","+(10*(row+2)+col-2);
				//String tempIndex = temp +(10*(row+2)+col-2);
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2) //Jumping down-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col+2, temp, tempBoard);
			}
		}
		else if(tboard.getBoard()[row][col]==4) //This means we found a black king!
		{
			if(temp.length() > 3){ //If temp has more than 1 coordinate
				if(!(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2) &&
						!(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) && !(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2)){
					temp = temp.substring(0,temp.length()-1);
					genMoves.add(temp);
					return;
				}
			}

			if(tboard.checkValidMove(10*row+col,10*(row+2)+col-2)==2) //Jumping down left
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row+2)+col+2)==2) //Jumping down-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row+2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row+2), col+2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row-2)+col-2)==2) //Jumping up-left
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col-2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col-2, temp, tempBoard);
			}

			if(tboard.checkValidMove(10*row+col,10*(row-2)+col+2)==2) //Jumping up-right
			{
				CheckersOp tempBoard = new CheckersOp(tboard);
				int to = (10*(row-2)+col+2);
				int from = row*10 + col;
				tempBoard.makeMove(from, to);
				addValidSquareJumpMovesForRed((row-2), col+2, temp, tempBoard);
			}
		}
	}


}
