
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *The OpenWindowsChecker class monitors the 
 *open DataTableWindows and uses and arraylist to
 *store all the windows which are currently open.
 * @author Jayen kumar Jaentilal 
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
	/**
	 * This method removes can also be used to remove the
	 * window from the arraylist windows.
	 * This method can find the window to remove via title of the window
	 * @param windowName
	 */
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
		//nullPointerException is thrown if there are no windows
		catch(NullPointerException e)
		{
			//nothing to remove so do nothing
		}
	}
	/**
	 * Method to show the window.
	 * This method brings the window above
	 * all the other windows.
	 * @param windowName
	 */
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
		//nullPointerException is thrown if there are no windows
		catch(NullPointerException e)
		{
			//nothing to do as there are no windows to bring forward
		}
	}
	/**
	 * This method returns the window which
	 * has the the title windowName.
	 * @param windowName
	 * @return JFrame-window
	 */
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
		//nullPointerException is thrown if there are no windows
		catch(NullPointerException e)
		{
			//nothing to do as there are no windows to get
		}
		return null;
	}
}//end OpenWindowsChecker
