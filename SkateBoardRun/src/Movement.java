import java.util.Timer;


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
