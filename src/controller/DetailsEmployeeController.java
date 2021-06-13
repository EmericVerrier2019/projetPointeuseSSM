package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Main;
import view.DetailsEmployeeView;
import view.MainFrame;


/*
 * Controleur permettant d'affficher les détails d'un employé lors d'un double-clique sur la table des employés
 */
public class DetailsEmployeeController implements MouseListener {

	/*
	 * Entreprise géré par l'application
	 */
	private Company company;
	
	private MainFrame mainFrame;
	
	public DetailsEmployeeController(Company company, MainFrame mainFrame) {
		this.company = company;
		this.mainFrame = mainFrame;
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
	        	DetailsEmployeeView detailsEmployeeView = new DetailsEmployeeView(company.getListEmployees().get(row), company, mainFrame);
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
