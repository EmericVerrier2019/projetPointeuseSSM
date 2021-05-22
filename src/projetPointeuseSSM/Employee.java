/**
 * The class used to model an employee
 * 
 * This class is composed of 7 attributes :
 * -a identifier which is composed of type int
 * -a last name which is composed of type String
 * -a first name which is composed of type String
 * -a department identifier which is composed of type int
 * -a planning which is composed of type CPlanning
 * -a number of theoretical hours that the employee is supposed to do. Which is composed of type LocalTime
 * -a number of hours completed that the employee have done. Which is composed of type LocalTime 
 * 
 * The class has all methods setters and getters, which allow to update and get the different attributes of this one
 * 
 * @author Quentin
 * @version 1.0
 * 
 */

package projetPointeuseSSM;


import java.time.*;
import java.time.temporal.ChronoUnit;

import org.w3c.dom.events.EventException;

public class Employee {

	private int idEmployee;
	private String lastNameEmployee;
	private String firstNameEmployee;
	private int idDepartment;
	private Planning planningEmployee;
	private Duration hoursToDo;
	private Duration hoursWorked;
	private boolean isWorking; //this boolean mean the employee is present at work
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	/**
	 * Default constructor, which create Employee
	 */
	public Employee(){
		setHoursWorked(Duration.of(0,ChronoUnit.HOURS));
	}
	
	/**
	 * Constructor which create a employee with the first name, the last name and the Employee's id of department
	 * @param String firstName: the first name, String lastName: the last name,int idDepartment: the id of department,
	 * 			int idEmployee : the Employee's id
	 */
	public Employee(String firstName,String lastName, int idDepartment, int idEmployee) {
		setFirstNameEmployee(firstName);
		setLastNameEmployee(lastName);
		setIdDepartment(idDepartment);
		setIdEmployee(idEmployee);
		setHoursWorked(Duration.of(0,ChronoUnit.HOURS));
	}
	
	/**
	 * Constructor which create a employee with the id of the employee and the if of the department
	 * @param int idDepartment: the id of department,int idEmployee : the Employee's id
	 * 
	 */
	public Employee(int idDepartment, int idEmployee) {
		setIdDepartment(idDepartment);
		setIdEmployee(idEmployee);
		setHoursWorked(Duration.of(0,ChronoUnit.HOURS));
		
	}
	
	/******************/
	/***** Setter *****/
	/******************/
	
	
	/**
	 * Update the attribute idEmployee
	 * @param int newId, the new employee's id we want to set
	 */
	public void setIdEmployee(int newId) {
		this.idEmployee = newId;
	}
	
	/**
	 * Update the attribute lastNameEmployee
	 * @param String newLastName, the new employee's last name we want to set
	 */
	public void setLastNameEmployee(String newLastName) {
		this.lastNameEmployee = newLastName;
	}
	
	/**
	 * Update the attribute firstNameEmployee
	 * @param String newFirstName, the new employee's first name we want to set
	 */
	public void setFirstNameEmployee(String newFirstName) {
		this.firstNameEmployee = newFirstName;
	}
	
	/**
	 * Update the attribute idDepartment
	 * @param int newNumber, the new employee's id of department we want to set
	 */
	public void setIdDepartment(int newNumber) {
		this.idDepartment = newNumber;
	}
	
	/**
	 * Update the attribute planningEmployee
	 * @param Planning newPlanning, the new employee's planning we want to set
	 * @see Planning
	 */
	public void setPlanning(Planning newPlanning) {
		this.planningEmployee = newPlanning;
	}
	
	/**
	 * Update the attribute hoursTheory
	 * @param Duration newHour, the new employee's number of hours to do that we want to set
	 */
	private void setHoursToDo(Duration newHours) {
		this.hoursToDo = newHours;
	}
	
	/**
	 * Update the attribute hoursDo
	 * @param Duration newHour, the new employee's number of hours completed that we want to set
	 * 
	 */
	private void setHoursWorked(Duration newHours) {
		this.hoursWorked = newHours;
	}
	
	/**
	 * Update the attribute isWorking
	 * @param boolean isWorking, we want to set if the employee is present at work
	 */
	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	
	/******************/
	/***** Getter *****/
	/******************/
	
	/**
	 * Return the attribute idEmployee
	 * @return int idEmployee
	 */
	public int getIdEmployee() {
		return this.idEmployee;
	}
	
	/**
	 * Return the attribute lastNameEmployee
	 * @return String lastNameEmployee
	 */
	public String getLastName() {
		return this.lastNameEmployee;
	}
	
	/**
	 * Return the attribute firstNameEmployee
	 * @return String firstNameEmployee
	 */
	public String getFirstName() {
		return this.firstNameEmployee;
	}
	
	/**
	 * Return the attribute idDepartment
	 * @return int idDepartment
	 */
	public int getIdDepartment() {
		return this.idDepartment;
	}
	
	/**
	 * Return the attribute planningEmployee
	 * @return CPlanning planningEmployee
	 */
	public Planning getPlanning() {
		return this.planningEmployee;
	}
	
	/**
	 * Return the attribute hoursTheory
	 * @return LocalTime hoursTheory;
	 */
	public Duration getHoursToDo() {
		return this.hoursToDo;
	}
	
	/**
	 * Return the attribute hoursDo
	 * @return LocalTime hoursDo
	 */
	public Duration getHoursWorked() {
		return this.hoursWorked;
	}
	
	/**
	 * Return the attribute isWorking
	 * @return boolean isWorking
	 */
	public boolean getIsWorking() {
		return this.isWorking;
	}
	
	/*******************/
	/***** Methods *****/
	/*******************/
	
	
	public void addHourWorked(Duration hour) {
		setHoursWorked(getHoursWorked().plus(hour));
	}
	
	public String toString() {
		return "Employé avec l'identifiant: "+getIdEmployee()+System.lineSeparator()+
				"Nom: "+getLastName()+" Prenom: "+getFirstName()+System.lineSeparator()+
				"Identifiant de son departement: "+getIdDepartment()+System.lineSeparator()+
				"Heures que l'employé doit faire: "+getHoursToDo()+System.lineSeparator()+
				"Heures que l'employé a réalisé: "+getHoursWorked()+System.lineSeparator()+
				"L'employé est présent sur le lieu de travail: "+getIsWorking();
	}
	
	public void updateHoursToDo() {
		if (getPlanning() == null ) {
			String errorMessage = "The employe's planning is not set";
			short codeError = 1;
			throw new EventException(codeError,errorMessage);
		}else {
			Planning planning = getPlanning();
			//For the developpment we use stub methods
			planning.PlanningStub();
			setHoursToDo(planning.getWeeklyWorkedHours());
		}
	}
}
