
public class AirCollision extends AnimateCollision
{

	public AirCollision(CharacterModel player) 
	{
		super(player);
		
	}

	@Override
	public void run() 
	{
		//New image set after 6 frames
		if(frame % 6 == 0)
			usr.setCollideImage(++count);
			
		System.out.println(count + " Air Collision " + System.currentTimeMillis());
		boolean grounded = 	usr.ground - usr.yPos < 3;
		
		//Task complete when animation completes
		//and player is on ground
		if(grounded && count > 2)
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
