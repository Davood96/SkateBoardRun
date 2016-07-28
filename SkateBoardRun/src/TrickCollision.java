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
		// TODO Auto-generated constructor stub
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
				usr.setRunning(false);
				usr.tCode = null;
				t.stop();
				
			default:
				usr.xPos += 1;

		}
			
		
	}

}
