/**
 * The DataTable class extends the default AbstractTableModel
 * and also implements the TableModel
 * This class sets up the actual JTable with the yahoo data
 * @author Jayenkumar Jaentilal
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class DataTable extends AbstractTableModel implements TableModel
{
	private String[] columnNames={"Date","Open","High","Low","Close","Volume","Adj Close"};
	private String[][] data;
	private String[][] companyData;
	
	/**
	 * Constructor for DataTable this method assigns the data to the 
	 * private data and also uses the chronologicalOrder method to 
	 * order the data.
	 * @param data
	 * @param chronological
	 */
	public DataTable(String[][]data,boolean chronological)
	{
		this.data=data;
		this.chronologicalOrder(chronological);
	}
	/**
	 * The chornologicalOrder sets the order of the data
	 * based on the order wanted by the user. 
	 * @param chronological
	 */
	
	public void chronologicalOrder(boolean chronological)
	{
		if(chronological==true)
		{
			companyData=new String[data.length][columnNames.length];
			for(int row=0;row<data.length-1;row++)
			{
				companyData[row]=data[((data.length-2)-row)];
			}
		}
		else
		{
				companyData=data;
		}
	}
	
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount() 
	{
		/*rowCount is length-1 as the last 2 rows in the company data array are the same
		 *due to the removal of the row names from the top of the array
		 */
		return companyData.length-1;
	}
	
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int row, int col) 
	{
		return companyData[row][col];
	}
}//end DataTable
