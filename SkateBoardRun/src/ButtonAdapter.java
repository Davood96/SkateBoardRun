
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;


@SuppressWarnings("serial")
public abstract class ButtonAdapter extends JButton implements ActionListener
{
	private static Timer timer = new Timer();
	private static Scheduler scheduler = null;
	
	/**
	 * Creates a new button adapter with label
	 * @param Button_Symbol - the label
	 */
	public ButtonAdapter(String Button_Symbol)
	{
		super(Button_Symbol);
		addActionListener(this);
		
	}
	/**
	 * Performs a set of instructions when a ButtonAdapter
	 * pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		pressed();
	}
	/**
	 * The actions once a button is pressed
	 */
	public abstract void pressed();
	
	private class Scheduler extends TimerTask
	{

		@Override
		public void run()
		{
			pressed();
		}
		
	}
	
	public Timer getTimer()
	{
		return timer;
	}
	
	
}
