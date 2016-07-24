import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class Fall extends Movement
{
	protected ArrayList<Platform> floor;
	
	public Fall(ArrayList<Platform> floors)
	{
		floor = floors;
	}
	
	@Override
	public  void move(CharacterModel player) 
	{
		Timer t = new Timer();
		t.scheduleAtFixedRate(new MoveChild(player, t), 0, 18);
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
			for(int i = 0;!found && i < floor.size(); i++)
			{
				int currLeft = floor.get(i).leftSide();
				int currRight = floor.get(i).rightSide();
				
				if(currLeft <= currPos && currPos <= currRight)
					found = true;
				index = i;
					
			}
			usr.currPlat = index;
			int leftBound = floor.get(index).leftSide();
			int rightBound = floor.get(index).rightSide();
			usr.ground = usr.getGround(leftBound, rightBound, floor.get(index).getHeight());
			
			if(usr.yPos - usr.ground < 0)
			{
				usr.yPos += 3;
				usr.screenCapVert += 3;
				usr.setFalling(true);
				usr.setFallingImg();
				
			}
			else
			{
				if(!usr.isCollided())
					usr.checkGrind();
				usr.setFalling(false);
				usr.code = null;
				timer.cancel();
			} 
		}
		
	}
	
}
