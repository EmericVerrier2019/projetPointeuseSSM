package fr.polytech.projetPointeuseSSM.application.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.polytech.projetPointeuseSSM.application.Main;
import fr.polytech.projetPointeuseSSM.application.controller.DetailsEmployeeController;
import fr.polytech.projetPointeuseSSM.application.controller.addEmployeeController;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.Employee;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.IsWorkingEmployeeCellRenderer;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.ManagementTableModel;


public class ManagementPanel extends JPanel{
	
	/**
	 * Numero de version imposé par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private ManagementTableModel tableModel;
	
	private Company company;
	
	private MainFrame mainFrame;
	
	public ManagementPanel(Company company, MainFrame mainFrame) {
		super();
		setName("Accueil");
		this.company = company;
		this.tableModel = new ManagementTableModel(company);
		this.mainFrame = mainFrame;
		IHMSetUp();
	}
	
		
	private void IHMSetUp() {
			
		JTable tb = new JTable(tableModel);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setModel(tableModel);
		tb.getColumnModel().getColumn(3).setCellRenderer(new IsWorkingEmployeeCellRenderer()); //on définie un rendu pour la dernière colonne
		tb.addMouseListener(new DetailsEmployeeController(company,mainFrame));
		
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
		JButton addEmployeeButton = new JButton("ajouter");
		addEmployeeButton.addActionListener(
			//création du formulaire d'inscription
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Pour ajouter un employé on utilise la vue des details avec null pour le paramètre de l'employé
					DetailsEmployeeView addEmployeeView = new DetailsEmployeeView(null, company,mainFrame);
					addEmployeeView.setVisible(true);	
				}
			}
		);
		this.add(addEmployeeButton,BorderLayout.SOUTH);
		
	}
	
	
	public void updateEmployeeTableModel(ArrayList<Employee> listEmployee) {
		tableModel.setEmployees(listEmployee);
		tableModel.fireTableDataChanged();
	}
	
}
