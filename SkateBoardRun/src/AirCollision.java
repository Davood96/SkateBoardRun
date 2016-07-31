import java.awt.event.ActionEvent;

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
		int index = frame++ / 6;
		usr.setCollideImage(index < 4 ? index : 3);
			
		System.out.println(count + " Air Collision " + System.currentTimeMillis());
		//boolean grounded = 	usr.ground - usr.yPos < 3;
		
		//Task complete when animation completes
		//and player is on ground
		if(!usr.isAirborne() && index > 2)
		{
			terminate();
			usr.playHitClip();
		}
		else
			usr.xPos += 2;
	
		
	}

	

}
