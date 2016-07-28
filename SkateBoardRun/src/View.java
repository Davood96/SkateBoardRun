import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;

import javax.swing.JLayeredPane;
import java.awt.Label;


public class View extends JFrame 
{

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	private Character player1;
	private CharacterController cntrl;
	private JPanel buttons = new JPanel();
	private ButtonAdapter start;
	
	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public View()
	{
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		buttons.setOpaque(false);
		
		BackGroundPanel panel = new BackGroundPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(buttons, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttons, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		player1 = new Character();
		player1.setOpaque(false);
		cntrl = new CharacterController(player1);
		player1.setControl(cntrl);
		GroupLayout gl_player1 = new GroupLayout(player1);
		gl_player1.setHorizontalGroup(
			gl_player1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 564, Short.MAX_VALUE)
		);
		gl_player1.setVerticalGroup(
			gl_player1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 468, Short.MAX_VALUE)
		);
		player1.setLayout(gl_player1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(player1, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(player1, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		buttons.setBackground(Color.GRAY);
		buttons.setForeground(Color.GREEN);
		getContentPane().setLayout(groupLayout);
		buttons.setVisible(true);
		ButtonAdapter jump;
		buttons.add(jump = new ButtonAdapter("Jump")
		{
			public void pressed()
			{
				//player1.setJumping(true);
				//player1.jump();
				cntrl.initiateJump();
			}
		});
		jump.setBackground(Color.BLACK);
		jump.setForeground(Color.YELLOW);
		
		
		buttons.add(start = new ButtonAdapter("start")
		{
			public void pressed()
			{
				cntrl.initiateStart();
				setVisible(false);
				//Must make new Thread of execution
				//to release Event Dispatch Thread
				Thread begin = new Thread()
				{
					@Override
					public void run()
					{
						execute();
					}
				};
				begin.start();
			}
		});
		
		ButtonAdapter trick;
		buttons.add(trick = new ButtonAdapter("Trick")
		{
			public void pressed()
			{
				cntrl.initiateTrick();
			}
		});
		GroupLayout gl_buttons = new GroupLayout(buttons);
		gl_buttons.setHorizontalGroup(
			gl_buttons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttons.createSequentialGroup()
					.addGap(59)
					.addComponent(start, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
					.addComponent(jump, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(trick, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_buttons.setVerticalGroup(
			gl_buttons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttons.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buttons.createParallelGroup(Alignment.TRAILING)
						.addComponent(jump, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
						.addComponent(trick, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
						.addComponent(start, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
					.addGap(71))
		);
		buttons.setLayout(gl_buttons);
		setResizable(false);
		setVisible(true);
		
	}
	

	public void execute()
	{
			long lastLoopTime = System.currentTimeMillis();
			long lastFPSTime = 0;
			final int TARGET_FPS = 60;
			final long OPTIMAL_TIME = 1000/TARGET_FPS;
			int fps = 0;
			double secs = 0;
		
			cntrl.initateFall();
			while(cntrl.getRunning())
			{
				long current = System.currentTimeMillis();
				long updateTime = current - lastLoopTime;
				lastLoopTime = current;
				double delta = updateTime/((double)OPTIMAL_TIME);
				lastFPSTime+=updateTime;
				secs+=updateTime;
				fps++;
	
				if(lastFPSTime>=1000)
				{
					System.out.println("FPS =" + fps);
					lastFPSTime=0;
					fps=0;
					
				}
			
				try
				{
					Thread.sleep(System.currentTimeMillis()-lastLoopTime + OPTIMAL_TIME);
				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				player1.repaint();
			}
			cntrl.endGravity();
			cntrl.checkScore();
			cntrl.openRestartMenu(this);
	
  }
	
	
	public void reset() 
	{
		cntrl.reset();
		player1.repaint();
		start.setVisible(true);
	}
}
