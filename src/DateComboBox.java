
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;

/**
 * This class creates JComboBoxes for the 
 * APPGUI this class extends the JComboBox
 * and uses a String type to create a specialised 
 * JComboBox depending on the type.
 * @author Jayenkumar Jaentilal
 */

public class DateComboBox extends JComboBox
{
	/**
	 * Constructor for the DateComboBox
	 * Uses the constructor from the JComboBox to
	 * create the JComboBox and then adds Items 
	 * specified by the type
	 * @param type
	 */
	public DateComboBox(String type)
	{ 
		super();
		if(type=="Day")
		{
			int Day=1;
			while(Day<=31)
			{
				this.addItem(Day);
				Day++;
			}
		}
		else if(type=="Month")
		{
			String[] months={"January","February","March","April","May","June","July"
							  ,"August","September","October","November","December"};
			for(int i=0;i<months.length;i++)
			{
				this.addItem(months[i]);
			}
		}
		else if(type=="Year")
		{
			int year=1970;
			GregorianCalendar date = new GregorianCalendar();
			while(year<=date.get(Calendar.YEAR))
			{
				this.addItem(year);
				year++;
			}
		}
		this.setPreferredSize(new Dimension(100,30));
	}
}//end DateComboBox
