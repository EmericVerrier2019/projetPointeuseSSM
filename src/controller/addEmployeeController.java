package controller;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import view.AddEmployeeForm;


public class addEmployeeController implements ActionListener {

	
	/**
	 * Vue a la quelle est rattach� le controler
	 */
	//private MainFrame view;
	
	public addEmployeeController() {
		super();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JDialog formDialogue  = new JDialog();
		formDialogue.setTitle("Fen�tre qui affiche du texte"); //On donne un titre � l'application
		formDialogue.setSize(320,240); //On donne une taille � notre fen�tre
		formDialogue.setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		formDialogue.setResizable(true); //On permet le redimensionnement
		formDialogue.add(new AddEmployeeForm());
		formDialogue.setVisible(true);
		
	}
	
}
