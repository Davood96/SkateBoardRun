import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * This class implements the Restart window
 * Most of it done using WindowBuilder interface
 * @author You're back!
 *
 */
public class RestartWindow extends JFrame 
{
	public RestartWindow(MenuController control, int playerScore, int[] scores, String[] names)
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
		 
		 JLabel scoreLabel = new JLabel("Your Score : " + playerScore);
		 scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		 scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 StringBuilder sb = new StringBuilder();
		 
		 for(int i = 0; i < scores.length; i++)
			sb.append(String.format("<html>%d. %s %d<br><html>", i + 1, names[i], scores[i]));
		 
		 
		 JLabel scoresList = new JLabel(sb.toString());
		 scoresList.setHorizontalAlignment(SwingConstants.CENTER);
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(30)
		 			.addComponent(yes, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
		 			.addComponent(no, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
		 			.addGap(29))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(63)
		 			.addComponent(scoreLabel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(66, Short.MAX_VALUE))
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
		 			.addPreferredGap(ComponentPlacement.UNRELATED)
		 			.addComponent(scoresList, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblTryAgain, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(yes, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(no, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
		 			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		 );
		 getContentPane().setLayout(groupLayout);
		
	}
}









