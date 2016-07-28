import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for horizontal player motion,
 * which includes collision detection
 * @author You're back!
 *
 */
public class Collision 
{
	private MoveChild detection;
	Timer t = new Timer();
	/**
	 * Creates a new motion thread
	 * @param player - the model
	 */
	public Collision(CharacterModel player)
	{
		detection = new MoveChild(player, player.getXpos(), t);
		Thread thrd = new Thread()
		{
			@Override
			public void run()
			{
				move(player);
			}
		};
		thrd.start();
		//move(player);
	}
	/**
	 * Begin the motion thread
	 * @param player - the model
	 */
	public void move(CharacterModel player) 
	{
		t.scheduleAtFixedRate(detection, 0, 18);
	}
	/**
	 * End the motion thread
	 */
	public void terminate() 
	{
			t.cancel();
	}
	
	private class MoveChild extends TimerTask
	{
		private CharacterModel usr;
		private int x;
		private Timer timer;
		
		public MoveChild(CharacterModel player, int xPos, Timer t)
		{
			usr = player;
			x = xPos;
			timer = t;
		}
		
		

		@Override
		public void run() 
		{
			int nextWall = usr.floors.get(usr.currPlat).rightSide();
			int height = usr.floors.get(usr.currPlat + 1).getHeight();
			boolean wall_collision = (nextWall - usr.xPos < 3  && usr.yPos - height > 2);
			usr.setCollided(wall_collision);
			
			
			int option = usr.isCollided() ? 1 : 0;
			option =  !usr.isAirborne() ? option + 2 : option;
			
			switch(option)
			{
				case 1:
					new AirCollision(usr);
					terminate();
					break;
					
				case 3:
					new GroundCollision(usr);
					terminate();
					break;
					
			}
			
			
			usr.xPos += 2; 
			usr.addDefault();
			usr.screenCapHoriz+=2;
			usr.checkBoost();
			usr.generateBoost();
				
				
		}
		
	}

	
}