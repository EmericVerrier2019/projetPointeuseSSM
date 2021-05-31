package view.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Employee;

public class ManagementPanel extends JPanel{
	
	
	
	public ManagementPanel() {
		super();
		IHMSetUp();
	}
	
		
	private void IHMSetUp() {
	
		this.add(new JButton("ajouter"));
		
	}
	
	
}
