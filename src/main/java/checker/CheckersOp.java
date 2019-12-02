package checker;

public class CheckersOp {

	// 0 - Blank Space
	// 1 - Red Piece
	// 2 - Black Piece
	// 3 - Red King
	// 4 - Black King
	// 5 - Rogue Piece

	private int[][] board;
	private int[][] board2;
	public int rCount;
	public int bCount;
	public int turnCount; //Just a counter -- NOT whose turn it is
	public int turn; //Bizzarely, 0 is black, and 1 is red
	public int currJumper;
	public int prevrCount;
	public int prevbCount;
	public int prevturnCount; //Just a counter -- NOT whose turn it is
	public int prevturn; //Bizzarely, 0 is black, and 1 is red
	public int[][] prevBoard;

	public void resetBoard()
	{
		
		board = new int[][]
				{{0, 1, 0, 1, 0, 1, 0, 1},
				{1, 0, 1, 0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0, 1, 0, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 2, 0, 2, 0, 2, 0},
				{0, 2, 0, 2, 0, 2, 0, 2},
				{2, 0, 2, 0, 2, 0, 2, 0}};
//		board = new int[][]
//						{{0, 0, 0, 0, 0, 0, 0, 1},
//						{0, 0, 0, 0, 1, 0, 1, 0},
//						{0, 0, 0, 1, 0, 1, 0, 1},
//						{0, 0, 1, 0, 0, 0, 0, 0},
//						{0, 2, 0, 0, 0, 0, 0, 0},
//						{2, 0, 0, 0, 2, 0, 0, 0},
//						{0, 0, 0, 0, 0, 0, 0, 2},
//						{0, 0, 0, 0, 2, 0, 2, 0}};

				

		//Remember to change this later! This was just for testing the endgame:

		board2 = new int[][]
				{{0, 1, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 1, 0, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 2, 0, 2, 0, 2, 0},
				{0, 2, 0, 2, 0, 2, 0, 2},
				{2, 0, 2, 0, 2, 0, 2, 0}};

		

		//Once again, just for testing the endgame (computer winning):
		/*
		board = new int[][] 
				{{0, 1, 0, 1, 0, 1, 0, 1},   
				{1, 0, 1, 0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0, 1, 0, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 2, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 0, 0, 0, 0, 0}};
				*/
				
	}
	
	 
	public CheckersOp() //This constructor makes the default starting board
	{
		rCount=12;
		bCount=12;
		turnCount=0;
		turn = 0; //0 for black, 1 for red
		currJumper = -1; //-1 to be default state
		board = new int[8][8];
		resetBoard();
	}
	
	public CheckersOp(CheckersOp template) //This one clones a given CheckersOp template
	{
		int[][] inputBoard = template.getBoard();
		board = new int[8][8];
		for(int row=0; row<8; row++)
		{
			for(int col=0; col<8; col++)
			{
				board[row][col] = inputBoard[row][col];
			}
		}
		rCount = template.rCount;
		bCount = template.bCount;
		turnCount = template.turnCount;
		turn = template.turn;
		currJumper = template.currJumper;
	}

//	public void undoLastMove(){
//		// board = prevBoard;
//		for (int i = 0; i < prevBoard.length; i++) {
//			System.arraycopy(prevBoard[i], 0, board[i], 0, prevBoard[i].length);
//		}
//		currJumper = -1;
//		turn = prevturn;
//		turnCount = prevturnCount;
//		rCount = prevrCount;
//		bCount = prevbCount;
//
//	}
	
