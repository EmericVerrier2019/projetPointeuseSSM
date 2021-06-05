package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controller.addEmployeeController;
import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;

public class ManagementPanel extends JPanel{
	
	/**
	 * Numero de version imposé par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private JTable tb;
	
	private Company company;
	
	public ManagementPanel(Company company) {
		super();
		setName("Accueil");
		this.company = company;
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
		addEmployeeButton.addActionListener(
			//création du formulaire d'inscription
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog formDialogue  = new JDialog();
					formDialogue.setTitle("Fenêtre qui affiche du texte"); //On donne un titre à l'application
					formDialogue.setSize(320,240); //On donne une taille à notre fenêtre
					formDialogue.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
					formDialogue.setResizable(true); //On permet le redimensionnement
					formDialogue.add(new DetailsEmployeeView(null,company));
					formDialogue.setVisible(true);

					
				}
			}
		);
		this.add(addEmployeeButton,BorderLayout.SOUTH);
		
	}
	
	
}
