package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Main;
import view.DetailsEmployeeView;

public class deleteEmployeeController implements ActionListener {

	/*
	 * view qui est rattaché au controleur
	 */
	private DetailsEmployeeView view;
	
	/**
	 * L'entreprise géré par l'application
	 */
	private Company company;
	
	/**
	 * Employé à supprimer
	 */
	private Employee employee;

	
	/**
	 * Contructeur qui initialise le controleur
	 * @param view Vue rattaché au controleur
	 * @param company Entreprise géré par l'application
	 * @param employee L'employé à supprimer
	 */
	public deleteEmployeeController(DetailsEmployeeView view, Company company, Employee employee) {
		super();
		this.view = view;
		this.company = company;
		this.employee = employee;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String message = "Voulez-vous supprimer l'employé "+ employee.getFirstName() + " "+ employee.getLastName() + "?";
		if (JOptionPane.showConfirmDialog(view,message, "Demande de confirmation",JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			company.removeEmployee(employee);
			Main.mainFrame.updateEmployeeTable();
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
		};
	}
}
