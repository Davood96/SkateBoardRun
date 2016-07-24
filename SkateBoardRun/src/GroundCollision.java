
public class GroundCollision extends AnimateCollision
{

	public GroundCollision(CharacterModel player) 
	{
		super(player);
	}

	@Override
	public void run() 
	{
		if(frame % 6 == 0)
			usr.setCollideImage(++count);
			
		System.out.println(count + " " + System.currentTimeMillis());
			
		if(count  > 2)
		{
			usr.setRunning(false);
			//usr.playHitClip();
			t.stop();
		}
		else
			usr.xPos -= 1;
		
		//usr.repaint();
		frame++;
		
	}

}
