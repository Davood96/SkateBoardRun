/**
 * This class is responsible for carrying out
 * ground collision animations
 * @author You're back!
 *
 */
public class GroundCollision extends AnimateCollision
{

	public GroundCollision(CharacterModel player) 
	{
		super(player);
	}

	@Override
	public void run() 
	{
		//Animation runs for 18 frames
		
		
		int index = frame++ / 6;
		usr.setCollideImage(index);
		
		switch(index)
		{
			case 3:
				t.stop();
				usr.setRunning(false);
				break;
				
			default:
				usr.xPos -= 1;
		}
	
	}

}
