import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class represents the physical properties
 * of a player
 * @author You're back!
 *
 */
public class CharacterModel 
{
	
	protected int xPos;
	protected int yPos;
	protected int ground;
	protected int screenCapVert;
	protected int screenCapHoriz;
	protected int score = 1;
	protected int minScore = 1;
	protected int offset;
	protected int trickPoint;
	protected int grindPoint;
	protected int alivePoint;
	
	
	protected BufferedImage img;
	private BufferedImage movingImg;
	private BufferedImage collide1;
	private BufferedImage collide2;
	private BufferedImage collide3;
	private BufferedImage collide4;
	private BufferedImage[] collideImgs;
	private BufferedImage jumpSheet;
	private BufferedImage trickSheet;
	private BufferedImage fallImg;
	private BufferedImage grindImg;
	private BufferedImage brickImg;
	private BufferedImage railImg;
	private BufferedImage doublePointImg;
	private BufferedImage[] jumpImgs = new BufferedImage[2]; 
	private BufferedImage[] trickImgs = new BufferedImage[3]; 
	
	protected ArrayList<Platform> floors;
	protected ArrayList<Grind> grinds;
	protected ArrayList<Boost> boosts = new ArrayList<Boost>();
	
	protected boolean jumping = false;
	protected boolean falling = false;
	protected Movement code = null;
	private boolean running = true;
	private boolean collided = true;
	protected int currPlat = 0;
	protected Trick tCode;
	
	Clip jump = null;
	Clip landing = null;
	Clip movingClip = null;
	Clip hitClip = null;
	
	private Fall gravity;
	private Collision motion;
	
