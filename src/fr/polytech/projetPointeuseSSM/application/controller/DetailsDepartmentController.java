package fr.polytech.projetPointeuseSSM.application.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.view.DetailsDepartmentView;
import fr.polytech.projetPointeuseSSM.application.view.DetailsEmployeeView;


/*
 * Controleur permettant d'affficher les détails d'un employé lors d'un double-clique sur la table des employés
 */
public class DetailsDepartmentController implements MouseListener {

	/*
	 * Entreprise géré par l'application
	 */
	private Company company;
	
	public DetailsDepartmentController(Company company) {
		this.company = company;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		if(e.getClickCount() == 2) { // verification qu'il s'agit d'un double-clique
			
			JTable table = (JTable) e.getSource();
	        int row = table.rowAtPoint(e.getPoint());
	        
	        if (table.getSelectedRow() != -1) { // On vérifie si une ligne est bien sélectionnée
	        	
	        	// On ouvre la fenetre correspondant au détail de l'employé
	        	DetailsDepartmentView detailsDepartmentView = new DetailsDepartmentView(company.getListDepartment().get(row), company);
	        	detailsDepartmentView.setVisible(true);
	        }
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
