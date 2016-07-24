import java.util.Timer;
import java.util.TimerTask;


public abstract class Move extends TimerTask
{
		protected Character usr;
		protected int ground;
		protected Timer t;
		
		public Move(Character player, int floor, Timer timer)
		{
			usr = player;
			ground = floor;
			t = timer;	
		}
		
}