	public CharacterModel()
	{
		reset();
		
		try {
			movingImg = ImageIO.read(new File("src//resources//moving.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fallImg = ImageIO.read(new File("src//resources//falling_only.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			grindImg = ImageIO.read(new File("src//resources//grinding.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			brickImg = ImageIO.read(new File("src//resources//brickTile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			railImg = ImageIO.read(new File("src//resources//GrindRail.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		loadJumpImgs();
		loadTrickImgs();
		loadCollisionImgs();
		loadAudioFile();
		img = movingImg;
	}
	
	private void loadAudioFile() 
	{
		AudioInputStream audioIn = null; 
		try {
			audioIn = AudioSystem.getAudioInputStream(
					new File("src//resources//jump.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jump = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jump.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			audioIn = AudioSystem.getAudioInputStream(
					new File("src//resources//landingSound.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			landing = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			landing.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			audioIn = AudioSystem.getAudioInputStream(
					new File("src//resources//movingSound.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			movingClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			movingClip.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			audioIn = AudioSystem.getAudioInputStream(
					new File("src//resources//hit.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			hitClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			hitClip.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void loadCollisionImgs()
	{
		try {
			collide1 = ImageIO.read(new File("src//resources//Collide1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			collide2 = ImageIO.read(new File("src//resources//Collide2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			collide3 = ImageIO.read(new File("src//resources//Collide3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			collide4 = ImageIO.read(new File("src//resources//Collide4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		collideImgs = new BufferedImage[4];
		collideImgs[0] = collide1;
		collideImgs[1] = collide2;
		collideImgs[2] = collide3;
		collideImgs[3] = collide4;
	}
	
	private void loadTrickImgs()
	{
		try {
			trickSheet = ImageIO.read(new File("src//resources//trick.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 3; i++)
			trickImgs[i] = trickSheet.getSubimage(i * 148, 0, 148, 129);
		
	}
	
	private void loadJumpImgs()
	{
		try {
			jumpSheet = ImageIO.read(new File("src//resources//jumping_only.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 2; i++)
			jumpImgs[i] = jumpSheet.getSubimage(i * 141, 0, 142, 133);
		
	}
	
	/**
	 * Creates a new Jump task
	 * @param player - the model object
	 */
	public void jump(CharacterModel player)
	{
		if(!isAirborne() && running)
		{		
			code = new Jump();
			movingClip.stop();
			setJumping(true);
			jump.setFramePosition(0);
			jump.start();
			code.move(this);
		}
	}
	/**
	 * Returns whether the player is airborne or not
	 * @return - true if airborne. false otherwise
	 */
	public boolean isAirborne() 
	{
		return yPos - ground < 0;
	}

	/**
	 * Creates a new Fall task
	 */
	public void fall()
	{
		System.out.println("fall task new");
		gravity = new Fall(floors);
		gravity.move(this);
			
	}
	/**
	 * Return the player's x position
	 * @return - the player's x position
	 */
	public int getXpos()
	{
		return xPos;
	}
	/**
	 * Set the jumping state of the player
	 * @param var - the jumping state
	 */
	public void setJumping(boolean var)
	{
		jumping = var;
	}
	/**
	 * Set the running state of the game
	 * @param var - the running state
	 */
	public void setRunning(boolean var)
	{
		running = var;
		//System.out.println(running);
		if(running)
		{
			collided = !running;
			reset();
			motion = new Collision(this);
			playMovingClip();
		}
	}
	/**
	 * Execute a trick sequence
	 */
	public void trick()
	{
		
		if(isAirborne() && tCode == null && !collided)
			  tCode = new Trick(this);
		
		if(tCode != null)
			tCode.increment();
		
	}
	
	/**
	 * Reset the model to original state
	 */
	public void reset()
	{
		xPos = 128;
		yPos = 320;
		
		minScore = 0;
		offset = 2;
		score = 0;
		
		currPlat = 0;
		img =  movingImg;
		
		setFalling(false);
		running = true;
		
		alivePoint = 2;
		trickPoint = 2;
		grindPoint = 2;
		
		boosts.clear();
		
		screenCapVert = 489;
	}
	
	/**
	 * Return the player's y position
	 * @return - the player's y position
	 */
	public int getYpos()
	{
		return yPos;
	}
	/**
	 * Returns the collided state of the player 
	 * @return - the player's collided state
	 */
	public boolean isCollided() 
	{
		return collided;
	}
	/**
	 * Returns the player's relative ground
	 * @param left - the left side of the platform directly beneath the player
	 * @param right - the right side of the platform directly beneath the player
	 * @param platY - the index of the platform directly beneath the player
	 * @return the player's relative ground
	 */
	public int getGround(int left, int right, int platY) 
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		int index = -1;
		for(int i = 0; !exists && i < grinds.size(); i++)
		{
			int leftPos = grinds.get(i).leftSide();
			int rightPos = grinds.get(i).rightSide();
			
			exists = (leftPos >= left && rightPos <= right && xPos >= leftPos);
				 
			
			index = i;
		}
		
		if(!exists || yPos - grinds.get(index).getHeight() > 3)
			return platY;
		return grinds.get(index).getHeight();	
	}
	/**
	 * Sets the image of the player to
	 * a falling image
	 * @precondition - the player has not collided nor is he mid-trick
	 */
	public void setFallingImg() 
	{
		img = tCode == null && !collided ? fallImg : img;
	}
	/**
	 * Sets the image of the player to a jumping image
	 * @param index - the particular jumping image
	 * @precondition - the player has not collided
	 */
	public void animateJump(int index)
	{
		img = !collided ? jumpImgs[index] : img;
	}

	public void setFalling(boolean b) 
	{
		boolean landed = !b && falling;
		
		if(landed)
			checkGrind();
		
		falling = b;
	}
	/**
	 * Executes grind sequence or landing sequence
	 */
	public void checkGrind()
	{
		int yPosPlat = floors.get(currPlat).getHeight();
		int option =  yPosPlat != ground ? 0 : 1;
		
		if(!running)
			option += 2;
		
		System.out.println(option);
		
		switch(option)
		{
			case 0:
				startGrind(ground);
				break;
			
			case 1:
				startLanded();
				break;
		}
		
	}

	//Grind sequence
	private void startGrind(int grindHeight) 
	{
		
		img = grindImg;
		//Play grind sound on loop
		while(yPos < grindHeight )
		{
			addGrind();
			System.out.println("grind");
		}
		//End grind playback
	}
	//Landing sequence
	private void startLanded() 
	{
		
		img = movingImg;
		playLandClip();
		
		if(!collided)
			playMovingClip();
		
	}

	private void playMovingClip() 
	{
		movingClip.setFramePosition(0);
		movingClip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}

	public void setCollided(boolean b)
	{
		collided = b;
		//movingClip.stop();
	}
	/**
	 * Creates copies of references for platforms and grinds lists
	 * @param floors2 - the platform list
	 * @param grinds2 - the grind list
	 */
	public void assignElements(ArrayList<Platform> floors2,
			ArrayList<Grind> grinds2) 
	{
		floors = floors2;
		grinds = grinds2;
		
	}
	/**
	 * Sets the image of the player to a trick image
	 * @param i - the particular trick image
	 */
	public void animateTrick(int i) 
	{
		img = trickImgs[i];
		
	}

	public void setCollideImage(int i) 
	{
		img = collideImgs[i];
	}
	
	public String getScore()
	{
		return "" + score;
	}
	/**
	 * Adds default score
	 */
	public void addDefault() 
	{
		score += alivePoint;
		minScore++;
	}
	/**
	 * Adds points for grinding
	 */
	public void addGrind()
	{
		score += grindPoint;
	}
	/**
	 * Adds points for executing a trick
	 */
	public void addTrick1()
	{
		score += trickPoint;
	}

	public boolean getRunning()
	{
		return running;
	}
	
	public BufferedImage getBrick()
	{
		return brickImg;
	}
	
	public BufferedImage getRail()
	{
		return railImg;
	}
	
	public BufferedImage getDoublePoint()
	{
		return doublePointImg;
	}

	/**
	 * Generates a new boost object every 2000 points
	 * Removes an uncollected boost once player has passed
	 */
	public void generateBoost() 
	{
		//Generate
		if(score % 2000 == 0)
			boosts.add(new DoublePoint(xPos + 10 * 64, yPos - 64));	
		//Remove
		if(!boosts.isEmpty() && boosts.get(0).x + 32 < xPos)
			boosts.remove(0);
	}
	/**
	 * Checks if player has collected a boost
	 */
	public void checkBoost() 
	{ 
		Boost first = boosts.get(0);
			
		if(inBoostRange(first))
		{
			System.out.println("Boost collected");
			boosts.remove(first);
			first.changeState(this);
		}
		
	}
	
	private boolean inBoostRange(Boost boost)
	{
		return boost.x  - xPos - 30 < 1 && yPos - boost.y < 28;
	}

	public BufferedImage getBoostImg(Boost b) 
	{
		b.animate();
		return b.getImg();
	}
	
	public void playHitClip()
	{
		hitClip.setFramePosition(0);
		hitClip.start();
	}
	
	public void playLandClip()
	{
		landing.setFramePosition(0);
		landing.start();
	}
	/**
	 * Terminates the gravity thread
	 */
	public void endGravity() 
	{
		gravity.terminate();
	}
	/**
	 * Terminates the motion thread
	 */
	public void stopMotion() 
	{
		motion.terminate();
		
	}

	
}
