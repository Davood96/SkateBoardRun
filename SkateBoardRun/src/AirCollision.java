/**
 * This class implements mid-air, collision animations
 * @author You're back!
 *
 */
public class AirCollision extends AnimateCollision
{

	public AirCollision(CharacterModel player) 
	{
		super(player);
		
	}

	/**
	 * Executes the animation
	 */
	@Override
	public void run() 
	{
		//New image set after 6 frames
		int index = frame / 6;
		usr.setCollideImage(index < 4 ? index : 3);
			
		System.out.println(count + " Air Collision " + System.currentTimeMillis());
		boolean grounded = 	usr.ground - usr.yPos < 3;
		
		//Task complete when animation completes
		//and player is on ground
		if(grounded && index > 2)
		{
			usr.setRunning(false);
			usr.playHitClip();
			t.stop();
		}
		else
			usr.xPos += 2;
		frame++;
		
	}

}
