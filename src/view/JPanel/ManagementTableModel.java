package view.JPanel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Employee;

public class ManagementTableModel extends AbstractTableModel{
	private ArrayList<Employee> employees;
	private String[] header;
	
	//header : {id , nom, prenom, idDepartement, nomDepartement, loupe, suppr}
	
	public int getRowCount() {
		return employees.size();
	}
	
	public int getColumnCount() {
		return header.length;
	}
	
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}
	//header : {id , nom, prenom,  suppr}
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return employees.get(rowIndex).getIdEmployee();
		case 1: 
			return employees.get(rowIndex).getLastName();
		case 2:
			return employees.get(rowIndex).getFirstName();
		default:
			return null;		
		}
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
		fireTableRowsInserted(employees.size() -1 ,employees.size() -1);
	}
	
	public void removeEmployee(int rowIndex) {
		employees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
		
	}


}
