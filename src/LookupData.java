
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.*;

/**
 * This lookupData class implements the ActionListener
 * This class sets the data up required by yahoo to get stock info
 * and also queries yahoo using the URLReader class
 * @author Jayenkumar Jaentilal 
 */

public class LookupData implements ActionListener
{
	private JTextField stockSymbol; 
	private DateComboBox beginDay,  beginMonth,  beginYear; 
	private DateComboBox endDay, endMonth, endYear;
	private JComboBox jcbInterval;
	private JCheckBox order;
	private String symbol;
	//a,b,c,d,e,f,g stores the data needed by Yahoo to get stock data
	private int a,b,c;
	private int d,e,f;
	private String g;
	private boolean chronological;
	private String dateRange,dataInterval;
	private JFrame mainWindow;
	
	/**
	 * Constructor for LookupData
	 * @param arrayOfParams
	 * @param window
	 */
	public LookupData(JTextField stockSymbol, DateComboBox beginDay, DateComboBox beginMonth, DateComboBox beginYear, 
					  DateComboBox endDay, DateComboBox endMonth, DateComboBox endYear, JComboBox jcbInterval, JCheckBox order,JFrame window)
	{
		this.stockSymbol=stockSymbol;
		this.beginDay=beginDay;
		this.beginMonth=beginMonth;
		this.beginYear=beginYear;
		this.endDay=endDay;
		this.endMonth=endMonth;
		this.endYear=endYear;
		this.jcbInterval=jcbInterval;
		this.order=order;
		mainWindow=window;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		prepareData();
		//check if the stock symbol is more than 8 characters or if it doesn't satisfy the upper case digits and period only show error message
		if((symbol.length()>=8) || (!(symbol.matches("^[A-Z0-9\\.]+$"))))
		{	
			JOptionPane.showMessageDialog(mainWindow,symbol+" must be maximum 8 characters in length(uppercase characters, digits and period only)!");
		}
		else
		{
			if(checkDate(a, b, c, d, e, f)==true)//use the checkDate method to check if beginDate is before endDate
			{
				try
				{
					dateRange=b+"-"+(a+1)+"-"+c+" To "+e+"-"+(d+1)+"-"+f;
					String chronologicalText="";
					if(chronological==true)
					{
						chronologicalText=" Chronological Order";
					}
					else
					{
						chronologicalText=" Reverse Chronological Order";
					}
					
					//check if the window is already open, if yes bring it forward
					boolean check=OpenWindowsChecker.windowCheck(symbol+": "+dateRange+" "+dataInterval+chronologicalText);
					if(check==false)
					{
						//setup the url that the URLReader needs
						String url="http://ichart.yahoo.com/table.csv?s="+symbol+"&a="+a+"&b="+b+"&c="+c+"&d="+d+"&e="+e+"&f="+f+"&g="+g;
						//split the data and put it in a 2D array
						String[] data=(URLReader.readURL(url,mainWindow)).split("\n");
						String[][] data2D= new String[data.length][];
						for (int i=0;i<data.length;i++)
						{
							data2D[i] = data[i].split(",");
						}
						//create the data window using the DataTableWindow class
						DataTableWindow spreadsheet= new DataTableWindow(symbol,dateRange,dataInterval,chronological,data2D);
					}
				}
				catch(NullPointerException exception)
				{
					/*
					 * Catch exception so the program can continue to function
					 * NullPointerException is thrown when URLReader fails due to invalid stock symbol 
					 * or Internet problem to get the data meaning that the array is empty causing the null pointer error
					 * No need to do any handling here as the handling is done in the URLReader to show user
					 * appropriate message 
					 */
				}
			}
			else
			{
				JOptionPane.showMessageDialog(mainWindow,"End date specified has to be after the begin date.");
			}
		}
	}
	/**
	 * Method to prepare the information required for a testing and
	 * getting the data from yahoo.
	 */
	private void prepareData()
	{
		this.symbol=stockSymbol.getText();
		stockSymbol.setText("");
		a=beginMonth.getSelectedIndex();
		b=(Integer)beginDay.getSelectedItem();
		c=(Integer)beginYear.getSelectedItem();
		d=endMonth.getSelectedIndex();
		e=(Integer)endDay.getSelectedItem();
		f=(Integer)endYear.getSelectedItem();
		
		//set up the monthly key as used by yahoo
		if(((String)jcbInterval.getSelectedItem()).equals("Monthly"))
		{
			g="m";
			dataInterval="(Monthly)";
		}
		else if(((String)jcbInterval.getSelectedItem()).equals("Weekly"))
		{	
			g="w";
			dataInterval="(Weekly)";
		}
		else
		{
			g="d";
			dataInterval="(Daily)";
		}
		//if the JCheckBox is selected user wants chronological order
		if(order.isSelected())
		{
			chronological=true;
		}
		else
		{
			chronological=false;
		}	
	}
	/**
	 * The checkDate method checks if the begin date given by the user
	 * is after the end date given by the user and returns true or false
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return beginBeforeEnd
	 */
	/*
	 *a=Start month,b=Start day,c=Start year,
	 *d=End month,e=End day,f=End year
	 */
	public boolean checkDate(int a,int  b,int c,int d,int e,int f)
	{
		//uses the before method from the gregorianCalender to check if a date is before another
		boolean beginBeforeEnd=false;
		GregorianCalendar beginDate = new GregorianCalendar(c,a,b);
		GregorianCalendar endDate = new GregorianCalendar(f,d,e);
		
		if(beginDate.before(endDate))
		{
			beginBeforeEnd=true;
		}
		return beginBeforeEnd;	
	}
}//end LookupData
