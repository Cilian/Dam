package checker.AI;

import checker.CheckersOp;

public class CheckersAITree
{
	private CheckersAINode root;
	
	public CheckersAITree(CheckersOp inputBoard)
	{	
		root = new CheckersAINode(inputBoard, null, 11);
	}
	
	public void buildTree()
	{
		root.buildTree(false);
	}
	
	public String findBestMove() //Returns the sequence of best moves
	{
		System.out.println("The AI is thinking . . .");
		root.buildTree(false);
		System.out.println(root.bestNextMove);
		return root.bestNextMove;
	}
}
