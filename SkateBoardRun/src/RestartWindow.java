import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * This class implements the Restart window
 * Most of it done using WindowBuilder interface
 * @author You're back!
 *
 */
public class RestartWindow extends JFrame 
{
	private JLabel scoreLabel;
	private JLabel numbersList;
	private JLabel namesList;
	private JLabel scoresList;
	
	public RestartWindow(MenuController control)
	{
		super();
		setSize(300,300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ButtonAdapter yes = new ButtonAdapter("Yes")
		{
			public void pressed()
			{
				control.restart();
			}
		};
		ButtonAdapter no = new ButtonAdapter("No")
		{
			public void pressed()
			{
				control.shutdown();
			}
		};
 
		 JLabel lblTryAgain = new JLabel("Try Again?");
		 lblTryAgain.setFont(new Font("Times New Roman", Font.BOLD, 20));
		 lblTryAgain.setForeground(Color.BLUE);
		 lblTryAgain.setHorizontalAlignment(SwingConstants.CENTER);
		 
		 scoreLabel = new JLabel();
		 scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		 scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 numbersList = new JLabel();
		 numbersList.setVerticalAlignment(SwingConstants.TOP);
		 
		 JLabel lblScores = new JLabel("Scores");
		 lblScores.setFont(new Font("Times New Roman", Font.BOLD, 20));
		 
		 JLabel lblNewLabel = new JLabel("Position");
		 lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 
		 JLabel lblName = new JLabel("Name");
		 lblName.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 
		 JLabel lblScore = new JLabel("Score");
		 lblScore.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 
		 namesList = new JLabel();
		 namesList.setVerticalAlignment(SwingConstants.TOP);
		 namesList.setHorizontalAlignment(SwingConstants.CENTER);
		 
		 scoresList = new JLabel();
		 scoresList.setHorizontalAlignment(SwingConstants.CENTER);
		 scoresList.setVerticalAlignment(SwingConstants.TOP);
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.TRAILING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(scoreLabel, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
		 			.addContainerGap())
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(lblScores, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(216, Short.MAX_VALUE))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 						.addComponent(lblNewLabel)
		 						.addComponent(numbersList, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
		 					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
		 						.addGroup(groupLayout.createSequentialGroup()
		 							.addGap(46)
		 							.addComponent(namesList, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
		 							.addGap(18)
		 							.addComponent(scoresList, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
		 						.addGroup(groupLayout.createSequentialGroup()
		 							.addGap(64)
		 							.addComponent(lblName)
		 							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 							.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
		 							.addGap(29))))
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addComponent(yes, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
		 					.addComponent(no, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
		 			.addContainerGap())
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(93)
		 			.addComponent(lblTryAgain, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
		 			.addGap(85))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(20)
		 			.addComponent(scoreLabel)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblScores)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblNewLabel)
		 				.addComponent(lblScore)
		 				.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(scoresList, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
		 				.addComponent(namesList, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
		 				.addComponent(numbersList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblTryAgain, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		 			.addGap(5)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(no, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(yes, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
		 			.addGap(48))
		 );
		 getContentPane().setLayout(groupLayout);
		 
	}
	
	
	
	public void updateList(int[] scores, String[] names)
	{
		
		 String column1 = "";
		 String column2 = "";
		 String column3 = "";
		 
		 for(int i = 0; i < scores.length; i++)
		 { 
			 boolean count = scores[i] > 0;
			 column1 += count ? String.format("<html>%d.<br>", i + 1) : "";
			 column2 += count ? String.format("<html>%s<br>", names[i]) : "";
			 column3 += count ? String.format("<html>%d<br>", scores[i]) : "";
			
		 }
		 
		 numbersList.setText(column1);
		 namesList.setText(column2);
		 scoresList.setText(column3);
	}



	public void updateScore(int playerScore)
	{
		scoreLabel.setText("Your Score : " + playerScore);
	}
}









