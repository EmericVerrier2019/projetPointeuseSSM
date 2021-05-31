package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.MainFrame;

public class AddEmployeeCongtroller implements ActionListener {

	
	/**
	 * Vue a la quelle est rattaché le controler
	 */
	private MainFrame view;
	
	/**
	 * La fenetre servant pour le formulaire d'inscription
	 */
	private JDialog formIncription;
	
	/**
	 * le prenom de l'employe
	 */
	private JTextField firstNameEmployee;
	
	/**
	 * le nom de l'employe
	 */
	private JTextField lastNameEmployee;
	
	/**
	 * liste des noms des departements
	 */
	private Object[] nounDepartment;
	
	/**
	 * liste deroulante pour les departements
	 */
	private JComboBox listDepartment;
	
	
	/**
	 * 
	 * @param view
	 * @param formIncription
	 * @param firstNameEmployee
	 * @param lastNameEmployee
	 * @param nounDepartment
	 * @param listDepartment
	 */
	public AddEmployeeCongtroller(MainFrame view, JDialog formIncription, JTextField firstNameEmployee,
			JTextField lastNameEmployee, Object[] nounDepartment, JComboBox listDepartment) {
		super();
		this.view = view;
		this.formIncription = formIncription;
		this.firstNameEmployee = firstNameEmployee;
		this.lastNameEmployee = lastNameEmployee;
		this.nounDepartment = nounDepartment;
		this.listDepartment = listDepartment;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
