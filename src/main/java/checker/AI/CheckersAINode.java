package checker.AI;


import java.util.ArrayList;
import java.util.List;

public class CheckersAINode
{

	public int alpha; //Alpha is the maximum lower bound of possible solutions
	//That is, alpha is the minimum (worst) value that the maximizer (the computer AI) is assured at that node
	public int beta; //Beta is the minimum upper bound of possible solutions
	public int maxply = 10;

	public String minmax(String st, int[][] board, boolean max, int ply, int player){
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;

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

}