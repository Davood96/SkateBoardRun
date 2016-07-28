/**
 * This class implements floors seen in game
 * @author You're back!
 *
 */
public class Platform 
{
	
	private int xLeft;
	private int xRight;
	private int y;
	/**
	 * Create a new floor
	 * @param x1 - left boundary
	 * @param x2 - right boundary
	 * @param height - y position
	 */
	public Platform(int x1, int x2, int height)
	{
		
		xLeft = x1;
		xRight = x2;
		y = height;
	
	}
	
	public int leftSide()
	{
		return xLeft;
	}
	
	public int rightSide()
	{
		return xRight;
	}
	
	public int getHeight()
	{
		return y;
	}
	
	
}
