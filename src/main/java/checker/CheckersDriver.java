package checker;//This is the starting screen, where you choose 1-player or two-player game

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckersDriver extends JFrame implements ActionListener
{
	public CheckersDriver(String title)
	{
		super(title);
		setSize(400,200);
		setLayout(new GridLayout(2,1));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton onePlayer = new JButton("Play as black");
		add(onePlayer);
		onePlayer.addActionListener(this);
		onePlayer.setVisible(true);
		onePlayer.setActionCommand("Play as black");
		
		JButton twoPlayer = new JButton("Play as red");
		add(twoPlayer);
		twoPlayer.addActionListener(this);
		twoPlayer.setVisible(true);
		twoPlayer.setActionCommand("Play as red");
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getActionCommand().equals("Play as black"))
		{
			CheckersGUIPruning game = new CheckersGUIPruning("Human vs. Computer", true);
			game.setVisible(true);
		}
		else if(evt.getActionCommand().equals("Play as red"))
		{
			CheckersGUIPruning game = new CheckersGUIPruning("Human vs. Human", false);
			game.setVisible(true);
		}
		this.setVisible(false);
		this.dispose();
	}
	
	public static void main(String[] args)
	{
		CheckersDriver hello = new CheckersDriver("Welcome! One or Two Player Game?");
		hello.setVisible(true);
	}
}
