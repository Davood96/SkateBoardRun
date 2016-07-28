import java.util.Timer;

/**
 * Pretty useless. Will clean-up later
 * @author You're back!
 *
 */
public abstract class Movement 
{
	public static boolean state = false;
	
	public  abstract void move(CharacterModel player);

	public void setState(boolean b) 
	{
		state  = b;
	}

	public boolean getState() 
	{
		// TODO Auto-generated method stub
		return state ;
	}


}
