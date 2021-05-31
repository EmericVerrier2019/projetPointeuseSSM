package view;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;





public class MainFrame extends JFrame{
	
	private ManagementPanel gestionManagementPanel;
	private TicketPanel ticketPanel; 
	private JTabbedPane menuTab;
	
	public MainFrame() {
		super();
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		setTitle("Gestion des employes"); //give name at application
		setSize(600,600); //give the size of Frame
		setLocationRelativeTo(null); //put at the center of the screen
		setResizable(true); //we allow the Resizable of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the default closing of frame when we click on the red cross
		menuTab = new JTabbedPane();
		gestionManagementPanel = new ManagementPanel();
		ticketPanel = new TicketPanel();
		menuTab.add(gestionManagementPanel);
		menuTab.add(ticketPanel);
		
		setContentPane(menuTab);
	}
}
