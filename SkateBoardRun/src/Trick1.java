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
		usr.setCollided(usr.ground - usr.yPos   < 3);
		usr.animateTrick(frame / 6);
		
		int option = usr.isCollided() ? 0 : frame / 6 + 1;
		System.out.println("Option = "  + option);
		switch(option)
		{
			case 3:
				usr.addTrick1();
				terminate();
				break;
				
			case 0:
				terminate();
				usr.stopMotion();
				new TrickCollision(usr);
				break;
		
		}
		
		
		frame++;
	}

}
