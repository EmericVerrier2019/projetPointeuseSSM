package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import projetPointeuseSSM.Employee;
import view.JPanel.*;

public class MainFrame extends JFrame{
	private JTable tb;
	private ManagementPanel gestionManagementPanel;
	
	public MainFrame() {
		super();
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		setTitle("Gestion des employes"); //give name at application
		setSize(500,500); //give the size of Frame
		setLocationRelativeTo(null); //put at the center of the screen
		setResizable(true); //we allow the Resizable of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the default closing of frame when we click on the red cross
		
		gestionManagementPanel = new ManagementPanel();
		setContentPane(gestionManagementPanel);
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
		getContentPane().add(new JScrollPane(tb),BorderLayout.CENTER);
	}
}
