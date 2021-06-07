package projetPointeuseSSM.TableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IsWorkingEmployeeCellRenderer extends DefaultTableCellRenderer{

	/**
	 * Numéro de version imposé par DefaultTableCellRenderer
	 */
	private static final long serialVersionUID = 1L;
	
	public IsWorkingEmployeeCellRenderer() {
		super();
	}

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        boolean isWorking = (boolean) value;
        if (isWorking) {
        	setText("travail");
        	setForeground(Color.green);
        }
        else {
        	setText("absent");
        	setForeground(Color.red);
        }        
        return this;
    }
}

