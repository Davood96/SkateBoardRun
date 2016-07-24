
public class MenuController 
{

	private static View level_view;
	private  RestartWindow restart_view;
	
	
	public MenuController(View view)
	{
		level_view = view;
		restart_view = new RestartWindow(this);
	}
	
	
	public  void restart()
	{
		restart_view.setVisible(false);
		level_view.reset();
	}
	
	public void shutdown()
	{
		System.exit(0);
	}
	
	public void popUp()
	{
		restart_view.setLocationRelativeTo(level_view);
		restart_view.setVisible(true);
	}
	
	
}
