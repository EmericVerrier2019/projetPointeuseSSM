package fr.polytech.projetPointeuseSSM.application.view;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.Employee;

public class MainFrame extends JFrame{
	
	private ManagementPanel gestionManagementPanel;
	private ManagementDepartmentPanel gestionDepartmentPanel;
	private ReportingPanel historyPanel;
	private JTabbedPane menuTab;
	private Company company;
	
	public MainFrame(Company company) {
		super();
		this.company = company;
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		this.setTitle("Gestion des employes"); //give name at application
		this.setSize(1000,600); //give the size of Frame
		this.setLocationRelativeTo(null); //put at the center of the screen
		this.setResizable(true); //we allow the Resizable of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the default closing of frame when we click on the red cross
		menuTab = new JTabbedPane();
		gestionManagementPanel = new ManagementPanel(company,this);
		gestionDepartmentPanel = new ManagementDepartmentPanel(company);
		historyPanel = new ReportingPanel(company);
		menuTab.add(gestionManagementPanel);
		menuTab.add(historyPanel);		
		menuTab.add(gestionDepartmentPanel);
		this.setContentPane(menuTab);
	}

	public ManagementPanel getGestionManagementPanel() {
		return gestionManagementPanel;
	}

	public JTabbedPane getMenuTab() {
		return menuTab;
	}

	public Company getCompany() {
		return company;
	}
	
	public void updateEmployeeTable() {
		gestionManagementPanel.updateEmployeeTableModel(company.getListEmployees()); 
		//On mets a jour la table des historiques pour prendre en compte le cas o� on supprime un employ�
		historyPanel.updateReportingTable();;
	}
	
	public void updateDepartmentTable() {
		gestionDepartmentPanel.updateDepartmentTable();
	}
	
	public void updateReportingTable() {
		historyPanel.updateReportingTable();
	}
}
