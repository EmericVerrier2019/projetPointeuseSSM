package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddEmployeeForm extends JPanel {

	private JTextField firstNameEmployee;
	private JTextField lastNameEmployee;
	private Object[] idDepartment;
	
	
	public AddEmployeeForm() {
		super();
		setName("Formulaire d'ajout");
		IHMSetup();
	}
	
	public void IHMSetup() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraintsGrid = new GridBagConstraints();
		
		//Ajout de la premiere ligne du formulaire
		constraintsGrid.fill = GridBagConstraints.HORIZONTAL;
		constraintsGrid.gridx = 0;
		constraintsGrid.gridy = 0;
		JLabel askFirstNameEmployee = new JLabel("Prénom :");
		this.add(askFirstNameEmployee,constraintsGrid);
		
		constraintsGrid.fill = GridBagConstraints.HORIZONTAL;
		constraintsGrid.weightx = 0.5;
		constraintsGrid.gridx = 1;
		constraintsGrid.gridy = 0;
		firstNameEmployee = new JTextField();
		firstNameEmployee.setColumns(10);
		this.add(firstNameEmployee,constraintsGrid);
		
		//ajout de la deuxieme ligne
		constraintsGrid.fill = GridBagConstraints.HORIZONTAL;
		constraintsGrid.gridx = 0;
		constraintsGrid.gridy = 1;
		JLabel askLastNameEmployee = new JLabel("Nom :");
		this.add(askLastNameEmployee,constraintsGrid);
		constraintsGrid.fill = GridBagConstraints.HORIZONTAL;
		constraintsGrid.weightx = 0.5;
		constraintsGrid.gridx = 1;
		constraintsGrid.gridy = 1;
		lastNameEmployee = new JTextField();
		lastNameEmployee.setColumns(10);
		this.add(lastNameEmployee,constraintsGrid);
		
	}
}
