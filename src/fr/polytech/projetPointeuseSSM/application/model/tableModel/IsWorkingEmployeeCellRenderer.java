package fr.polytech.projetPointeuseSSM.application.model.tableModel;

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
        
        int isWorking = (int) value;
        if (isWorking > 0) {
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

