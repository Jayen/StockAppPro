

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * The ColorRenderer class extends the default renderer and implements
 * the TabelCellRenderer.
 * This Class sets up the colour for the adj Close column .
 */

public class ColorRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	private boolean chronological;
	public ColorRenderer(boolean chronological)
	{
		this.chronological=chronological;
	}
	
	/**
	 * getTableCellRenderer method sets the colour 
	 * based on the price change
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
					boolean isSelected, boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		//The chornology of the data will affect the way the data is managed to colour so the order check is made first
		if(chronological==true)
		{
			if (row==0)//if first column then assign black colour as there is no previous data
			{	
				cell.setForeground(Color.BLACK);
			}
			else
			{
				//if adj close for the column is more than the previous then colour the data green
				if ((Double.parseDouble((String) table.getValueAt(row,6)))>(Double.parseDouble((String)table.getValueAt(row-1,6))))
				{
					cell.setForeground(Color.GREEN );
				}
				//if the adj close fot the column is less than the previous then colour the data red
				else if((Double.parseDouble((String) table.getValueAt(row,6)))<(Double.parseDouble((String)table.getValueAt(row-1,6))))
				{	
					cell.setForeground(Color.RED);
				}
				else//if no change colour it black
				{	
					cell.setForeground(Color.BLACK);
				}
			}	
		}
		//reverse chronological
		else
		{
			//if the adj close for the column is less than the column after it then colour red
			if ((Double.parseDouble((String) table.getValueAt(row,6)))<(Double.parseDouble((String)table.getValueAt(row+1,6))))
			{
				cell.setForeground(Color.RED);
			}
			//if the adj close foe the column is more than the column after ir then colour red
			else if((Double.parseDouble((String) table.getValueAt(row,6)))>(Double.parseDouble((String)table.getValueAt(row+1,6))))
			{	
				cell.setForeground(Color.GREEN);
			}
			else//if no change colour ir black
			{	
				cell.setForeground(Color.BLACK);
			}
		}
		return cell;	
	}
}//end ColorRenderer
