import java.util.Timer;
import java.util.TimerTask;


public class Collision 
{
	private MoveChild detection;
	Timer t = new Timer();
	
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
	
	public void move(CharacterModel player) 
	{
		t.scheduleAtFixedRate(detection, 0, 18);
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
			
			if(wall_collision || usr.isCollided())
			{
				usr.setCollided(true);
				timer.cancel();
			}	
			
			if(wall_collision)
			{
				//usr.playHitClip();
				if(usr.code == null)
					new GroundCollision(usr);
				else if(usr.tCode == null)
					new AirCollision(usr);
			}
			
			usr.xPos += 2; 
			usr.addDefault();
			usr.screenCapHoriz+=2;
			usr.checkBoost();
			usr.generateBoost();
				
				
		}
		
	}
}