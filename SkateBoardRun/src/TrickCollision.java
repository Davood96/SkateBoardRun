import java.awt.event.ActionEvent;

/**
 * This class implements animation for 
 * Trick collisions
 * @author You're back!
 *
 */
public class TrickCollision extends AnimateCollision
{

	public TrickCollision(CharacterModel player) 
	{
		super(player);
	}
	
	@Override
	public void terminate()
	{
		t.stop();
		usr.setRunning(false);
		usr.tCode = null;
	}
	
	@Override
	public void run() 
	{
		//Animation is 18 frames
		
		int index = frame++ / 6;
		usr.setCollideImage(index);
	
		switch(index)
		{
			case 3:
				usr.playHitClip();
				terminate();
				break;
				
			default:
				usr.xPos += 1;

		}
			
		
	}

}
