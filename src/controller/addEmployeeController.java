package controller;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import projetPointeuseSSM.Company;



public class addEmployeeController implements ActionListener {

	
	/**
	 * L'entreprise 
	 */
	private Company company;
	
	public addEmployeeController(Company company) {
		super();
		this.company = company;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}
