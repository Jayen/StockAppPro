
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class extends the JList 
 * uses the default list model to display 
 * a list of windows which are open.
 * @author Jayen kumar Jaentilal 
 */

public class WindowList extends JList
{
	private static DefaultListModel model;
	private static JList list;
		
	public WindowList()
	{
		super();
		model = new DefaultListModel();
		list=this;
		list.setModel(model);
		list.setToolTipText("Double Click to show window");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event)
			{
				try
				{
					JList list = (JList)event.getSource();
					if(event.getClickCount()==2)//if there is a double click
					{
						OpenWindowsChecker.showWindow((String)model.getElementAt(list.getSelectedIndex()));
					}
				}
				//There may be no windows open which would cause an indexOutOfBoundsException
				catch(IndexOutOfBoundsException e)
				{
					
				}
			}
		});
	}
	/**
	 * Add a specified windowName to the listModel
	 * @param windowName
	 */
	public static void addWindow(String windowName)
	{
		model.addElement(windowName);
	}
	/**
	 * Remove the windowName from the listModel
	 * @param windowName
	 */
	public static void removeWindow(String windowName)
	{
		model.removeElement(windowName);
	}
	/**
	 * Get the title of the window that is currently selected.
	 * @return String-windowTitle
	 */
	public static String getSelectedWindow()
	{
		return (String)model.getElementAt(list.getSelectedIndex());
	}
}
