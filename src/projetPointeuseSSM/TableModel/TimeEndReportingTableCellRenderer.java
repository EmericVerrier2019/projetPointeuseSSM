package projetPointeuseSSM.TableModel;

import java.awt.Color;
import java.awt.Component;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import projetPointeuseSSM.Day;

public class TimeEndReportingTableCellRenderer extends DefaultTableCellRenderer{

	/**
	 * Numéro de version imposé par DefaultTableCellRenderer
	 */
	private static final long serialVersionUID = 1L;
	
	public TimeEndReportingTableCellRenderer() {
		super();
	}

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (column == 3) {
		    LocalDateTime timeEnd = (LocalDateTime) value; 
		    if (timeEnd == null) {
		    	setBackground(Color.orange);
		    }
		    else {
		    	setText(timeEnd.toLocalTime().format(DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY : h'h'mm",Locale.FRANCE)));
		    	setForeground(Color.green);
		    }        
        }
        if (column == 4) {
        	Duration overTimeHour = (Duration) value;
        	if(overTimeHour == null) {
        		setBackground(Color.gray);
        	}
        	else if(overTimeHour.toHours() < 0) {
        		setText(Long.toString(overTimeHour.toHours()));
        		setForeground(Color.red);
        	}
        	else if( overTimeHour.toHours() == 0) {
        		setText(Long.toString(overTimeHour.toHours()));
        		setForeground(Color.blue);
        	}
        	else {
        		setText(Long.toString(overTimeHour.toHours()));
        		setForeground(Color.green);
        	}
        }
        
        return this;
    }
}

