import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This class acts a base for implementing 
 * collision animations
 * @author You're back!
 *
 */
public abstract class AnimateCollision implements ActionListener
{
	protected Timer t;
	protected CharacterModel usr;
	int count = 0;
	int frame = 0;
	long before;

	public AnimateCollision(CharacterModel player) 
	{
		t = new Timer(18, this);
		//execution = new MoveChild(player, player.xPos, t);
		usr = player;
		player.movingClip.stop();
		t.start();
	}
	
	
	public void terminate()
	{
		t.stop();
		usr.setRunning(false);
	}
	
	/**
	 * The animation is executed
	 */
	public abstract void run();
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		run();
	}
	

}
