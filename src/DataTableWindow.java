
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

/**
 * This class creates the JFrame which holds the JTable
 * for the stock data for the companies
 * @author Jayenkumar Jaentilal
 */

public class DataTableWindow extends JFrame
{
	private String[][]data;
	private boolean chronological;
	/**
	 * Constructor for DataTableWindow this uses the super constructor
	 * to create the jframe and sets up the name for the JFrame.
	 * @param stockSymbol
	 * @param dateRange
	 * @param dataInterval
	 * @param chronological
	 * @param data
	 */
	public DataTableWindow(String stockSymbol,String dateRange,String dataInterval,boolean chronological,String[][] data)
	{
		super();
		if(chronological==true)
		{
			this.setTitle(stockSymbol+": "+dateRange+" "+dataInterval+" Chronological Order");
		}
		else
		{
			this.setTitle(stockSymbol+": "+dateRange+" "+dataInterval+" Reverse Chronological Order");
		}
		OpenWindowsChecker.addWindow(this);
		WindowList.addWindow(this.getTitle());
		this.data=data;
		this.chronological=chronological;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(560,375));
		this.setupWindow();
		this.setVisible(true);
		this.pack();
	}
	/**
	 * setupWindow method sets up the Data window for this query
	 * Creates the JTable which uses the DataTable TableModel
	 */	
	private void setupWindow() 
	{
		//remove the column names given by yahoo
		for(int row=0;row<data.length;row++)
		{
			for(int col=0;col<7;col++)
			{	
				if(!(row==data.length-1))
				{
					data[row][col]=data[row+1][col];
				}
			}
		}
		
		DataTable dataTable= new DataTable(data,chronological);
		JTable jtData= new JTable(dataTable);//assign the dataTable as the TableModel for jtData
		jtData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   
		jtData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtData.setCellSelectionEnabled(true);
		jtData.getTableHeader().setReorderingAllowed(false);
		
		TableColumn adjColumn=jtData.getColumnModel().getColumn(6);
		adjColumn.setCellRenderer(new ColorRenderer(chronological));//set the  ColorRenderer for adjColumn as the CellRenderer
		JScrollPane scroll= new JScrollPane(jtData);
		//add a windowListener to monitor the windowClosing event
		this.addWindowListener(new WindowAdapter()
			{
			 //when the window is closed remove the window name from the OpenwindowsChecker
			 public void windowClosing(WindowEvent event) 
			 {
				 WindowList.removeWindow(DataTableWindow.this.getTitle());
				 OpenWindowsChecker.removeWindow(DataTableWindow.this);
			 }
			});
		JLabel statusBar= new JLabel("StatusBar");
		this.statusBarData(statusBar);
		this.add(scroll,BorderLayout.CENTER);
		this.add(statusBar,BorderLayout.SOUTH);
	}
	/**
	 * The statusBar method calculates the average and the 
	 * maximal drawdown and adds it to the JLabel statusbar
	 * @param statusBar
	 */
	private void statusBarData(JLabel statusBar)
	{
		double adjCloseTotal=0;
		double count=data.length-1;
		double Average=0;
		double drawdown=0;
		//set peak as the first adj close price.
		double peak=-9999999;//set the peak to a very low number as a start
		double maximalDrawdown=0;
		
		//row 0 contains the column titles so it needs to be skipped.
		for (int i=0;i<data.length-1;i++)
		{
			adjCloseTotal=(Double)adjCloseTotal+(Double.parseDouble((data[i][6])));
		}
		//as the data is in reverse chronological the data array needs to be gone through in reverse
		for(int i=data.length-1;i>=0;i--)
		{
			if((Double.parseDouble((data[i][6])))>peak)
			{
				peak= Double.parseDouble((data[i][6]));
			}
			else 
			{
				drawdown= peak-(Double.parseDouble((data[i][6])));
			}
			if(drawdown>maximalDrawdown)
			{
				maximalDrawdown=drawdown;
			}
		}
		Average=adjCloseTotal/count;
		DecimalFormat averageFormat = new DecimalFormat("#.###");
		statusBar.setText("Average: "+averageFormat.format(Average)+"  Maximal Drawdown: "+averageFormat.format(maximalDrawdown));
	}
}//end DataTableWindow
