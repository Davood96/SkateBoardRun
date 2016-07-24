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
	Clip moving = null;
	Clip hitClip = null;
	
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
			moving = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			moving.open(audioIn);
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
	
	public void jump(CharacterModel player)
	{
		if((code == null) && running)
		{		
			code = new Jump();
			moving.stop();
			jump.setFramePosition(0);
			jump.start();
			code.move(this);
		}
	}
	
	public void fall()
	{
		if(code == null && running)
		{
			code = new Fall(floors);
			code.move(this);
		}
	}
	
	public int getXpos()
	{
		return xPos;
	}
	
	
	public void setJumping(boolean var)
	{
		jumping = var;
	}
	
	public void setRunning(boolean var)
	{
		running = var;
		//System.out.println(running);
		if(running)
		{
			collided = !running;
			reset();
			new Collision(this);
		}
	}
	
	public void trick()
	{
		if( (falling || jumping) && tCode == null)
			tCode = new Trick(this);
		
			
		
		if(tCode != null)
			tCode.increment();
		
	}
	
	public void reset()
	{
		xPos = 128;
		yPos = 320;
		
		minScore = 0;
		offset = 2;
		score = 0;
		running = true;
		currPlat = 0;
		img =  movingImg;
		
		alivePoint = 2;
		trickPoint = 2;
		grindPoint = 2;
		
		boosts.clear();
		
		screenCapVert = 489;
	}
	
	
	public int getYpos()
	{
		return yPos;
	}

	public boolean isCollided() 
	{
		// TODO Auto-generated method stub
		return collided;
	}
	

	public int getGround(int left, int right, int platY) 
	{
		// TODO Auto-generated method stub
		boolean exists = false;
		int index = -1;
		for(int i = 0; !exists && i < grinds.size(); i++)
		{
			int leftPos = grinds.get(i).leftSide();
			int rightPos = grinds.get(i).rightSide();
			
			if(leftPos >= left && rightPos <= right && xPos >= leftPos)
				exists = true;
			
			index = i;
		}
		
		if(!exists || yPos - grinds.get(index).getHeight() > 3)
			return platY;
		return grinds.get(index).getHeight();	
	}

	public void setFallingImg() 
	{
		if(tCode == null && !collided)
			img = fallImg;
		
	}
	
	public void animateJump(int index)
	{
		if(tCode == null && !collided)
			img = jumpImgs[index];
	}

	public void setFalling(boolean b) 
	{
		falling = b;
	}
	
	public void checkGrind()
	{
		if(img == movingImg)
		{
			moving.start();
			moving.loop(Clip.LOOP_CONTINUOUSLY);
			return;
		}
			
		int yPosPlat = floors.get(currPlat).getHeight();
		
		if(yPosPlat != ground && Math.abs(yPos - ground) < 3){
			img = grindImg; addGrind();}
		else
		{
			img = movingImg; 
			moving.stop();
			playLandClip();
		}
		
	}

	public void setCollided(boolean b)
	{
		collided = b;
		moving.stop();
	}

	public void assignElements(ArrayList<Platform> floors2,
			ArrayList<Grind> grinds2) 
	{
		floors = floors2;
		grinds = grinds2;
		
	}

	public void animateTrick(int i) 
	{
		img = trickImgs[i];
		
	}

	public void setCollideImage(int i) 
	{
		if(i < collideImgs.length)
			img = collideImgs[i];
	}
	
	public String getScore()
	{
		return "" + score;
	}

	public void addDefault() 
	{
		score += alivePoint;
		minScore++;
	}
	
	public void addGrind()
	{
		score += grindPoint;
	}
	
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

	public void generateBoost() 
	{
		if(score % 2000 == 0)
			boosts.add(new DoublePoint(xPos + 10 * 64, yPos - 64));	
		
		if(!boosts.isEmpty() && boosts.get(0).x + 32 < xPos)
			boosts.remove(0);
	}

	public void checkBoost() 
	{
		if(boosts.isEmpty())
			return;
		
		Boost first = boosts.get(0);
		if(first.x  - xPos - 30 < 1 && yPos - first.y < 28 )
		{
			System.out.println("Boost collected");
			boosts.remove(0);
			first.changeState(this);
		}
		
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

	
}
