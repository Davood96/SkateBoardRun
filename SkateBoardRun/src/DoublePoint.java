import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class DoublePoint extends Boost 
{

	public DoublePoint(int xPos, int yPos) 
	{
		super(xPos, yPos);
		super.img = new BufferedImage[14];
		try {
			sheet = ImageIO.read(new File("C://Users//You're back!//Desktop//Resources 2//DoublePointsAnimate.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 14; i++)
			img[i] = sheet.getSubimage(i*67, 0, 67, 68);
		
		super.index = super.img.length - 1;
	}

	@Override
	public void changeState(CharacterModel usr) 
	{
		usr.trickPoint = 4;
		usr.grindPoint = 4;
		Timer t = new Timer();
		Thread thrd = new Thread()
		{
			@Override
			public void run()
			{
				t.scheduleAtFixedRate(new TimedEvent(1, t, usr), 0, 18);
			}
		};
		thrd.start();
		
	}
	
	private class TimedEvent extends TimerTask
	{
		private int frameCounter;
		private Timer time;
		private CharacterModel plyr;
		
		public TimedEvent(int frameCount, Timer timer, CharacterModel player)
		{
			frameCounter = frameCount;
			time = timer;
			plyr = player;
		}

		@Override
		public void run() 
		{
			if(600 - frameCounter++ < 0)
			{
				plyr.trickPoint = 2;
				plyr.grindPoint = 2;
				time.cancel();
			}
			
		}
		
	}

}
