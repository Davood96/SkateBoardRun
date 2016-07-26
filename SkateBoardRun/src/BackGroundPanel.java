import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class implements the background on the gui
 * @author You're back!
 *
 */
public class BackGroundPanel extends JPanel 
{

	private BufferedImage bckgrd;
	public BackGroundPanel()
	{
		super();
		try {
			bckgrd = ImageIO.read(new File("C://Users//You're back!//Desktop//Resources 2//background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bckgrd, 0, 0, getWidth(), getHeight(), null);
	}
}
