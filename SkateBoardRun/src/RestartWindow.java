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
	private JLabel scoresList;
	private StringBuilder sb = new StringBuilder();
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
		 scoresList = new JLabel();
		 scoresList.setHorizontalAlignment(SwingConstants.LEFT);
		 
		 JLabel lblScores = new JLabel("Scores");
		 lblScores.setFont(new Font("Times New Roman", Font.BOLD, 20));
		 
		 JLabel lblNewLabel = new JLabel("Position");
		 lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 
		 JLabel lblName = new JLabel("Name");
		 lblName.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 
		 JLabel lblScore = new JLabel("Score");
		 lblScore.setFont(new Font("Times New Roman", Font.BOLD, 11));
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(31)
		 			.addComponent(yes, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
		 			.addComponent(no, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
		 			.addGap(29))
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
		 			.addComponent(lblNewLabel)
		 			.addGap(83)
		 			.addComponent(lblName)
		 			.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
		 			.addComponent(lblScore)
		 			.addGap(27))
		 		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
		 			.addGap(91)
		 			.addComponent(lblTryAgain, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
		 			.addGap(87))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(scoresList, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
		 			.addContainerGap())
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
		 				.addComponent(lblName))
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(scoresList, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblTryAgain, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(yes, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(no, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
		 			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		 );
		 getContentPane().setLayout(groupLayout);
		 
	}
	
	
	
	public void updateView(int playerScore, int[] scores, String[] names)
	{
		 sb.delete(0, sb.length());
		 scoreLabel.setText("Your Score : " + playerScore);
		 for(int i = 0; i < scores.length; i++)
		 {
			String next = scores[i] > 0 ? String.format("<html>%d. %s %d<br><html>", i + 1, 
					names[i], scores[i]) : "";
			sb.append(next);
		 }
		 scoresList.setText(sb.toString());
	}
}









