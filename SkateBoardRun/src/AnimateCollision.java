import java.awt.event.ActionListener;

/**
 * This class acts a base for implementing 
 * collision animations
 * @author You're back!
 *
 */
public abstract class AnimateCollision extends Trick implements ActionListener
{

	public AnimateCollision(CharacterModel player) 
	{
		super(player);
	}
	
	/**
	 * The animation is executed
	 */
	@Override
	public abstract void run();
	

}
