/**
 * This class acts as a medium between the separate views.
 * Currently, it acts between the Game and restart windows.
 * @author You're back!
 *
 */
public class MenuController 
{

	private static View level_view;
	private  RestartWindow restart_view;
	
	/**
	 * Creates a new MenuController object with its associated 
	 * windows
	 * @param view - the game window
	 */
	public MenuController(View view)
	{
		level_view = view;
		restart_view = new RestartWindow(this);
	}
	
	/**
	 * Closes restart window and restarts game
	 */
	public  void restart()
	{
		restart_view.setVisible(false);
		level_view.reset();
	}
	/**
	 * Close game
	 */
	public void shutdown()
	{
		System.exit(0);
	}
	/**
	 * Opens restart window
	 */
	public void popUp()
	{
		restart_view.setLocationRelativeTo(level_view);
		restart_view.setVisible(true);
	}
	
	
}
