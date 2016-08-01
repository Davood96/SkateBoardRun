import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * This class is responsible for drawing and updating
 * the level's properties
 * @author You're back!
 *
 */
public class Character extends JPanel 
{
	//The left side of the rightmost platform
	private int leftEnd;
	
	private int changeInGrowth;
	protected ArrayList<Platform> floors = new ArrayList<Platform>();
	protected ArrayList<Grind> grinds = new ArrayList<Grind>();
	private CharacterController cntrl;
	private int highScore = 0;
	private int[] scores = new int[3];
	private String[] names = new String[3];
	FileInputStream fileIn = null;
	
	
	public Character()
	{
		//Done using WindowBuilder Interface
		super();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		setVisible(true);
		
		try {
			fileIn = new FileInputStream("src//Resources//Scores.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		readScore();
	}
	
	private void readScore() 
	{
		Scanner scanner = new Scanner(fileIn);
		int i = 0;
		Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\s+)([0-9]+)");
		//System.out.print("hi" + scanner.nextLine());
		while(scanner.hasNextLine())
		{
			String text = scanner.nextLine();
			Matcher match = pattern.matcher(text);
			match.matches();
			names[i] = match.group(1);
			scores[i++] = Integer.parseInt(match.group(3));
		}
		
		highScore = scores[0];
			
		try {
			fileIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		scanner = null;
	}

	/**
	 * Sets the controller for each panel
	 * @param control -  the controller object
	 */
	public void setControl(CharacterController control)
	{
		cntrl = control;
	}
	
	/**
	 * Drawing done here
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.cyan);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.translate(-cntrl.getxPos() , -cntrl.getyPos() + 320);
	    
		//Remove platform that goes off-screen
		//This will always be the first object in the list
		//if the starting conditions are set correctly
		if(floors.get(0).rightSide() <= cntrl.getxPos() - 30)
			floors.remove(0);
		
		//Remove grind that goes off-screen
		if(grinds.get(0).rightSide() <= cntrl.getxPos() - 30)
			grinds.remove(0);
		
		//generate new platform when
		//right most platform appears on screen
		if(leftEnd - cntrl.getxPos() < 600 )
			generateNewPlat();
		
		Graphics2D g2d = (Graphics2D) g;
		//Draw player
		g2d.drawImage(cntrl.getImg(), cntrl.getxPos(), cntrl.getyPos(), 30, 40, null);
		//Draw score
		g.drawString(cntrl.getScore(), cntrl.getxPos() + 500, cntrl.getyPos() - 300);
		
		//Draw grinds
		for(int index = 0; index < grinds.size() && grinds.get(index).leftSide() - cntrl.getxPos() < 574 ; index++)
		{
			Grind h = grinds.get(index);
			g2d.drawImage(cntrl.getRailImg(), h.leftSide() + 30, h.getHeight() + 40, h.rightSide() - h.leftSide() , 19, null);
		}
	
		//Draw platforms
		for(int index = 0; index < floors.size() && floors.get(index).leftSide() - cntrl.getxPos() < 574; index++)
		{
			Platform p = floors.get(index);
			for(int j = p.getHeight() + 40; j < cntrl.getVertCap(); j += 64)
			{
				for( int i = p.leftSide() + 30;i < p.rightSide() + 30; i += 64)
					g2d.drawImage(cntrl.getBrickImg(),i, j, 64,64, null);
			}
		}
	
		for(Boost b : cntrl.getBoosts())
			g2d.drawImage(cntrl.getBoostImg(b), b.x, b.y, 32, 32, null);
		
		
		
	}
	
	/**
	 * Regeneration of a new platform 
	 */
	public void generateNewPlat() 
	{
		int index = floors.size() - 1;
		int rightEnd = floors.get(index).rightSide();
		leftEnd = floors.get(index).leftSide();
		int prevHeight = floors.get(index).getHeight();
		int diff = cntrl.getNumericScor() - cntrl.getxPos()   + 128 - changeInGrowth;
		changeInGrowth += diff;
		int offset = diff < 1 ? generateRandomOffset() : 3;
		int newHeight = prevHeight < cntrl.getyPos() ? prevHeight + 8 * offset: prevHeight - 8 * offset;
		floors.add(new Platform(rightEnd, rightEnd + 64 * offset, newHeight));
		if(offset > 1)
			grinds.add(new Grind(rightEnd + 30, rightEnd + 64 *--offset, newHeight - 16));
		
	}

	/**
	 * Set the position of the player
	 * @param x - x-coordinate
	 * @param y - y-coordinate
	 */
	public void setPositions(int x, int y)
	{
		floors.clear();
		grinds.clear();
		floors.add(new Platform(x - 64, x + 128, y));
		floors.add(new Platform(x + 128, x + 640, y - 16));
		
		floors.add(new Platform(x + 640, x + (15 * 64), y + 16));
		grinds.add(new Grind(x + 640 + 30, x + (12 * 64), y));
		
		leftEnd = x  ; 
		changeInGrowth = 0;
	}

	/**
	 * Returns the current score
	 * @return - the current score
	 */
	public int getScore() 
	{
		// TODO Auto-generated method stub
		return highScore;
	}
	

	/**
	 * Updates the scores of the level
	 * @param newScore - the most recent score
	 * @throws IOException
	 */
	public void updateScores(int newScore) throws IOException 
	{
		String name = JOptionPane.showInputDialog(this, "New HighScore! Enter name below");
		if(name == null)
			name = "Voldemort";
		for(int i = 0; i < scores.length;i++)
		{
			int tmpScore = scores[i];
			String tmpName = names[i];
			if(newScore > tmpScore)
			{
				scores[i] = newScore;
				newScore = tmpScore;
				
				names[i] = name;
				name = tmpName;
			}
		}
		
		FileWriter fw = new FileWriter("src//Resources//Scores.txt");
		 
		for (int i = 0; i < 3; i++) 
		{
			fw.write(names[i] + " " +  scores[i]);
			fw.write(System.lineSeparator());
		}
	 
		fw.close();
	}

	/**
	 * Return the player's x position
	 * @return - the player's x position
	 */
	public int getXpos() 
	{
		// TODO Auto-generated method stub
		return cntrl.getxPos();
	}

	/**
	 * Return the player's y position
	 * @return - the player's y position
	 */
	public int getYpos() 
	{
		// TODO Auto-generated method stub
		return cntrl.getyPos();
	}
	
	private int generateRandomOffset()
	{
		return (cntrl.getyPos() % 2) + 1;
	}

	public int[] getScores() 
	{
		int[] copy = Arrays.copyOf(scores, scores.length);
		return copy;
	}

	public String[] getNames() 
	{
		// TODO Auto-generated method stub
		return Arrays.copyOf(names, names.length);
	}

	public int lowestScore() 
	{
		// TODO Auto-generated method stub
		return scores[scores.length - 1];
	}
}
