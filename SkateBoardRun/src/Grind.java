/**
 * This class stores data for grinds
 * @author You're back!
 *
 */
public class Grind extends Platform
{

	public Grind(int x1, int x2, int height) 
	{
		super(x1, x2, height);
	}

	public int getLength() 
	{
		// TODO Auto-generated method stub
		return rightSide() - leftSide();
	}
	

}