	public int makeMove(int from, int to){
		// example of this is 25 / 10 = 2.5 but since it is int is floored to 2
		// example of this is 25 % 10 removes all multiples of 10,
		// so it basicly removes 10 and keeps doing it as long as the remainder is higher than 10
		int fromY = from / 10;
		int fromX = from % 10;
		int toY = to / 10;
		int toX = to % 10;
		

		int moveType; //stores whether it is a move, jump or invalid

		int pieceType = board[fromY][fromX]; //finds the type of piece at the from position

		if(pieceType == 0) return 0; //if you are moving from a blank face, return false

		if(pieceType %2 != turn) return 0;//it is not the person's turn 

		moveType = checkValidMove(from, to);

		if(moveType == 0) return 0;

		//now move the piece accordingly. moveType is 1 if it is a normal move or 2 if it is a jump

		if(currJumper != -1)
		{
			if(from != currJumper) return 0;
			if(moveType != 2) return 0;
			
		}
		board[toY][toX] = board[fromY][fromX];
		board[fromY][fromX] = 0;

		if(moveType == 2){

			int y = (toY - fromY)/2;
			int x = (toX - fromX)/2;
			int middle = board[fromY + y][fromX + x];
			if(pieceType == 2 || pieceType == 4){
				if(middle == 1 || middle == 3){
					board[fromY + y][fromX + x] = 0;
					rCount--;
				}else return 0;
			}

			if(pieceType == 1 || pieceType == 3){
				if(middle == 2 || middle == 4){
					board[fromY + y][fromX + x] = 0;
					bCount--;
				}else return 0;
			}
		}
		//Check to see if a King was born
		if((toY == 0  && pieceType == 2)|| (toY == 7 && pieceType == 1)){
			if(pieceType == 1) {
				board[toY][toX] = 3;
				switchTurn();
				turnCount++;
				return 420;
			}
			if(pieceType == 2) {
				board[toY][toX] = 4;
				switchTurn();
				turnCount++;
				return 420;
			}

		}
		turnCount++;
		if(currJumper != -1) currJumper = -1;
		boolean testHasJump = hasJump(to);
		if(moveType == 2 && testHasJump) currJumper = to;
		else switchTurn();

		return moveType;
	}

//	public int makeMove(int from, int to, int start){
//		// example of this is 25 / 10 = 2.5 but since it is int is floored to 2
//		// example of this is 25 % 10 removes all multiples of 10,
//		// so it basicly removes 10 and keeps doing it as long as the remainder is higher than 10
//		if(start == 0){
//			prevBoard = new int[8][8];
//			for (int i = 0; i < board.length; i++) {
//				System.arraycopy(board[i], 0, prevBoard[i], 0, board[i].length);
//			}
//			prevbCount = bCount;
//			prevrCount = rCount;
//			prevturn = turn;
//			prevturnCount = turnCount;
//		}
//		int fromY = from / 10;
//		int fromX = from % 10;
//		int toY = to / 10;
//		int toX = to % 10;
//
//
//		int moveType; //stores whether it is a move, jump or invalid
//
//		int pieceType = board[fromY][fromX]; //finds the type of piece at the from position
//
//		if(pieceType == 0) return 0; //if you are moving from a blank face, return false
//
//		if(pieceType %2 != turn) return 0;//it is not the person's turn
//
//		moveType = checkValidMove(from, to);
//
//		if(moveType == 0) return 0;
//
//		//now move the piece accordingly. moveType is 1 if it is a normal move or 2 if it is a jump
//
//		if(currJumper != -1)
//		{
//			if(from != currJumper) return 0;
//			if(moveType != 2) return 0;
//
//		}
//		board[toY][toX] = board[fromY][fromX];
//		board[fromY][fromX] = 0;
//
//		if(moveType == 2){
//
//			int y = (toY - fromY)/2;
//			int x = (toX - fromX)/2;
//			int middle = board[fromY + y][fromX + x];
//			if(pieceType == 2 || pieceType == 4){
//				if(middle == 1 || middle == 3){
//					board[fromY + y][fromX + x] = 0;
//					rCount--;
//				}else return 0;
//			}
//
//			if(pieceType == 1 || pieceType == 3){
//				if(middle == 2 || middle == 4){
//					board[fromY + y][fromX + x] = 0;
//					bCount--;
//				}else return 0;
//			}
//		}
//		//Check to see if a King was born
//		if(toY == 0 || toY == 7){
//			if(pieceType == 1) {
//				board[toY][toX] = 3;
//				switchTurn();
//				return 420;
//			}
//			if(pieceType == 2) {
//				board[toY][toX] = 4;
//				switchTurn();
//				return 420;
//			}
//
//		}
//		turnCount++;
//		if(currJumper != -1) currJumper = -1;
//		boolean testHasJump = hasJump(to);
//		if(moveType == 2 && testHasJump) currJumper = to;
//		else switchTurn();
//
//		return moveType;
//	}

