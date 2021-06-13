package fr.polytech.projetPointeuseSSM.application.model.tableModel;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import fr.polytech.projetPointeuseSSM.timeClocking.Ticket;

public class TicketTableModel  extends AbstractTableModel{
	private ArrayList<Ticket> ticketsList;
	private String[] header;
	public TicketTableModel(ArrayList<Ticket> ticketList, String[] header) 
	{
		this.ticketsList = ticketList;
		this.header = header;
	}
	public TicketTableModel()
	{
		this.ticketsList = new ArrayList<Ticket>();
		this.header = new String[] {"Id Employé","Arrivées","Départ","Departement"};
	}
	@Override
	public int getRowCount() {
		return ticketsList.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) 
		{
		case 0:
			return ticketsList.get(rowIndex).getIdEmployee();
		case 1:
			return ticketsList.get(rowIndex).getTicketDateTime();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
