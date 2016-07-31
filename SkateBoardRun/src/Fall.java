import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for gravity
 * @author You're back!
 *
 */
public class Fall extends Movement
{
	protected ArrayList<Platform> floor;
	private Timer t;
	
	public Fall(ArrayList<Platform> floors)
	{
		floor = floors;
	}
	/**
	 * Begins a new gravity thread
	 */
	@Override
	public  void move(CharacterModel player) 
	{
		t = new Timer();
		t.scheduleAtFixedRate(new MoveChild(player, t), 0, 18);
	}
	/**
	 * Terminates a gravity thread
	 */
	public void terminate()
	{
		t.cancel();
	}
	
	private class MoveChild extends TimerTask
	{
		private CharacterModel usr;
		private Timer timer;
		
		public MoveChild(CharacterModel player, Timer t) 
		{
			usr = player;
			timer = t;
		}

		@Override
		public void run() 
		{
			int currPos = usr.getXpos();
			 
			boolean found = false;
			int index = 0;
			
			//Find platform directly below player
			for(int i = 0;!found && i < floor.size(); i++)
			{
				int currLeft = floor.get(i).leftSide();
				int currRight = floor.get(i).rightSide();
				
				found = currLeft <= currPos && currPos <= currRight;	
				index = i;
					
			}
			
			int platY = floor.get(index).getHeight();
			usr.currPlat = index;
			int leftBound = floor.get(index).leftSide();
			int rightBound = floor.get(index).rightSide();
			usr.ground = usr.getGround(leftBound, rightBound, platY);
			usr.setFalling(!usr.jumping && usr.yPos - usr.ground < 0);
			
			if(usr.falling)
			{
				usr.yPos += 3;
				usr.screenCapVert += 3;
				usr.setFallingImg();
				usr.movingClip.stop();
			}
		}
		
	}

	
}
