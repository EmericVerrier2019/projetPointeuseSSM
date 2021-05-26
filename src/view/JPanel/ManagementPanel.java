package view.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Employee;

public class ManagementPanel extends JPanel{
	JTable tb;
	
	
	public ManagementPanel() {
		super();
		IHMSetUp();
	}
	
	public ManagementPanel(Employee[] listEmployees, String[] header) {
		super();
		IHMSetUp();
		
		
	}
	
	private void IHMSetUp() {
	
		this.setLayout(new BorderLayout());		
		JLabel label = new JLabel("Voici la liste des employées");
		this.add(label, "North");
		
	}
	
	
}