	public boolean isRedTurn(){
		return turn == 1;
	}

	public void setRedTurn(){
		turn = 1;
	}

	public void setBlackTurn(){
		turn = 0;
	}
	
	private void switchTurn(){
		if(turn == 0) turn = 1;
		else turn = 0;
	}

	public boolean hasJump(int piece)
	{
		int y=piece/10;
		int x=piece%10;
		int pieceType=board[y][x];
		if(pieceType==0)
			return false;
		if(checkValidMove(piece, piece+22)!=0||
				checkValidMove(piece, piece+18)!=0||
				checkValidMove(piece, piece-22)!=0||
				checkValidMove(piece, piece-18)!=0)
			return true;
		return false;
	}
	
	//MoveType
	//0 - Invalid Move
	//1 - Normal Move
	//2 - Jump

	public int checkValidMove(int from, int to) {
		int fromY = from / 10;
		int fromX = from % 10;
		int toY = to / 10;
		int toX = to % 10;

		// checks if it is outside the board
		if(fromY>7||fromY<0||toX>7||toX<0||fromX>7||fromX<0||toY>7||toY<0)
			return 0;
		int fromPieceType = board[fromY][fromX];
		if(fromPieceType == 3 ||fromPieceType == 4) 
			return checkValidkingMove(from, to);
		int toPieceType = board[toY][toX];

		if(toPieceType != 0) return 0; //check to make sure you're trying to go to a blank space

		//make sure it is in a valid spot
		if(fromPieceType == 1 && Math.abs(toX - fromX) == 1 && toY - fromY == 1) return 1;
		if(fromPieceType == 2 && Math.abs(toX - fromX) == 1 && toY - fromY == -1) return 1;
		// is a jump move? |
		//                 V
		if(fromPieceType == 1 && Math.abs(toX - fromX) == 2 && toY - fromY == 2){
			int x = (toX - fromX)/2;
			// if statement checks if it is an opponents piece that is jumped over and returns 2 (jump move) if true
			// or 0 (invalid move) if false
			if(board[fromY + 1][fromX  + x]==2||board[fromY + 1][fromX  + x]==4) return 2;
		return 0;
		}
		if(fromPieceType == 2 && Math.abs(toX - fromX) == 2 && toY - fromY == -2){
			int x = (toX - fromX)/2;
			if(board[fromY - 1][fromX  + x]==1||board[fromY - 1][fromX  + x]==3) return 2;
		return 0;
		}
		else return 0;
	}

	private int checkValidkingMove(int from, int to) {
		int fromY = from / 10;
		int fromX = from % 10;
		int toY = to / 10;
		int toX = to % 10;

		if(fromY>7||fromY<0||toX>7||toX<0||fromX>7||fromX<0||toY>7||toY<0)
			return 0;
		
		int fromPieceType = board[fromY][fromX];
		int toPieceType = board[toY][toX];

		if(toPieceType != 0) return 0; //check to make sure you're trying to go to a blank space

		//make sure it is within range.
		if(Math.abs(toY - fromY) == 1 && Math.abs(toX - fromX) == 1) return 1;
		else if(Math.abs(toY - fromY) == 2 && Math.abs(toX - fromX) == 2)
		{
			int x = (toX - fromX)/2;
			int y = (toY - fromY)/2;
			if(fromPieceType == 3 && (board[fromY + y][fromX + x] == 2 || board[fromY + y][fromX + x] == 4)){
				return 2;
			}
			if(fromPieceType == 4 && (board[fromY + y][fromX + x] == 1 || board[fromY + y][fromX + x] == 3)){
				return 2;
			}
			return 0;	
		}
		else return 0;
	}

