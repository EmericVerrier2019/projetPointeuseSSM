package projetPointeuseSSM;

import java.lang.reflect.InvocationTargetException;
import java.time.*;

import java.time.chrono.ChronoLocalDate;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.sun.jdi.event.MonitorWaitedEvent;

import view.timeClockView.*;
import controller.MainFrameController;
import view.MainFrame;
public class Main {

	public static Company company;
	
	public static MainFrame mainFrame;
	
	public static void main(String[] args)
	{
	
		try {
			SwingUtilities.invokeAndWait(new Runnable(){
				public void run(){
					//On crée une nouvelle instance de notre JDialog

					mainStub();
					mainFrame = new MainFrame(company);
					MainFrameController controller = new MainFrameController(company);
					controller.setMainFrame(new MainFrame(company));
					controller.getMainFrame().setVisible(true);
					
					
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(mainFrame,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
		}
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				TimeClockFrame fenetrePointeuse = new TimeClockFrame();
				fenetrePointeuse.setVisible(true);
			}
		});
	}

	/**
	 * @brief fonction qui instancie une entreprise à partir de rien
	 */
	public static void mainStub() {
		//On crée une nouvelle instance de notre JDialog
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
		Planning pla = new Planning();
		pla.PlanningStub();
		test.setPlanning(pla);
		Reporting report = new Reporting(test);
		LocalDateTime date1 = LocalDateTime.of(LocalDate.of(2021, 6, 7),LocalTime.of(8, 0));
		LocalDateTime date2 = date1.plusHours(9);
		LocalDateTime date3 = date1.plusDays(1);
		LocalDateTime date4 = date3.plusHours(5);
		LocalDateTime date5 = date3.plusDays(1);
		LocalDateTime date6 = date5.plusHours(10);
		LocalDateTime date7 = LocalDateTime.now();
		report.updateReporting(date1);
		report.updateReporting(date2);
		report.updateReporting(date3);
		report.updateReporting(date4);
		report.updateReporting(date5);
		report.updateReporting(date6);
		report.updateReporting(date7);
		test.setReportingOfDayWorked(report);
		Main.company.addEmployee(test, dep);
	}

}
