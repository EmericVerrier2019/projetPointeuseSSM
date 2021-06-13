package view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.EventException;

import controller.addEmployeeController;
import controller.deleteEmployeeController;
import controller.modifyEmployeeController;
import projetPointeuseSSM.Company;
import projetPointeuseSSM.Day;
import projetPointeuseSSM.Department;
import projetPointeuseSSM.Employee;

public class DetailsEmployeeView extends JDialog {

	/**
	 * Numero de version impos� par JDialog
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Employe qui sera g�r� par la fen�tre. Attribut a null si l'on souhaite ajouter un employee.
	 */
	private Employee employee;
	
	/*
	 * Entreprise g�r�e par l'applicaiton
	 */
	private Company company;
	
	/*
	 * Liste qui va contenir tous les champs de saisie pour l'ajout ou modification d'un employ�
	 * les 20 premiers correspondent au JComboBox pour les horaires de l'employ�
	 */
	private ArrayList<Component> listComponentView = new ArrayList<Component>();
	
	/*
	 * frame principale de l'application
	 */
	private MainFrame mainFrame;
	/**
	 * Cr�e la vue
	 * @param employee Employ� g�r� par le fen�tre (nul si l'on souhaite ajouter un employ�)
	 * @param company Entreprise g�r�e par l'application
	 */
	public DetailsEmployeeView(Employee employee, Company company, MainFrame mainFrame) {
		
		this.employee = employee;
		this.company = company;
		this.mainFrame = mainFrame;
		getContentPane().add(new JScrollPane(createContent()));
		
		if(employeeIsModifiying()) {
			setTitle("D�tails de l'employ� - " + employee.getFirstName() + " " + employee.getLastName());
		}else {
			setTitle("Ajout d'un employ�");
		}
		
		setPreferredSize(new Dimension(700, 480));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
	}
	
