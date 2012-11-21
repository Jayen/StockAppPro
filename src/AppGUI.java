
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

/**
 * This class creates the main window for the
 * StockApp Pro application
 * This class extends the JFrame
 * @author Jayenkumar Jaentilal 
 */

public class AppGUI extends JFrame 
{
	protected DefaultListModel model;
	/**
	 * AppGUI constructor uses the super constructor from JFrame 
	 * to create the JFrame.
	 */	
	public AppGUI()
	{
		super("Stock Market Pro");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setupWindow();
		this.setPreferredSize(new Dimension(705,263));
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}
	/**
	 * setupWindow method sets up the main window for the application
	 * Uses a mainPanel gridLayout with 5 rows, which holds the 5 panels 
	 * to get 5 rows of i.e stock entry,begin dates,end dates, interval and order, lookup
	 */	
	private void setupWindow() 
	{
		//mainPanel will hold the 5 panels
		JPanel mainPanel= new JPanel(new GridLayout(5,1,2,8));
		
		JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));
		JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER,8,0));
		JPanel panel3= new JPanel(new FlowLayout(FlowLayout.CENTER,8,0));
		JPanel panel4= new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));
		JPanel panel5= new JPanel(new FlowLayout());
		
		//panel1 components
		JLabel stock= new JLabel("  Stock Symbol:");
		JTextField stockSymbol= new JTextField(8);
		stockSymbol.setPreferredSize(new Dimension(150,29));
		panel1.add(stock);
		panel1.add(stockSymbol);
		
		//panel2 components
		JLabel begin= new JLabel("      Begin Date:",SwingConstants.RIGHT);
		final DateComboBox beginDay= new DateComboBox("Day");
		DateComboBox beginYear= new DateComboBox("Year");
		beginYear.setSelectedItem(2000);//set the begin year to 2000
		final DateComboBox beginMonth= new DateComboBox("Month");
		
		//actionListener used to make sure that the dates shown for the month is correct
		beginMonth.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				monthDates(beginDay,beginMonth);
			}
		});
	
		panel2.add(begin);
		panel2.add(beginDay);
		panel2.add(beginMonth);
		panel2.add(beginYear);
		
		//panel3 components
		JLabel end= new JLabel("          End Date:",SwingConstants.RIGHT);
		final DateComboBox endDay= new DateComboBox("Day");
		DateComboBox endYear= new DateComboBox("Year");
		final DateComboBox endMonth= new DateComboBox("Month");
		
		//actionListener used to make sure that the dates shown for the month is correct
		endMonth.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				monthDates(endDay,endMonth);
			}
		});
		//set the end date to the current day.
		GregorianCalendar date = new GregorianCalendar();
		endMonth.setSelectedIndex(date.get(Calendar.MONTH));
		endDay.setSelectedItem(date.get(Calendar.DAY_OF_MONTH));
		endYear.setSelectedItem(date.get(Calendar.YEAR));
		
		panel3.add(end);
		panel3.add(endDay);
		panel3.add(endMonth);
		panel3.add(endYear);
		
		//panel4 components
		JLabel interval= new JLabel("              Interval:",SwingConstants.RIGHT);
		JComboBox jcbInterval= new JComboBox();
		jcbInterval.setPreferredSize(new Dimension(100,30));
		jcbInterval.addItem("Monthly");
		jcbInterval.addItem("Weekly");
		jcbInterval.addItem("Daily");
		JCheckBox order= new JCheckBox("chronological order");
		panel4.add(interval);
		panel4.add(jcbInterval);
		panel4.add(order);
		
		//panel5 components
		final JButton lookup=new JButton("Lookup");
		lookup.setToolTipText("Lookup the data for the company");
		lookup.setPreferredSize(new Dimension(80,28));
		lookup.addActionListener(new LookupData(stockSymbol,beginDay,beginMonth,beginYear,
				endDay,endMonth,endYear,jcbInterval,order,this));
		
		stockSymbol.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				if((arg0.getKeyCode())==KeyEvent.VK_ENTER)
				{
					lookup.doClick();
				}
			}
		});
		
		panel5.add(lookup);
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		this.add(mainPanel,BorderLayout.CENTER);
		
		JPanel listPanel = new JPanel(new BorderLayout());
		JLabel listLabel= new JLabel("Windows Open:",SwingConstants.CENTER);
		JButton closeWindow= new JButton("Close window");
		closeWindow.setToolTipText("Close selected window");
		closeWindow.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					OpenWindowsChecker.getWindow(WindowList.getSelectedWindow()).dispose();
					OpenWindowsChecker.removeWindow(WindowList.getSelectedWindow());
					WindowList.removeWindow(WindowList.getSelectedWindow());
				}
				catch(Exception e)
				{
					
				}
			}
			
		});
		WindowList list= new WindowList();
		JScrollPane scroll= new JScrollPane(list);
		
		listPanel.add(listLabel,BorderLayout.NORTH);
		listPanel.add(scroll,BorderLayout.CENTER);
		listPanel.add(closeWindow,BorderLayout.SOUTH);
		this.add(listPanel,BorderLayout.EAST);
		
	}
	/**
	 * The monthDates method changes the dates shown in the beginDay and endDay
	 * when the month is changed.
	 * @param dayComboBox
	 * @param monthComboBox
	 */
	private void  monthDates(JComboBox dayComboBox,JComboBox monthComboBox)
	{
		dayComboBox.removeAllItems();
		int monthNumber=monthComboBox.getSelectedIndex()+1;
		switch(monthNumber)
		{
			//Months with 31 days
			case 1:case 3:case 5:case 7: case 8: case 10: case 12: 
				for(int i=1;i<=31;i++)
					{
					dayComboBox.addItem(i);
					}break;
			//28 Days February	
			case 2:  
				for(int i=1;i<=28;i++)
				{
					dayComboBox.addItem(i);
				}
				break;
			//Months with 30 days
			case 4:case 6:case 9:case 11:
				for(int i=1;i<=30;i++)
				{
					dayComboBox.addItem(i);
				}break;
		}
	}
}//end AppGUI
