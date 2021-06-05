package projetPointeuseSSM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.SwingUtilities;

import controller.MainFrameController;
import view.MainFrame;
public class Main {

	public static void main(String[] args)
	{
	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On cr�e une nouvelle instance de notre JDialog
				Company company = new Company("Company");
				Department dep =  new Department();
				Department dep1 =  new Department();
				dep.setDepartmentName("RH");
				dep1.setDepartmentName("Prod");
				company.addDepartment(dep);
				company.addDepartment(dep1);
			
				MainFrame fenetre = new MainFrame(company);
				MainFrameController controllerMainFrame = new MainFrameController(company);
				controllerMainFrame.setMainFrame(fenetre);
				
			}
		});

	}
	/**
	 * @brief fonction qui instancie une entreprise a partir de rien
	 */
	public void Strump() {
		//Creation of four employee
		Employee Employe1 = new Employee();
		Employee Employe2 = new Employee();
		Employee Employe3 = new Employee();
		Employee Employe4 = new Employee();
		Employee Employe5 = new Employee();
		Employee Employe6 = new Employee();
		//Make the employee list for department
		ArrayList<Employee> EmployeeListe = new ArrayList<Employee>();
		EmployeeListe.add(Employe1);
		EmployeeListe.add(Employe2);
		EmployeeListe.add(Employe3);
		EmployeeListe.add(Employe4);
		
		ArrayList<Employee> EmployeeListeHome = new ArrayList<Employee>();
		EmployeeListeHome.add(Employe5);
		EmployeeListeHome.add(Employe6);
		
		Department Departement = new Department("DI",7,4,EmployeeListe);
		
		//Creation of the planning
		/*LocalTime start = LocalTime.of(8, 0);
		LocalTime end = LocalTime.of(16, 0);
		
		Day Lundi = new Day(start,end,"Lundi");
		Day Mardi = new Day(start,end,"Mardi");
		Day Mercredi = new Day(start,end,"Mercredi");
		Day Jeudi = new Day(start,end,"Jeudi");
		Day Vendredi = new Day(start,end,"Vendredi");
		
		List<Day> tousLesJours = new ArrayList<>();
		tousLesJours.add(Lundi);
		tousLesJours.add(Mardi);
		tousLesJours.add(Mercredi);
		tousLesJours.add(Jeudi);
		tousLesJours.add(Vendredi);*/
		
		Planning planning = new Planning();
		planning.PlanningStub();
		
		//Creation time clock
		TimeClock pointeuse = new TimeClock(EmployeeListe,EmployeeListeHome);
		for(Employee employe : pointeuse.GetListofEmployeeWork()) {
			employe.setPlanning(planning);
			employe.toString();
		}
		
		
	}
}
