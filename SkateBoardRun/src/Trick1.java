import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Trick1 extends TrickExecution 
{

	public Trick1(CharacterModel player) 
	{
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() 
	{
		if(Math.abs(usr.yPos - usr.ground) < 3)
			usr.setCollided(true);
		else if(frame % 6 == 0)
			usr.animateTrick(++count);
		
		System.out.println(count + " " + System.currentTimeMillis());
		
		if(count  > 1 || usr.isCollided())
		{
			t.stop();
			usr.tCode = null;
		}
		
		if(usr.isCollided()){
			new TrickCollision(usr);usr.playLandClip();}
		else
			usr.addTrick1();
		
		frame++;
	}

}
