import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public abstract class TrickExecution implements ActionListener 
{

	
		//private MoveChild execution;
		protected Timer t;
		protected CharacterModel usr;
		int count = -1;
		int frame = 0;
		long before;
		
		public TrickExecution(CharacterModel player)
		{
			t = new Timer(18, this);
			//execution = new MoveChild(player, player.xPos, t);
			usr = player;
			move();
		}
		
		public void move() 
		{
			System.out.println("Called start");
			System.out.println(System.currentTimeMillis());
			t.start();
		}
		
		public abstract void run();
		
			/*if(Math.abs(usr.yPos - usr.ground) < 3)
				usr.setCollided(true);
			else if(frame % 6 == 0)
				usr.animateTrick(++count);
				
			System.out.println(count + " " + System.currentTimeMillis());
				
			if(count  > 1 || usr.isCollided())
			{
				usr.tCode = null;
				t.stop();
				if(usr.isCollided())
					new TrickCollision(usr);
			}*/
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			run();
		}
}
