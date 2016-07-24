import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class RestartWindow extends JFrame 
{
	public RestartWindow(MenuController control)
	{
		super();
		setSize(300,300);
		this.setResizable(false);
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
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(19)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(lblTryAgain, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addComponent(yes, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
		 					.addComponent(no, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
		 			.addContainerGap())
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(23)
		 			.addComponent(lblTryAgain, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
		 			.addGap(32)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(yes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(no, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
		 			.addContainerGap(80, Short.MAX_VALUE))
		 );
		 getContentPane().setLayout(groupLayout);
		
	}
	
	
	
	
	
	
}









