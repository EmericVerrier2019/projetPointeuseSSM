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

public class Employee {

	private int idEmployee;
	private String lastNameEmployee;
	private String firstNameEmployee;
	private int idDepartment;
	private Planning planningEmployee;
	private LocalTime hoursTheory;
	private LocalTime hoursDo;
	private boolean isWorking; //this boolean mean the employee is present at work
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	/**
	 * Default constructor, which create Employee
	 */
	public Employee(){
		setHoursDo(LocalTime.of(0, 0, 0));
		setHoursTheory(LocalTime.of(0, 0, 0));
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
		setHoursDo(LocalTime.of(0, 0, 0));
		setHoursTheory(LocalTime.of(0, 0, 0));
	}
	
	/**
	 * Constructor which create a employee with the id of the employee and the if of the department
	 * @param int idDepartment: the id of department,int idEmployee : the Employee's id
	 * 
	 */
	public Employee(int idDepartment, int idEmployee) {
		setIdDepartment(idDepartment);
		setIdEmployee(idEmployee);
		setHoursDo(LocalTime.of(0, 0, 0));
		setHoursTheory(LocalTime.of(0, 0, 0));
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
	 * @param LocalTime newHour, the new employee's number of theoretical hours that we want to set
	 */
	public void setHoursTheory(LocalTime newHour) {
		this.hoursTheory = newHour;
	}
	
	/**
	 * Update the attribute hoursDo
	 * @param LocalTime newHour, the new employee's number of hours completed that we want to set
	 * 
	 */
	public void setHoursDo(LocalTime newHour) {
		this.hoursDo = newHour;
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
	public LocalTime getHoursTheory() {
		return this.hoursTheory;
	}
	
	/**
	 * Return the attribute hoursDo
	 * @return LocalTime hoursDo
	 */
	public LocalTime getHoursDo() {
		return this.hoursDo;
	}
	
	/**
	 * Return the attribute isWorking
	 * @return boolean isWorking
	 */
	public boolean getIsWorking() {
		return this.isWorking;
	}
}
