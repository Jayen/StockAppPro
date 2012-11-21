
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *The OpenWindowsChecker class monitors the 
 *open DataTableWindows and uses and arraylist to
 *store all the windows which are currently open. 
 */

public class OpenWindowsChecker 
{
	private static ArrayList<JFrame> windows= new ArrayList<JFrame>();
	
	/**
	 * This method can be used to check if the window is already open
	 * because the title of the JFrame is unique this method uses the 
	 * title of the frame to check if it is already open 
	 * when a user queries a stock symbol.
	 * @param window
	 * @return
	 */
	public static boolean windowCheck(String window)
	{
		/*
		 * If there are no windows open then the ArrayList would be empty
		 * which will cause the null pointer exception to be thrown which 
		 * needs to be caught.
		 */
		try
		{
			for(int i=0;i<windows.size();i++)
			{
				if((windows.get(i).getTitle()).equals(window))
				{
					//when a window is found get the window to show up
					windows.get(i).setVisible(false);
					windows.get(i).setVisible(true);
					return true;//return found so we know that the window is already open
				}
			}
			return false;//if not found return false
		}
		catch(NullPointerException e)
		{
			return false;//if there is a NullPointerException return false as there are no open windows
		}
	}
	/**
	 * The addWindow method adds a specified window to
	 * the ArrayList of windows.
	 * @param window
	 */
	public static void addWindow(JFrame window)
	{
		windows.add(window);
	}
	/**
	 * The removeWindow removes the specified window
	 * from the arraylist windows. This method can be
	 * used when a window is closed.
	 * @param window
	 */
	public static void removeWindow(JFrame window)
	{
		windows.remove(window);
	}
	public static void removeWindow(String windowName)
	{
		try
		{
			for(int i=0;i<windows.size();i++)
			{
				if((windows.get(i).getTitle()).equals(windowName))
				{
					windows.remove(i);
				}
			}
		}
		catch(NullPointerException e)
		{
			
		}
	}
	public static void showWindow(String windowName)
	{
		try
		{
			for(int i=0;i<windows.size();i++)
			{
				if((windows.get(i).getTitle()).equals(windowName))
				{
					//when a window is found get the window to show up
					windows.get(i).setVisible(false);
					windows.get(i).setVisible(true);
				}
			}
		}
		catch(NullPointerException e)
		{
			
		}
	}
	public static JFrame getWindow(String windowName)
	{
		try
		{
			for(int i=0;i<windows.size();i++)
			{
				if((windows.get(i).getTitle()).equals(windowName))
				{
					return windows.get(i);
				}
			}
		}
		catch(NullPointerException e)
		{
			
		}
		return null;
	}
}//end OpenWindowsChecker
