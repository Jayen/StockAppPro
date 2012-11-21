
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


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
					if(event.getClickCount()==2)
					{
						OpenWindowsChecker.showWindow((String)model.getElementAt(list.getSelectedIndex()));
					}
				}
				catch(IndexOutOfBoundsException e)
				{
					
				}
			}
		});
	}
	public static void addWindow(String windowName)
	{
		model.addElement(windowName);
	}
	public static void removeWindow(String windowName)
	{
		model.removeElement(windowName);
	}
	public static String getSelectedWindow()
	{
		return (String)model.getElementAt(list.getSelectedIndex());
	}
}