	public int checkWinner()
	{
		//0 no winner, 1 red winner, 2 black winner
		if(rCount==0)
			return 2;
		if(bCount==0)
			return 1;
		//So those checks -- if one side has lost all their pieces -- works fine
		//Now we have to figure out the trickier part:
		
		if(turn==0) //It's black's turn
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					int piece=10*i+j;
					if(board[i][j]==2||board[i][j]==4)
					{
						//First we check moves that a regular piece OR king might be able to make
						//That is, those that move forward
						if(checkValidMove(piece,piece-22)!=0)
							return 0;
						if(checkValidMove(piece,piece-11)!=0)
							return 0;
						if(checkValidMove(piece,piece-18)!=0)
							return 0;
						if(checkValidMove(piece,piece-9)!=0)
							return 0;
						
						if(board[i][j]==4)
						{
							if(checkValidkingMove(piece,piece+11)!=0)
								return 0;
							if(checkValidkingMove(piece,piece+9)!=0)
								return 0;
							if(checkValidkingMove(piece,piece+22)!=0)
								return 0;
							if(checkValidkingMove(piece,piece+18)!=0)
								return 0;
						}
					}
				}	
			}
			return 1;
		}
		
		else if(turn==1) //It's red's turn
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					int piece=10*i+j;
					if(board[i][j]==1||board[i][j]==3)
					{
						//First check forward moves
						if(checkValidMove(piece,piece+11)!=0)
							return 0;
						if(checkValidMove(piece,piece+9)!=0)
							return 0;
						if(checkValidMove(piece,piece+22)!=0)
							return 0;
						if(checkValidMove(piece,piece+18)!=0)
							return 0;
						
						if(board[i][j]==3) //If it's a king, then check possible backwards moves
						{

							if(checkValidkingMove(piece,piece-11)!=0)
								return 0;
							if(checkValidkingMove(piece,piece-9)!=0)
								return 0;
							if(checkValidkingMove(piece,piece-22)!=0)
								return 0;
							if(checkValidkingMove(piece,piece-18)!=0)
								return 0;
						}
					}
				}	
			}
			return 2;
		}
		return 0;
	}	
	
	public int evaluateBoard()
	{
		// THIS IS THE HEURISTIC FOR THE AI!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


		//We need to encourage the AI to make sacrifices in the endgame if necessary
		//Using a divide-by function might improve that.
		//Or! If the computer is at 0 pieces, the board should evaluate to Integer.MIN_VALUE
		//And if the human is at 0 pieces, the board should evaluate to Integer.MAX_VALUE -- best possible outcome
		//We could use the checkWinner() function, but that would be very slow
		
		//First we check to make sure there's at least one black and red piece on the board
		//Wait we can use rCount and bCount, silly Eric
		
//		if(rCount==0) //No more computer pieces; minimum (worst for AI) possible outcome
//			return Integer.MIN_VALUE;
//		if(bCount==0) //No more human pieces; maximum (best for AI) possible outcome
//			return Integer.MAX_VALUE;
		//if Red (com) wins return high val
		if(this.checkWinner() == 1){
			return Integer.MAX_VALUE;
		}
		//if Black (player) win return low val
		else if(this.checkWinner() == 2){
			return Integer.MIN_VALUE;
		}
		
		int sum = 0;
		//Edge pieces are worth more, center pieces are worth less
		//We can experiment with that scale.
		//But kings are worth a constant value (you want them moving around)
		
		int[][] sumBoard = 
			   {{0, 7, 0, 7, 0, 7, 0, 7},
				{6, 0, 5, 0, 5, 0, 5, 0},
				{0, 5, 0, 5, 0, 5, 0, 6},
				{6, 0, 5, 0, 5, 0, 5, 0},
				{0, 5, 0, 5, 0, 5, 0, 6},
				{6, 0, 5, 0, 5, 0, 5, 0},
				{0, 5, 0, 5, 0, 5, 0, 6},
				{7, 0, 7, 0, 7, 0, 7, 0}};

		//Remember to change depending on which color the computer is
		for(int y = 0;y < 8; y++){
			for(int x = 0; x < 8; x++){
				int pieceType = board[x][y];
				if(pieceType == 1){
					sum += sumBoard[x][y] * 1; //Value of the square that it's on
				}else if(pieceType ==2){
					sum -= sumBoard[x][y] * 1;
				}else if(pieceType == 3){
					sum += 10;
				}else if(pieceType == 4){
					sum -= 10;
				}
			}
		}
		return sum;
	}
	
	public int[][] getBoard(){
		return board;
	}

}