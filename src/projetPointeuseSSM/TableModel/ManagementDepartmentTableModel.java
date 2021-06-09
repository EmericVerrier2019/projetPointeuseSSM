package projetPointeuseSSM.TableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Department;
import projetPointeuseSSM.Employee;

public class ManagementDepartmentTableModel extends AbstractTableModel{
	/**
	 * Numéro de version imposé par AbstractTableModel
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Department> departments;
	private static final String[] header = {"id","nom"} ;
	
	public ManagementDepartmentTableModel() {
		super();
	}
	
	public ManagementDepartmentTableModel(Company company) {
		super();
		this.departments= (ArrayList<Department>) company.getListDepartment();
	}
	
	public ArrayList<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
	}

	public int getRowCount() {
		return departments.size();
	}
	

	public int getColumnCount() {
		return header.length;
	}
	
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return departments.get(rowIndex).getDepartmentNumber();
		case 1: 
			return departments.get(rowIndex).getDepartmentName();
		default :
			return null;
		}
	}
	

	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
