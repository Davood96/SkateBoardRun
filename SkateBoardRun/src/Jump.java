import java.util.Timer;
import java.util.TimerTask;



public class Jump extends Movement
{
	int count;
	int frame = 0;
	
	@Override
	public  void move(CharacterModel player) 
	{
		Timer t = new Timer();
		count = 0;
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
			if(ground - usr.getYpos() < 65)
			{	
				usr.yPos -= 3;
				usr.screenCapVert -= 3;
				if(frame % 7 == 0){
					usr.animateJump(count);count = 1;}
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
