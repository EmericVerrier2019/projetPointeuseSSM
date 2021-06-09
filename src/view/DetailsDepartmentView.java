package view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import projetPointeuseSSM.Company;
import projetPointeuseSSM.Department;
import projetPointeuseSSM.Main;


public class DetailsDepartmentView extends JDialog {

	/**
	 * Numero de version imposé par JDialog
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Employe qui sera géré par la fenêtre. Attribut a null si l'on souhaite ajouter un department.
	 */
	private Department department;
	
	/*
	 * Entreprise gérée par l'applicaiton
	 */
	private Company company;
	
		
	/**
	 * Crée la vue
	 * @param department Employé géré par le fenêtre (nul si l'on souhaite ajouter un employé)
	 * @param company Entreprise gérée par l'application
	 */
	public DetailsDepartmentView(Department department, Company company) {
		
		this.department = department;
		this.company = company;
		
		getContentPane().add(new JScrollPane(createContent()));
		
		setTitle("Ajout d'un département");
		
		setPreferredSize(new Dimension(400, 480));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
	}
	
	/**
	 * Crée le panneau de la vue
	 * @return le panneau principale
	 */
	private JPanel createContent() {
		//panel principale qui va contenir tous le reste
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(30,30,30,30));
		
		//panel qui va contenir tous les champs nécessaire pour département
		JPanel fieldsContainer = new JPanel();
		fieldsContainer.setLayout(new BorderLayout());
		
		//panel qui va contenir tous les champs de saisi pour l'ajout ou la modification du département
		JPanel fieldsEntryPanel = new JPanel();
		fieldsEntryPanel.setLayout(new GridLayout(3,2));
		
		//champs de saisi du nom du departement
		JTextField nameDepartment;

		
		//champ de saisi de l'identifiant du département
		JTextField idField ;
			
		//si on modifie le département
		if (departmentIsModifiying()) {
			idField = new JTextField(Integer.toString(department.getDepartmentNumber()));
			idField.setEnabled(false); //on interdit la modification de l'identifiant
			nameDepartment = new JTextField(department.getDepartmentName());
		} 
		//sinon on est entrain de créer un le département
		else {
			idField = new JTextField(Integer.toString(company.getListDepartment().size() + 1));
			nameDepartment = new JTextField();
		}
		fieldsEntryPanel.add(createEntryFieldPanel("Nom du département", nameDepartment));
		fieldsEntryPanel.add(createEntryFieldPanel("identifiant du département", idField));
		
		
		//on créer un panel pour les boutons
		JPanel allButtonsPannel = new JPanel();
		
		//bouton de validation
		JButton validationButton = new JButton("Valider");
		
		//On modifie l'action en fonction de si on ajout ou modifie un employé
		if(departmentIsModifiying()) {
			validationButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						if (nameDepartment.getText().isEmpty()) {
							String message = "Vous devez renseigner le nom du département";
							JOptionPane.showMessageDialog(mainContainer,message,"Message",JOptionPane.INFORMATION_MESSAGE);
						}else {
							department.setDepartmentName(nameDepartment.getText());
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(mainContainer,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
		}else {
			validationButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try{
						if (nameDepartment.getText().isEmpty()) {
							String message = "Vous devez renseigner le nom du département";
							JOptionPane.showMessageDialog(mainContainer,message,"Message",JOptionPane.INFORMATION_MESSAGE);
						}else {
							department = new Department();
							department.setDepartmentName(nameDepartment.getText());
							department.setDepartmentNumber(Integer.parseInt(idField.getText()));
							company.addDepartment(department);
							Main.mainFrame.updateDepartmentTable();
							dispose();
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(mainContainer,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		allButtonsPannel.add(validationButton);
		
		//Bouton pour annuler ou supprimer un employé
		if(departmentIsModifiying()) {
			
			JButton deleteButton = new JButton("Supprimer");
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
	
						company.removeDepartment(department);
						Main.mainFrame.updateDepartmentTable();
						dispose();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(mainContainer,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			allButtonsPannel.add(deleteButton);
			
		}
		else {
			//Button pour annuler l'ajout de l'employée
			JButton cancelCreationButton = new JButton("Annuler");
			cancelCreationButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			allButtonsPannel.add(cancelCreationButton);
		}
		fieldsContainer.add(fieldsEntryPanel,BorderLayout.CENTER);
		mainContainer.add(fieldsContainer,BorderLayout.CENTER);
		mainContainer.add(allButtonsPannel,BorderLayout.SOUTH);
		
			
		return mainContainer;
	}
	
		

	/**
	 * Permet de savoir si l'on modifie l'department
	 * @return vrai si on modifie l'employé, faux si on ajoute un employé
	 */
	private boolean departmentIsModifiying() {
		return this.department != null;
	}	
	
	/**
	 * Permet de faciliter l'ajout d'un champ de saisie pour l'édition de l'employé
	 * @param fieldName Nom du champ d'édition
	 * @return Un panneau permettant d'ajouter un champ de saisie
	 */
	private JPanel createEntryFieldPanel(String fieldName) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		JLabel label = new JLabel(fieldName);
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);
		return panel;
	}

	/**
	 * Permet de faciliter l'ajout d'un champ de saisie pour l'édition de l'employé
	 * @param fieldName Nom du champ d'édition
	 * @param value Composant à ajouter afin de pouvoir éditer l'employé
	 * @return Panneau contenant un label ainsi que le composant passé en paramètre
	 */
	private JPanel createEntryFieldPanel(String fieldName, Component value) {
		JPanel panel = createEntryFieldPanel(fieldName);
		if(value != null)
			panel.add(value);
		return panel;
	}
}
