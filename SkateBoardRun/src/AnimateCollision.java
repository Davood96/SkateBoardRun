import java.awt.event.ActionListener;


public abstract class AnimateCollision extends Trick implements ActionListener
{

	public AnimateCollision(CharacterModel player) 
	{
		super(player);
	}
	
	
	@Override
	public abstract void run();
	

}
