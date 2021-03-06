package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import projetPointeuseSSM.Company;
import view.DetailsEmployeeView;


/*
 * Controleur permettant d'affficher les d?tails d'un employ? lors d'un double-clique sur la table des employ?s
 */
public class DetailsEmployeeController implements MouseListener {

	/*
	 * Entreprise g?r? par l'application
	 */
	private Company company;
	
	public DetailsEmployeeController(Company company) {
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
	        
	        if (table.getSelectedRow() != -1) { // On v?rifie si une ligne est bien s?lectionn?e
	        	
	        	// On ouvre la fenetre correspondant au d?tail de l'employ?
	        	DetailsEmployeeView detailsEmployeeView = new DetailsEmployeeView(company.getListEmployees().get(row), company);
	        	detailsEmployeeView.setVisible(true);
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
