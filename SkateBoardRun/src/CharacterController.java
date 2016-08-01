import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class acts as a medium between CharacterModel
 * and the Character panel
 * @author You're back!
 *
 */
public class CharacterController 
{
	
	private Character player;
	private CharacterModel model;
	private static MenuController menu_cntrl = null;
	
	
	public CharacterController(Character user)
	{
		player = user;
		model = new CharacterModel();
		model.reset();
		player.setPositions(getxPos(), getyPos());
		model.assignElements(player.floors, player.grinds);
	}
	
	/**
	 * Instruct the model to begin its jump sequence
	 * @precondition - the player has not collided
	 */
	public void initiateJump()
	{
		if(!model.isCollided())
			model.jump(model);
		
	}
	/**
	 * Instruct the model to initiate its fall sequence
	 */
	public void initateFall()
	{
		model.fall();
	}
	/**
	 * Instruct the model to initiate its trick sequence
	 */
	public void initiateTrick()
	{
		model.trick();
	}
	/**
	 * Instruct the model to begin
	 */
	public void initiateStart()
	{
		model.setRunning(true);
	}
	/**
	 * Returns the current player image
	 * @return - the current player image
	 */
	public BufferedImage getImg()
	{
		return model.img;
	}
	/**
	 * Returns the player's x position
	 * @return - the player's x position
	 */
	public int getxPos()
	{
		return model.getXpos();
	}
	/**
	 * Returns the player's y position
	 * @return - the player's y position
	 */
	public int getyPos()
	{
		return model.yPos;
	}
	
	public boolean getRunning()
	{
		//System.out.println(model.running);
		return model.getRunning();
	}
	
	public boolean isCollided()
	{
		return model.isCollided();
	}


	public String getScore() 
	{
		// TODO Auto-generated method stub
		return model.getScore();
	}

	/**
	 * Instructs the level to update its top 3 scores
	 * @precondition - the user's current score is larger than
	 * the current level's lowest score
	 */
	public void checkScore() 
	{
		if(model.score > player.lowestScore())
		{
			try {
				player.updateScores(model.score);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Instructs the restart menu to pop-up
	 * @param view - the main gui
	 */
	public void openRestartMenu(View view)
	{
		if(menu_cntrl == null)
			menu_cntrl = new MenuController(view);
		
		menu_cntrl.popUp();
	}

	/**
	 * Resets model to its default state
	 */
	public void reset() 
	{
		model.reset();
		player.setPositions(getxPos(), getyPos());
	}
	/**
	 * Returns image of brick
	 * @return - returns image of brick
	 */
	public BufferedImage getBrickImg()
	{
		return model.getBrick();
	}
	/**
	 * Returns image of grind-rail
	 * @return - image of grind-rail
	 */
	public BufferedImage getRailImg()
	{
		return model.getRail();
	}
	
	public void endGravity()
	{
		model.endGravity();
	}
	
	public BufferedImage getDoublePointImg()
	{
		return model.getDoublePoint();
	}

	
	public int getVertCap() 
	{
		// TODO Auto-generated method stub
		return model.screenCapVert;
	}


	public int getHorizCap() 
	{
		// TODO Auto-generated method stub
		return model.screenCapHoriz;
	}
	
	public int getNumericScor()
	{
		return model.score;
	}

	/**
	 * Returns a list of boost objects
	 * @return - a list of boost objects
	 */
	public ArrayList<Boost> getBoosts() 
	{
		// TODO Auto-generated method stub
		return model.boosts;
	}

	public int getTrickPoints() 
	{
		// TODO Auto-generated method stub
		return model.trickPoint;
	}

	/**
	 * Returns a boost's visual representation
	 * @param b - the boost
	 * @return - the image
	 */
	public BufferedImage getBoostImg(Boost b) 
	{
		return model.getBoostImg(b);
	}

	public int[] getScores() 
	{
		return player.getScores();
	}

	public String[] getNames() 
	{
		// TODO Auto-generated method stub
		return player.getNames();
	}


}