	/**
	 * Cr�e le panneau de la vue
	 * @return le panneau principale
	 */
	private JPanel createContent() {
		//panel principale qui va contenir tous le reste
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setBorder(new EmptyBorder(30,30,30,30));
		
		//panel qui va contenir tous les champs n�cessaire pour le d�tail d'un employ�
		JPanel fieldsContainer = new JPanel();
		fieldsContainer.setLayout(new BorderLayout());
		
		//panel qui va contenir tous les champs de saisi pour l'ajout ou la modification d'un employe
		JPanel fieldsEntryPanel = new JPanel();
		fieldsEntryPanel.setLayout(new GridLayout(3,2));
		
		//champs de saisi des nom et pr�nom de l'employ�
		JTextField firstNameField;
		JTextField lastNameField;
		
		//champs de saisis des horaires de l'employ�
		
		//tableau de String contenant les heures
		String[] hours = new String[24];
		for(int nLoop = 0; nLoop < 24; nLoop++) {
			//on r�cup�re l'heure sous forme string en utilisant les format de LocalTime
			hours[nLoop] = Day.convertUnit(nLoop);
		}
		
		//tableau de String contenant les minutes
		String[] minutes = {"00", "15", "30", "45"};
		
		JPanel startSchedulePanel = createFieldSchedule(hours, minutes, "start");
		JPanel endSchedulePanel = createFieldSchedule(hours, minutes, "end");
		
		//champ de saisi du departement
		Department[] departments = company.getListDepartment().toArray(new Department[0]);
		String[] departmentNames = new String[departments.length];
		for (int i = 0; i < departments.length; i++) {
			departmentNames[i] = departments[i].getDepartmentName();
		}
		JComboBox<String> departmentsCombo = new JComboBox<String>(departmentNames);
		departmentsCombo.setName("departmentsCombo");
		
		//champ de saisi de l'identifiant de l'employ�
		JTextField idField;
		
		//si on modifie l'employe
		if (employeeIsModifiying()) {
			idField = new JTextField(Integer.toString(employee.getIdEmployee()));
			idField.setEnabled(false); //on interdit la modification de l'identifiant
			idField.setName("idField");//on donne un nom qui permettra de l'identifier dans la liste des components
			
			firstNameField = new JTextField(employee.getFirstName());
			firstNameField.setName("firstNameField");
			lastNameField = new JTextField(employee.getLastName());
			lastNameField.setName("lastNameField");
			
			//On indique le bon d�partement de l'employ�
			for(int i = 0; i< departments.length; i++) {
				if(departments[i].getDepartmentNumber() == employee.getIdDepartment()) {
					departmentsCombo.setSelectedItem(departmentNames[i]);
					break;
				}
			}
		} 
		//sinon on est entrain de cr�er un employ�
		else {
			idField = new JTextField(Integer.toString(company.getListEmployees().size() + 1));
			idField.setName("idField");
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
			
		}
		
		//on ajoute les champs
		fieldsEntryPanel.add(createEntryFieldPanel("Nom :",lastNameField));
		fieldsEntryPanel.add(createEntryFieldPanel("Pr�nom :",firstNameField));
		fieldsEntryPanel.add(createEntryFieldPanel("Identifiant :", idField));
		fieldsEntryPanel.add(createEntryFieldPanel("D�partement :",departmentsCombo));
		fieldsEntryPanel.add(createEntryFieldPanel("Horaire de d�but :",startSchedulePanel));
		fieldsEntryPanel.add(createEntryFieldPanel("Horaire de fin :",endSchedulePanel));
		//on cr�er un panel pour les boutons
		JPanel allButtonsPannel = new JPanel();
		
		//bouton de validation
		JButton validationButton = new JButton("Valider");
		
		//On modifie l'action en fonction de si on ajout ou modifie un employ�
		if(employeeIsModifiying()) {
			validationButton.addActionListener(new modifyEmployeeController(this, company, employee, listComponentView));
		}else {
			validationButton.addActionListener(new addEmployeeController(this, company, listComponentView));
		}
		allButtonsPannel.add(validationButton);
		
		//Bouton pour annuler ou supprimer un employ�
		if(employeeIsModifiying()) {
			
			//affichage des heures suppl�mentaire
			
			String displayOverTimeHour;//permet de g�rer l'affichage des heures suppl�mentaire
			long numberOverTimeHour = employee.getReportingOfDayWorked().getOverTimeHour().toHours();
			System.out.println(numberOverTimeHour);
			if(numberOverTimeHour == 0) {
				displayOverTimeHour = "Aucunes";
			}
			else {
				displayOverTimeHour = Long.toString(numberOverTimeHour);
			}
			fieldsContainer.add(createEntryFieldPanel("Heures suppl�mentaires : "+ displayOverTimeHour),BorderLayout.SOUTH);
			
			
			JButton deleteButton = new JButton("Supprimer");
			deleteButton.addActionListener(new deleteEmployeeController(this, company, employee));
			allButtonsPannel.add(deleteButton);
		}
		else {
			//Button pour annuler l'ajout de l'employ�e
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
		
		//On ajoute tous les components utilis� a la liste des components:
		listComponentView.add(lastNameField);
		listComponentView.add(firstNameField);
		listComponentView.add(idField);
		listComponentView.add(departmentsCombo);	
		return mainContainer;
	}
	
	/**
	 * Permet de cr�er un panel pour demander les horaires de l'employ� (permet de faire les horaires de d�but et de fin)
	 * @param hours tableau de strings contenant les heures
	 * @param minutes tableau de strings contenant les minutes
	 * @param format string qui permet de diff�rentier les ComboBox en ajoutant un nom avec le format (horraire de d�but ou de fin)
	 * 			format doit prendre la valeur de "end" ou "start" sinon cela l�ve une exception
	 * @return un panel contenant les champs pour saisir les horraires.
	 */
	private JPanel createFieldSchedule(String[] hours,String[] minutes,String format) {
		
		if (format != "end" && format != "start") {
			String errorMessage = "le param�tre format dans la m�thode createFieldSchedule doit �tre �gale a end ou start";
			throw new IllegalArgumentException(errorMessage);
		}
		
		JPanel fieldsContainer = new JPanel();
		fieldsContainer.setLayout(new GridLayout(5,3));
		String[] days = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};
		for(int i = 0; i < 5; i++){
			JLabel dayOfSchedule = new JLabel(days[i] +" : ");
			JComboBox<String> hourField = new JComboBox<String>(hours);
			JComboBox<String> minuteField = new JComboBox<String>(minutes);
			hourField.setName("ComboBox hour"+format+days[i]);
			minuteField.setName("ComboBox minute"+format+days[i]);
			//si on modifie l'employ� on selectionne ses horaires
			if(employeeIsModifiying()) {
				Day currentDay = employee.getPlanning().getDayList().get(i);
				int numberHour;
				int numberMinute;
				switch (format) {
					case "start":
						numberHour = currentDay.getTimeStart().getHour();
						hourField.setSelectedIndex(numberHour);
						numberMinute = currentDay.getTimeStart().getMinute();
						minuteField.setSelectedIndex((numberMinute/15)); //on divise par 15 pour r�cup�rer l'indice dans la liste d�roulante de valeur (00,15,30,45)
						break;
					case "end":
						numberHour = currentDay.getTimeEnd().getHour();
						hourField.setSelectedIndex(numberHour);
						numberMinute = currentDay.getTimeEnd().getMinute();
						minuteField.setSelectedIndex((numberMinute/15));
						break;
				}				
			}//sinon on en prend par des valeurs par d�faut
			else {
				switch (format) {
				case "start":
					hourField.setSelectedIndex(8);
					minuteField.setSelectedIndex(0);
					break;
				case "end":
					hourField.setSelectedIndex(18);
					minuteField.setSelectedIndex(0);
					break;
				}
			}
			//On ajoute les components a la liste des commponent de la view
			listComponentView.add(hourField);
			listComponentView.add(minuteField);
			fieldsContainer.add(dayOfSchedule);
			fieldsContainer.add(hourField);
			fieldsContainer.add(minuteField);
		}
		return fieldsContainer;
	}
	
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * Permet de savoir si l'on modifie l'employee
	 * @return vrai si on modifie l'employ�, faux si on ajoute un employ�
	 */
	private boolean employeeIsModifiying() {
		return this.employee != null;
	}	
	
	/**
	 * Permet de faciliter l'ajout d'un champ de saisie pour l'�dition de l'employ�
	 * @param fieldName Nom du champ d'�dition
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
	 * Permet de faciliter l'ajout d'un champ de saisie pour l'�dition de l'employ�
	 * @param fieldName Nom du champ d'�dition
	 * @param value Composant � ajouter afin de pouvoir �diter l'employ�
	 * @return Panneau contenant un label ainsi que le composant pass� en param�tre
	 */
	private JPanel createEntryFieldPanel(String fieldName, Component value) {
		JPanel panel = createEntryFieldPanel(fieldName);
		if(value != null)
			panel.add(value);
		return panel;
	}
}
