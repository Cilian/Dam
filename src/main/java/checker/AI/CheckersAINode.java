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

		return "din";

	}

}