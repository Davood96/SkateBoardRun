import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


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
	
	
	public void initiateJump()
	{
		if(model.isCollided())
			return;
		model.setJumping(true);
		model.jump(model);
	}
	
	public void initateFall()
	{
		model.fall();
	}
	
	public void initiateTrick()
	{
		model.trick();
	}
	
	public void initiateStart()
	{
		model.setRunning(true);
	}
	
	public BufferedImage getImg()
	{
		return model.img;
	}
	
	public int getxPos()
	{
		return model.getXpos();
	}
	
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


	public void checkScore() 
	{
		if(model.score > player.getScore());
			try {
				player.updateScores(model.score);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
			}
		
	}
	
	public void openRestartMenu(View view)
	{
		if(menu_cntrl == null)
			menu_cntrl = new MenuController(view);
		
		menu_cntrl.popUp();
	}


	public void reset() 
	{
		model.reset();
		player.setPositions(getxPos(), getyPos());
	}
	
	public BufferedImage getBrickImg()
	{
		return model.getBrick();
	}

	public BufferedImage getRailImg()
	{
		return model.getRail();
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


	public BufferedImage getBoostImg(Boost b) 
	{
		return model.getBoostImg(b);
	}



}
