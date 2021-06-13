package fr.polytech.projetPointeuseSSM.application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import fr.polytech.projetPointeuseSSM.application.controller.MainFrameController;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.Department;
import fr.polytech.projetPointeuseSSM.application.model.Employee;
import fr.polytech.projetPointeuseSSM.application.model.Planning;
import fr.polytech.projetPointeuseSSM.application.model.Reporting;
import fr.polytech.projetPointeuseSSM.application.view.MainFrame;


/**
 * Class allowing to launch the application on a machine which will contain all the information of the company
 * @author ThÃ©o Millaire Quentin Schau Matthis Servoz Emeric Verrier
 *
 */
public class Main {

    /*
     * Company managed by the application
     */
    public static Company company;
    
    /*
     * the main frame of the application
     */
    public static MainFrame mainFrame;
    
    
    public static void main(String[] args)
    {
    
        try {
            SwingUtilities.invokeAndWait(new Runnable(){
                public void run(){
                    if (Serialization.getFILE_COMPANY().length() > 0) {
                        try {
                            company = Serialization.readCompany();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        mainStub();
                    }
                    mainFrame = new MainFrame(company);
                    MainFrameController controller = new MainFrameController(company);
                    controller.setMainFrame(new MainFrame(company));
                    mainFrame.setVisible(true);
                    
                    
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(mainFrame,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }

    /**
     * function that instantiates a company from scratch
     */
    public static void mainStub() {
        Main.company = new Company("Company");
        Department dep =  new Department();
        Department dep1 =  new Department();
        dep.setDepartmentName("RH");
        dep1.setDepartmentName("Prod");
        Main.company.addDepartment(dep);
        Main.company.addDepartment(dep1);
        Employee test = new Employee();
        test.setFirstNameEmployee("Pierre"); 
        test.setLastNameEmployee("Laroche");
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
        LocalDateTime date7 = date5.plusDays(1);
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