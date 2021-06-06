package projetPointeuseSSM;

import java.time.Duration;
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

	public static Company company;
	
	public static MainFrame mainFrame;
	
	public static void main(String[] args)
	{
	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				mainStub();
				Main.mainFrame = new MainFrame(company);
				Main.mainFrame.setVisible(true);
				MainFrameController controllerMainFrame = new MainFrameController(company);
				
				
			}
		});

	}
	/**
	 * @brief fonction qui instancie une entreprise a partir de rien
	 */
	public static void mainStub() {
		//On cr�e une nouvelle instance de notre JDialog
		Main.company = new Company("Company");
		Department dep =  new Department();
		Department dep1 =  new Department();
		dep.setDepartmentName("RH");
		dep1.setDepartmentName("Prod");
		Main.company.addDepartment(dep);
		Main.company.addDepartment(dep1);
		Employee test = new Employee();
		test.setFirstNameEmployee("Prenom"); 
		test.setLastNameEmployee("nom");
		test.setIdDepartment(1);
		test.setIdEmployee(1);
		test.addHourWorked(Duration.ofHours(5));
		Planning pla = new Planning();
		pla.PlanningStub();
		test.setPlanning(pla);
		test.updateHoursToDo();
		Main.company.addEmployee(test, dep);
	}
}
