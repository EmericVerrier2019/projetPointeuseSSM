package view;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import projetPointeuseSSM.Company;





public class MainFrame extends JFrame{
	
	private ManagementPanel gestionManagementPanel;
	private TicketPanel ticketPanel; 
	private JTabbedPane menuTab;
	private Company company;
	
	public MainFrame(Company company) {
		super();
		this.company = company;
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		this.setTitle("Gestion des employes"); //give name at application
		this.setSize(600,600); //give the size of Frame
		this.setLocationRelativeTo(null); //put at the center of the screen
		this.setResizable(true); //we allow the Resizable of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the default closing of frame when we click on the red cross
		menuTab = new JTabbedPane();
		gestionManagementPanel = new ManagementPanel(this.company);
		ticketPanel = new TicketPanel();
		DetailsEmployeeView test = new DetailsEmployeeView(null, company);
		menuTab.add(gestionManagementPanel);
		menuTab.add(ticketPanel);
		test.setVisible(true);
		
		this.setContentPane(menuTab);
	}
}
