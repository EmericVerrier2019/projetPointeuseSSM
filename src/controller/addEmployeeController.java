package controller;

import projetPointeuseSSM.Serialization;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Department;
import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Main;
import projetPointeuseSSM.Planning;
import view.DetailsEmployeeView;



public class addEmployeeController implements ActionListener, Serializable {

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * view qui est rattaché au controleur
	 */
	private DetailsEmployeeView view;
	
	/**
	 * L'entreprise géré par l'application
	 */
	private Company company;
	
	/*
	 * Liste des components rattachés à la vue et contenant les informations relatives à l'employé
	 */
	private ArrayList<Component> listComponentView;
	
	
	/**
	 * Contructeur qui initialise le controleur
	 * @param view Vue rattaché au controleur
	 * @param company Entreprise géré par l'application
	 * @param listComponentView Liste des components rattachés a la vue
	 */
	public addEmployeeController(DetailsEmployeeView view, Company company, ArrayList<Component> listComponentView) {
		super();
		this.view = view;
		this.company = company;
		this.listComponentView = listComponentView;
	}

	/**
	 * Permet de créer une heure de type LocalTime en utilisant deux JComboBox
	 * @param hourCombo Liste déroulante contenant l'heure
	 * @param minutesCombo Liste déroulante contenant les minutes
	 * @return L'heure créee à partir des deux listes déroulantes
	 */
	private LocalDateTime createLocalTimeHour(JComboBox<String> hourCombo,JComboBox<String> minutesCombo) {
		int numberHour = Integer.parseInt((String) hourCombo.getSelectedItem());
		int numberMinutes = Integer.parseInt((String) minutesCombo.getSelectedItem());
		return LocalDateTime.of(LocalDate.now(),LocalTime.of(numberHour, numberMinutes));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JTextField idField = new JTextField();
			JTextField firstFieldName = new JTextField();
			JTextField lastFieldName= new JTextField();
			JComboBox<String> departmentsCombo = new JComboBox<String>();
			Department department = new Department();
			for(Component component : listComponentView) {
				//verifie que le nom et prénom ont été saisi
				if(component.getName() == "firstNameField" || component.getName() == "lastNameField") {
					if(((JTextField) component).getText().isEmpty()){
						String message = "Vous devez renseigner le nom et le prénom";
						JOptionPane.showMessageDialog(view,message,"Message",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
							
				//On recupère les autres components
				switch(component.getName()) {
				case "idField" :
					idField = (JTextField) component;
					break;				
				case "firstNameField":
					firstFieldName = (JTextField) component;
					break;
				case "lastNameField":
					lastFieldName = (JTextField) component;
					break;
				case "departmentsCombo":
					departmentsCombo = (JComboBox<String>) component;
					//On récupère le department
					for (Department boucleDepartment : company.getListDepartment()) {
						if (((String) departmentsCombo.getSelectedItem()) == boucleDepartment.getDepartmentName()){
							department = boucleDepartment ;
						}
					}
					break;
				}
				
			}
			
			
			//creation du planning
			
			Planning planning = new Planning();
			planning.PlanningStub();//On modifie juste les horaires des jours
			
			//Lors de la création de la liste des components les 20 premiers servent pour le planning
			//les 10 premiers pour les heures d'arriver et les 10 autres pour les de départ de la journée
			for(int i = 0; i < 10; i += 2) {
				LocalDateTime beginningHour = createLocalTimeHour((JComboBox<String>)listComponentView.get(i),(JComboBox<String>)listComponentView.get(i+1));
				LocalDateTime enddingHour = createLocalTimeHour((JComboBox<String>)listComponentView.get(i+10),(JComboBox<String>)listComponentView.get(i+11));
				//On défini les horaires
				planning.getDayList().get(i/2).setTimeStart(beginningHour);
				planning.getDayList().get(i/2).setTimeEnd(enddingHour);
			}
			
			
			
			Employee employee = new Employee(firstFieldName.getText(),lastFieldName.getText(),department.getDepartmentNumber(),Integer.parseInt(idField.getText()));
			employee.setPlanning(planning);
			company.addEmployee(employee, department);
			if(Serialization.getFILE_COMPANY().delete()) {
				Serialization.writeCompany(company);
			}
			Main.mainFrame.updateEmployeeTable();
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(view,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
}