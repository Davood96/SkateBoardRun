import java.awt.image.BufferedImage;
import java.util.Timer;

/**
 * This class provides the base for implementing
 * pick-ups (e.g. Double points, Double speed)
 * @author You're back!
 *
 */
public abstract class Boost 
{
	int x;
	int y;
	BufferedImage[] img;
	BufferedImage sheet;
	int index;
	int frames;
	
	public Boost(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
		frames = 0;
	}
	
	public BufferedImage getImg()
	{
		return img[index];
	}
	

	/**
	 * Changes state of the player
	 * @param usr - the player
	 */
	public abstract void changeState(CharacterModel usr);

	/**
	 * Executes boost animation
	 */
	public void animate() 
	{
		frames++;
		if(frames % 5 == 0)
			index = (index + 1) % img.length;
	}
}
