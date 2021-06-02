package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controller.addEmployeeController;
import projetPointeuseSSM.Employee;

public class ManagementPanel extends JPanel{
	
	private JTable tb;
	
	
	public ManagementPanel() {
		super();
		setName("Accueil");
		IHMSetUp();
	}
	
		
	private void IHMSetUp() {
		ManagementTableModel tableModel = new ManagementTableModel();
		
		Employee test = new Employee();
		test.setFirstNameEmployee("Prenom"); 
		test.setLastNameEmployee("nom");
		test.setIdDepartment(1);
		test.setIdEmployee(1);
		tableModel.addEmployee(test);
		String[] header = {"id","prenom","nom",""};
		tableModel.setHeader(header);
		tb = new JTable(tableModel);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
		JButton addEmployeeButton = new JButton("ajouter");
		addEmployeeButton.addActionListener(new addEmployeeController());
		this.add(addEmployeeButton,BorderLayout.SOUTH);
		
	}
	
	
}
