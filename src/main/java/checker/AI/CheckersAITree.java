package checker.AI;

import checker.CheckersOp;

public class CheckersAITree
{
	private CheckersAINode root;
	
	public CheckersAITree(CheckersOp inputBoard)
	{	
		root = new CheckersAINode(inputBoard, null, 0);
	}
	
	public void buildTree()
	{
		root.buildTree(0);
	}
	
	public String findBestMove() //Returns the sequence of best moves
	{
		System.out.println("The AI is thinking . . .");
		root.buildTree(0);
		System.out.println(root.bestNextMove);
		return root.bestNextMove;
	}
}
