
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * This class is responsible for detecting consecutive
 * clicks, executing a new trick relative to said
 * number of consecutive presses
 * @author You're back!
 *
 */
public class Trick implements ActionListener
{
	//private MoveChild execution;
	protected Timer t;
	protected CharacterModel usr;
	int count = 0;
	int frame = 0;
	long before;
	
	public Trick(CharacterModel player)
	{
		t = new Timer(18, this);
		//execution = new MoveChild(player, player.xPos, t);
		usr = player;
		move();
	}
	/**
	 * Begin detection thread
	 */
	public void move() 
	{
		//System.out.println("Called start");
		//System.out.println(System.currentTimeMillis());
		t.start();
	}
	/**
	 * The detection thread
	 */
	public void run() 
	{
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
		if(++frame - ((count + 1) * 3) > 3)
		{
			t.stop();
			System.out.println("Consecutive presses = " + count);
			new Trick1(usr);
		}
		
	}
	
	public void increment()
	{
		count++;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		run();
	}

	
}

