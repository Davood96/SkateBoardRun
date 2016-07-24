
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
		if(frame % 6 == 0)
			usr.setCollideImage(++count);
			
		System.out.println(count + " " + System.currentTimeMillis());
			
		if(count  > 2)
		{
			usr.setRunning(false);
			usr.playHitClip();
			t.stop();
		}
		else
			usr.xPos += 1;
		//usr.repaint();
		frame++;
	}

}
