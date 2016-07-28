import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for player jumping
 * @author You're back!
 *
 */
public class Jump extends Movement
{
	int frame = 0;
	//Jumping range
	private static final int RANGE = 65;
	/**
	 * Begin jumping thread
	 */
	@Override
	public  void move(CharacterModel player) 
	{
		Timer t = new Timer();
		Thread jmp = new Thread()
		{
			@Override
			public void run()
			{
				t.scheduleAtFixedRate(new MoveChild(player, player.getYpos(), t), 0, 18);
			}
		};
		jmp.start();
		
	}
	
	
	private class MoveChild extends TimerTask
	{
		private CharacterModel usr;
		private Timer timer;
		private int ground;
		
		public MoveChild(CharacterModel player, int yPos, Timer t) 
		{
			usr = player;
			timer = t;
			ground = yPos;
		}

		@Override
		public void run() 
		{
			if(ground - usr.getYpos() < RANGE)
			{	
				usr.yPos -= 3;
				usr.screenCapVert -= 3;
				int index = frame > 6 ? 1 : 0;
				usr.animateJump(index);
			}
			else
			{
				usr.code = null;
				usr.setJumping(false);
				timer.cancel();	
			}
			frame++;
			
		}
		
	}

			
				
	

}
